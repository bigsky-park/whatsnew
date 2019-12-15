package kr.bigskypark.whatsnew.core.storage;

import kr.bigskypark.whatsnew.core.dto.Item;
import kr.bigskypark.whatsnew.core.dto.JobConfiguration;

import java.util.List;
import java.util.Optional;

public interface Storage {

    String FILE_NAME_DELIMITER = "__del__";

    String DATA_FILE_EXTENSION = ".json";

    String JOB_CONFIG_FILE_PREFIX = "config/job";

    String CONFIG_FILE_EXTENSION = ".yml";

    Optional<List<String>> listJobConfigurationPaths(String type);

    Optional<JobConfiguration> getConfig(String path);

    Optional<Item> getItem(String path);

    void putItem(String path,
                 Item item);
}
