package kr.bigskypark.whatsnew.collector.common.s3.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.bigskypark.whatsnew.core.storage.S3Storage;
import kr.bigskypark.whatsnew.core.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {

  @Value("${storage.s3.bucket}")
  private String s3Bucket;

  @Bean
  public Storage storage() {
    final var s3 =
        AmazonS3ClientBuilder.standard()
            .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
            .withRegion(Regions.AP_NORTHEAST_2)
            .build();

    final var yamlMapper = new ObjectMapper(new YAMLFactory());
    final var jsonMapper =
        new ObjectMapper().registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());

    return new S3Storage(s3, s3Bucket, yamlMapper, jsonMapper);
  }
}
