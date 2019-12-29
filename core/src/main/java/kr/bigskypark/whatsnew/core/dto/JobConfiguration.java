package kr.bigskypark.whatsnew.core.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kr.bigskypark.whatsnew.core.Category;
import kr.bigskypark.whatsnew.core.Period;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@JsonDeserialize(builder = JobConfiguration.JobConfigurationBuilder.class)
@Builder
@Value
public class JobConfiguration {

  private String version;

  private String username;

  private Category category;

  private Period period;

  private Map<String, Object> details;

  @JsonPOJOBuilder(withPrefix = "")
  static class JobConfigurationBuilder {}
}
