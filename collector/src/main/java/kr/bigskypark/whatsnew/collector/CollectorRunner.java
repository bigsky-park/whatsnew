package kr.bigskypark.whatsnew.collector;

import kr.bigskypark.whatsnew.collector.book.BookDataCollectingJob;
import kr.bigskypark.whatsnew.core.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CollectorRunner implements ApplicationRunner {

  private final Storage storage;

  private final BookDataCollectingJob bookDataCollectingJob;

  @Override
  public void run(final ApplicationArguments args) throws Exception {
    final var type = getParameter(args, "category");
    storage
        .listJobConfigurationPaths(type)
        .ifPresent(
            configurationPaths ->
                configurationPaths.forEach(
                    configPath -> {
                      storage
                          .getConfig(configPath)
                          .ifPresent(
                              config -> {
                                final var job = getJobFor(config.getCategory().name());
                                job.run(config);
                              });
                    }));
  }

  private static String getParameter(final ApplicationArguments args, final String name) {
    if (args.containsOption(name)) {
      final var values = args.getOptionValues(name);
      if (values == null || values.size() != 1) {
        throw new IllegalArgumentException(
            "parameter %s is not provided properly. " + "It should be provided just one time");
      }
      return values.get(0).trim();
    } else {
      throw new IllegalArgumentException(String.format("Argument %s does not exists", name));
    }
  }

  private DataCollectingJob getJobFor(final String category) {
    if ("book".equalsIgnoreCase(category)) {
      return bookDataCollectingJob;
    } else {
      throw new IllegalArgumentException(category + " is invalid category name");
    }
  }
}
