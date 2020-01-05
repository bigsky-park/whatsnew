package kr.bigskypark.whatsnew.notifier.slack;

import kr.bigskypark.whatsnew.notifier.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class SimpleSlackClient implements SlackClient {

  private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

  private final OkHttpClient okHttpClient;

  private final String webHookUrl;

  @Override
  public void sendMessage(final Message message) {
    final var body = RequestBody.create(JsonUtils.toString(message), JSON);
    final var request = new Request.Builder().url(webHookUrl).post(body).build();
    try (final var response = okHttpClient.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new SlackSendUnSuccessfulException(response.message());
      }
    } catch (IOException ex) {
      log.error(ex.getMessage());
    }
  }
}
