package kr.bigskypark.whatsnew.collector.book.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;

import java.time.LocalDate;
import java.util.function.Predicate;

import static kr.bigskypark.whatsnew.collector.book.util.TimeUtils.SIMPLE_DATE_FORMAT;

@Slf4j
public final class HttpUrlUtils {

    private HttpUrlUtils() {
        throw new AssertionError("utility class cannot be initialized");
    }

    public static void addParamIfValid(final HttpUrl.Builder httpUrlBuilder,
                                       final Predicate<Integer> predicate,
                                       final String key,
                                       final int value) {
        if (predicate.test(value)) {
            httpUrlBuilder.addQueryParameter(key, Integer.toString(value));
        }
    }

    public static void addParamIfValid(final HttpUrl.Builder httpUrlBuilder,
                                       final Predicate<String> predicate,
                                       final String key,
                                       final String value) {
        if (predicate.test(value)) {
            httpUrlBuilder.addQueryParameter(key, value);
        }
    }

    public static void addDateRangeParamsIfValid(final HttpUrl.Builder httpUriBuilder,
                                                 final String dateFromKey,
                                                 final String dateFromValue,
                                                 final String dateToKey,
                                                 final String dateToValue) {
        if (isValidDateRange(dateFromValue, dateToValue)) {
            httpUriBuilder.addQueryParameter(dateFromKey, dateFromValue);
            httpUriBuilder.addQueryParameter(dateToKey, dateToValue);
        }
    }

    private static boolean isValidDateRange(final String dateFromValue,
                                            final String dateToValue) {
        try {
            final var dateBefore = LocalDate.parse(dateFromValue, SIMPLE_DATE_FORMAT);
            final var dateTo = LocalDate.parse(dateToValue, SIMPLE_DATE_FORMAT);
            return dateBefore.compareTo(dateTo) < 0;
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return false;
        }
    }

}
