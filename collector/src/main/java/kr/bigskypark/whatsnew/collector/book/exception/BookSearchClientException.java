package kr.bigskypark.whatsnew.collector.book.exception;

public class BookSearchClientException extends RuntimeException {

    public BookSearchClientException(String message) {
        super(message);
    }

    public BookSearchClientException(Throwable cause) {
        super(cause);
    }

}
