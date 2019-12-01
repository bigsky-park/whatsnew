package kr.bigskypark.whatsnew.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PeriodUnit {

    @JsonProperty("day")
    DAY("day"),

    @JsonProperty("week")
    WEEK("week");

    private final String value;

    PeriodUnit(final String value) {
        this.value = value;
    }

}
