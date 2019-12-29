package kr.bigskypark.whatsnew.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = Period.PeriodBuilder.class)
@Value
@Builder
public class Period {

  private PeriodUnit unit;

  private Integer value;

  @JsonPOJOBuilder(withPrefix = "")
  static class PeriodBuilder {}

  public String toPeriodString() {
    return "last_" + value + "_" + unit.name().toLowerCase();
  }
}
