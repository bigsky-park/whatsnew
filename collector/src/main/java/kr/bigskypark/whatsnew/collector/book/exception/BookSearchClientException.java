package kr.bigskypark.whatsnew.collector.book.exception;

public class BookSearchClientException extends RuntimeException {

    public BookSearchClientException(final String message) {
        super(message);
    }

    public BookSearchClientException(final Throwable cause) {
        super(cause);
    }

}
