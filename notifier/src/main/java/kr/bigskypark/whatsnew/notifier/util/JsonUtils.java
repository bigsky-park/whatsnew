package kr.bigskypark.whatsnew.notifier.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private JsonUtils() {
    throw new AssertionError("utility class cannot be initialized");
  }

  public static <T> String toString(T value) {
    try {
      return MAPPER.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      return "";
    }
  }
}
