package kr.bigskypark.whatsnew.collector.book.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    public static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("YYYYMMdd");

    private static final ZoneId SEOUL_ZONE = ZoneId.of("Asia/Seoul");

    private TimeUtils() {
        throw new AssertionError("utility class cannot be initialized");
    }

    public static ZonedDateTime getCurrentDatetime() {
        return ZonedDateTime.now(SEOUL_ZONE);
    }

    public static String toDateString(final ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(SIMPLE_DATE_FORMAT);
    }

    public static String toDateStringMinusDays(final ZonedDateTime zonedDateTime, final int minusDays) {
        return zonedDateTime.minusDays(minusDays).format(SIMPLE_DATE_FORMAT);
    }

}
