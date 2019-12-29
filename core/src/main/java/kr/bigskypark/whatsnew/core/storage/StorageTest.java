package kr.bigskypark.whatsnew.core.storage;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.bigskypark.whatsnew.core.Category;
import kr.bigskypark.whatsnew.core.dto.Item;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class StorageTest {

  public static void main(String[] args) throws Exception {

    final var s3 =
        AmazonS3ClientBuilder.standard()
            .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
            .withRegion(Regions.AP_NORTHEAST_2)
            .build();
    final var bucket = "s3-whatsnew";
    final var yamlMapper = new ObjectMapper(new YAMLFactory());
    final var jsonMapper =
        new ObjectMapper()
            //                .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());
    ;
    jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    final var storage = new S3Storage(s3, bucket, yamlMapper, jsonMapper);
    final var configurations = storage.listJobConfigurationPaths("collector");

    configurations.ifPresent(
        c -> {
          c.forEach(
              v -> {
                storage
                    .getConfig(v)
                    .ifPresent(
                        config -> {
                          final var key =
                              S3KeyResolver.resolveForItem(
                                  config.getCategory(),
                                  "20191201",
                                  "bigsky-park",
                                  config.getPeriod(),
                                  "쿠버네티스");
                          System.out.println(key);
                        });
              });
        });

    final var data = new ArrayList<JsonNode>();
    ObjectNode node = JsonNodeFactory.instance.objectNode();
    node.put("hello", "world");
    data.add(node);

    ObjectNode node2 = JsonNodeFactory.instance.objectNode();
    node2.put("hi", "world");
    data.add(node2);

    final var path = "data/book/dt=20191201/bigsky-park__del__last_7_days__del__쿠버네티스.json";
    final var item =
        Item.builder()
            .username("bigsky-park")
            .runAt(ZonedDateTime.now())
            .category(Category.BOOK)
            .data(data)
            .build();
    // dt = ${run date}
    // username + delimiter + period + keyword
    storage.putItem(path, item);
    Optional<Item> get = storage.getItem(path);
    System.out.println(get);
  }
}
