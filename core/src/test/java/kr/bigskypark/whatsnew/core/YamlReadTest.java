package kr.bigskypark.whatsnew.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import kr.bigskypark.whatsnew.core.dto.JobConfiguration;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class YamlReadTest {

  @Test
  void read_job_configuration_file() throws Exception {
    final var path = "src/test/resources/config/book__del__bigsky-park__del__job_configuration.yml";
    final var file = Paths.get(path).toFile();

    final var mapper = new ObjectMapper(new YAMLFactory());
    final var jobConfiguration = mapper.readValue(file, JobConfiguration.class);

    assertThat(jobConfiguration).isNotNull();
    assertThat(jobConfiguration.getVersion()).isNotBlank();
    assertThat(jobConfiguration.getCategory()).isNotNull();
    assertThat(jobConfiguration.getPeriod()).isNotNull();
    assertThat(jobConfiguration.getDetails()).isNotNull().isNotEmpty();
  }
}
