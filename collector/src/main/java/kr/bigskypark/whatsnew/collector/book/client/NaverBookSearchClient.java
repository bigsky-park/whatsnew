package kr.bigskypark.whatsnew.collector.book.client;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kr.bigskypark.whatsnew.collector.book.config.NaverApiProperties;
import kr.bigskypark.whatsnew.collector.book.dto.DetailBookSearchRequest;
import kr.bigskypark.whatsnew.collector.book.dto.Rss;
import kr.bigskypark.whatsnew.collector.book.exception.BookSearchClientException;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static kr.bigskypark.whatsnew.collector.book.config.NaverApiProperties.CLIENT_ID_HEADER;
import static kr.bigskypark.whatsnew.collector.book.config.NaverApiProperties.CLIENT_SECRET_HEADER;
import static kr.bigskypark.whatsnew.collector.book.util.HttpUrlUtils.addDateRangeParamsIfValid;
import static kr.bigskypark.whatsnew.collector.book.util.HttpUrlUtils.addParamIfValid;

@RequiredArgsConstructor
@Component
public class NaverBookSearchClient implements BookSearchClient {

  private final NaverApiProperties naverApiProperties;

  private final OkHttpClient okHttpClient;

  private final XmlMapper xmlMapper;

  @Override
  public Rss searchFor(final DetailBookSearchRequest searchRequest) {
    final var httpUrl = buildUrlForDetailBookSearch(searchRequest);
    final var request = buildRequestFor(httpUrl);

    try (var response = okHttpClient.newCall(request).execute()) {
      if (response.isSuccessful()) {
        return xmlMapper.readValue(requireNonNull(response.body()).byteStream(), Rss.class);
      } else {
        throw new BookSearchClientException(
            "cannot get search result. response code: " + response.code());
      }
    } catch (IOException e) {
      throw new BookSearchClientException(e);
    }
  }

  private HttpUrl buildUrlForDetailBookSearch(final DetailBookSearchRequest searchRequest) {
    final var builder =
        new HttpUrl.Builder()
            .scheme(naverApiProperties.getScheme())
            .host(naverApiProperties.getHost())
            .addPathSegments(naverApiProperties.getPaths());
    setQueryParams(builder, searchRequest);
    return builder.build();
  }

  private static void setQueryParams(
      final HttpUrl.Builder httpUrlBuilder, final DetailBookSearchRequest searchRequest) {
    addParamIfValid(
        httpUrlBuilder, i -> (i > -1 && i <= 100), "display", searchRequest.getDisplay());
    addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_titl", searchRequest.getDTitle());
    addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_auth", searchRequest.getDAuth());
    addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_cont", searchRequest.getDCont());
    addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_isbn", searchRequest.getDIsbn());
    addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_publ", searchRequest.getDPubl());
    addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_catg", searchRequest.getDCatg());
    addDateRangeParamsIfValid(
        httpUrlBuilder, "d_dafr", searchRequest.getDDafr(), "d_dato", searchRequest.getDDato());
  }

  private Request buildRequestFor(final HttpUrl httpUrl) {
    return new Request.Builder()
        .url(httpUrl)
        .addHeader(CLIENT_ID_HEADER, naverApiProperties.getClientId())
        .addHeader(CLIENT_SECRET_HEADER, naverApiProperties.getClientSecret())
        .build();
  }
}
