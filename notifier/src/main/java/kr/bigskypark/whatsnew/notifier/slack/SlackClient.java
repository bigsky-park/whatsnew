package kr.bigskypark.whatsnew.notifier.slack;

public interface SlackClient {

  void sendMessage(Message message);
}
