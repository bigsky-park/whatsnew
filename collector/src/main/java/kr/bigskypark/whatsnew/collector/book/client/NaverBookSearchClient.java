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
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static kr.bigskypark.whatsnew.collector.book.config.NaverApiProperties.CLIENT_ID_HEADER;
import static kr.bigskypark.whatsnew.collector.book.config.NaverApiProperties.CLIENT_SECRET_HEADER;

@RequiredArgsConstructor
@Component
public class NaverBookSearchClient implements BookSearchClient {

    private final NaverApiProperties naverApiProperties;

    private final OkHttpClient okHttpClient;

    private final XmlMapper xmlMapper;

    @Override
    public Rss searchFor(final DetailBookSearchRequest searchRequest) {
        HttpUrl httpUrl = buildUrlForDetailBookSearch(searchRequest);
        Request request = buildRequestFor(httpUrl);

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return xmlMapper.readValue(requireNonNull(response.body()).byteStream(), Rss.class);
            } else {
                throw new BookSearchClientException("cannot get search result. response code: " + response.code());
            }
        } catch (IOException e) {
            throw new BookSearchClientException(e);
        }
    }

    private HttpUrl buildUrlForDetailBookSearch(final DetailBookSearchRequest searchRequest) {
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme(naverApiProperties.getScheme())
                .host(naverApiProperties.getHost())
                .addPathSegments(naverApiProperties.getPaths());
        setQueryParams(builder, searchRequest);
        return builder.build();
    }

    private void setQueryParams(final HttpUrl.Builder httpUrlBuilder,
                                final DetailBookSearchRequest searchRequest) {
        addParamIfValid(httpUrlBuilder, i -> (i > 0 && i <= 100), "display", searchRequest.getDisplay());
        addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_titl", searchRequest.getDTitle());
        addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_auth", searchRequest.getDAuth());
        addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_cont", searchRequest.getDCont());
        addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_isbn", searchRequest.getDIsbn());
        addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_publ", searchRequest.getDPubl());
        addParamIfValid(httpUrlBuilder, Objects::nonNull, "d_catg", searchRequest.getDCatg());
        addDateParamsIfValid(httpUrlBuilder, searchRequest.getDDafr(), searchRequest.getDDato());
    }

    private void addParamIfValid(final HttpUrl.Builder httpUrlBuilder,
                                 final Predicate<Integer> predicate,
                                 final String key,
                                 final int value) {
        if (predicate.test(value)) {
            httpUrlBuilder.addQueryParameter(key, Integer.toString(value));
        }
    }

    private void addParamIfValid(final HttpUrl.Builder httpUrlBuilder,
                                 final Predicate<String> predicate,
                                 final String key,
                                 final String value) {
        if (predicate.test(value)) {
            httpUrlBuilder.addQueryParameter(key, value);
        }
    }

    private void addDateParamsIfValid(final HttpUrl.Builder httpUrlBuilder,
                                      final String dDafr,
                                      final String dDato) {
        // TODO: do validation for date parameters
        httpUrlBuilder.addQueryParameter("d_dafr", dDafr);
        httpUrlBuilder.addQueryParameter("d_dato", dDato);
    }

    private Request buildRequestFor(final HttpUrl httpUrl) {
        return new Request.Builder()
                .url(httpUrl)
                .addHeader(CLIENT_ID_HEADER, naverApiProperties.getClientId())
                .addHeader(CLIENT_SECRET_HEADER, naverApiProperties.getClientSecret())
                .build();
    }

}