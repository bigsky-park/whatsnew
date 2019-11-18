package kr.bigskypark.whatsnew.collector.book.client;

public enum NaverBookCategories {

    COMPUTER_IT_GENERAL(280),

    COMPUTER_IT_SPECIAL(280020);

    NaverBookCategories(int code) {
        this.code = code;
    }

    private int code;

    public String code() {
        return Integer.toString(code);
    }

}
