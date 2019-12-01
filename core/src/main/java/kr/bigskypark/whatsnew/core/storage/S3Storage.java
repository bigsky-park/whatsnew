package kr.bigskypark.whatsnew.core.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bigskypark.whatsnew.core.dto.Item;
import kr.bigskypark.whatsnew.core.dto.JobConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class S3Storage implements Storage {

    private final AmazonS3 s3;

    private final String bucket;

    private final ObjectMapper yamlMapper;

    private final ObjectMapper jsonMapper;

    @Override
    public Optional<List<String>> listJobConfigurationPaths(final String type) {
        try {
            final var result = s3.listObjectsV2(bucket, JOB_CONFIG_FILE_PREFIX + "/" + type);
            final var paths = result.getObjectSummaries().stream()
                    .filter(summary -> summary.getKey().endsWith(CONFIG_FILE_EXTENSION))
                    .map(S3ObjectSummary::getKey)
                    .collect(Collectors.toList());
            return Optional.of(paths);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<JobConfiguration> getConfig(final String path) {
        try {
            final var object = s3.getObject(bucket, path);
            final var content = object.getObjectContent();
            final var config = yamlMapper.readValue(content.readAllBytes(), JobConfiguration.class);
            return Optional.of(config);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Item> getItem(final String path) {
        try {
            final var object = s3.getObject(bucket, path);
            final var content = object.getObjectContent();
            return Optional.of(jsonMapper.readValue(content.readAllBytes(), Item.class));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void putItem(final String path,
                        final Item item) {
        try {
            final var bytes = jsonMapper.writeValueAsBytes(item);
            final var metadata = new ObjectMetadata();
            metadata.setContentType("application/json");
            metadata.setContentLength(bytes.length);
            final var request = new PutObjectRequest(bucket, path, new ByteArrayInputStream(bytes), metadata);
            s3.putObject(request);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

}
