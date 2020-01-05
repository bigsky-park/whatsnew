package kr.bigskypark.whatsnew.notifier.util;

import kr.bigskypark.whatsnew.notifier.slack.Message;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonUtilsTest {

  @Test
  void json_parsing_test() {
    final var message = new Message();
    final var text = "hello world!";
    message.setText(text);

    final var toString = JsonUtils.toString(message);
    assertThat(toString).isEqualTo("{\"text\":\"hello world!\"}");
  }
}
