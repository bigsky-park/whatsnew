package kr.bigskypark.whatsnew.collector.book;

import kr.bigskypark.whatsnew.collector.DataCollectingJob;
import kr.bigskypark.whatsnew.collector.book.service.BookSearchEngine;
import kr.bigskypark.whatsnew.core.dto.JobConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookDataCollectingJob implements DataCollectingJob {

    private final BookSearchEngine searchEngine;

    @Override
    public void run(final JobConfiguration configuration) {
        ensureConfigurationIsValidForBookSearch(configuration);
        final var details = configuration.getDetails();
        final var title = (String) details.get("title_keyword");
        final var categoryCode = (Integer) details.get("category_code");

        final var items = searchEngine.search(title, categoryCode, configuration.getPeriod().getValue());
        // TODO: implement persistence logic
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
