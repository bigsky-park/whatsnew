package kr.bigskypark.whatsnew.notifier.slack;

public class SlackSendUnSuccessfulException extends RuntimeException {

  public SlackSendUnSuccessfulException(final String message) {
    super(message);
  }
}
