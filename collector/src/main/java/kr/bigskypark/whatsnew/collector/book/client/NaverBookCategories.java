package kr.bigskypark.whatsnew.collector.book.client;

import java.util.HashMap;
import java.util.Map;

public enum NaverBookCategories {

    COMPUTER_IT_GENERAL(280),

    COMPUTER_IT_SPECIAL(280020);

    private static Map<Integer, NaverBookCategories> map = new HashMap<>();

    static {
        for (NaverBookCategories c : values()) {
            map.put(c.code, c);
        }
    }

    NaverBookCategories(int code) {
        this.code = code;
    }

    private int code;

    public String code() {
        return Integer.toString(code);
    }

    public static NaverBookCategories fromCode(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        throw new IllegalArgumentException(code + " is not valid category code");
    }

}
