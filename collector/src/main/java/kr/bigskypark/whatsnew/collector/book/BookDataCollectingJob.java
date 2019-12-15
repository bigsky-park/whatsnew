package kr.bigskypark.whatsnew.collector.book;

import kr.bigskypark.whatsnew.collector.DataCollectingJob;
import kr.bigskypark.whatsnew.collector.book.repository.BookRepository;
import kr.bigskypark.whatsnew.collector.book.service.BookSearchEngine;
import kr.bigskypark.whatsnew.collector.book.util.TimeUtils;
import kr.bigskypark.whatsnew.core.dto.JobConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookDataCollectingJob implements DataCollectingJob {

    private final BookSearchEngine searchEngine;

    private final BookRepository repository;

    @Override
    public void run(final JobConfiguration configuration) {
        ensureConfigurationIsValidForBookSearch(configuration);
        final var runAt = TimeUtils.getCurrentDatetime();
        final var period = configuration.getPeriod();
        final var details = configuration.getDetails();
        final var title = (String) details.get("title_keyword");
        final var categoryCode = (Integer) details.get("category_code");

        final var books = searchEngine.search(title, categoryCode, runAt, configuration.getPeriod().getValue());
        repository.saveAll(configuration.getUsername(), configuration.getCategory(), runAt, period, title, books);
    }

    private void ensureConfigurationIsValidForBookSearch(final JobConfiguration configuration) {
        final var details = configuration.getDetails();
        if (!details.containsKey("category_code")) {
            throw new IllegalArgumentException("configuration does not contains category_code");
        }
        if (!details.containsKey("title_keyword")) {
            throw new IllegalArgumentException("configuration does not contains title_keyword");
        }
    }

}
