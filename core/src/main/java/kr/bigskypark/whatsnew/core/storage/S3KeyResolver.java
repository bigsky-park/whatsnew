package kr.bigskypark.whatsnew.core.storage;

import kr.bigskypark.whatsnew.core.Category;
import kr.bigskypark.whatsnew.core.Period;

public final class S3KeyResolver {

    private static final String DATA_KEY_FORMAT = "data/%s/dt=%s/%s" + Storage.FILE_NAME_DELIMITER + "%s"
            + Storage.FILE_NAME_DELIMITER + "%s" + Storage.DATA_FILE_EXTENSION;

    private S3KeyResolver() {
        throw new AssertionError("utility class cannot be initialized");
    }

    public static String resolveForItem(final Category category,
                                        final String date,
                                        final String username,
                                        final Period period,
                                        final String keyword) {
        return String.format(DATA_KEY_FORMAT, category.name().toLowerCase(),
                date, username, period.toPeriodString(), keyword);
    }

}
