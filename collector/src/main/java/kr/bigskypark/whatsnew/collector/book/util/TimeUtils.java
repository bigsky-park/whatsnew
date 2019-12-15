package kr.bigskypark.whatsnew.collector.book.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    public static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("YYYYMMdd");

    private TimeUtils() {
        throw new AssertionError("utility class cannot be initialized");
    }

    public static String getCurrentDate() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).format(SIMPLE_DATE_FORMAT);
    }

    public static String getDateMinusDaysFrom(final int numberOfDays) {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).minusDays(numberOfDays).format(SIMPLE_DATE_FORMAT);
    }

}
