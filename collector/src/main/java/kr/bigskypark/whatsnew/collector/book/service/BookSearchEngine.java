package kr.bigskypark.whatsnew.collector.book.service;

import kr.bigskypark.whatsnew.collector.book.client.BookSearchClient;
import kr.bigskypark.whatsnew.collector.book.client.NaverBookCategories;
import kr.bigskypark.whatsnew.collector.book.dto.DetailBookSearchRequest;
import kr.bigskypark.whatsnew.collector.book.dto.Item;
import kr.bigskypark.whatsnew.collector.book.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookSearchEngine {

    private final BookSearchClient bookSearchClient;

    public List<Item> search(final String title,
                             final int categoryCode,
                             final int minusDays) {
        final var dateAfter = TimeUtils.getDateMinusDaysFrom(minusDays);
        final var dateTo = TimeUtils.getCurrentDate();
        final var category = NaverBookCategories.fromCode(categoryCode);

        final var request = DetailBookSearchRequest.builder()
                .display(100)
                .dTitle(title)
                .dCatg(category.code())
                .dDafr(dateAfter)
                .dDato(dateTo)
                .build();
        final var rss = bookSearchClient.searchFor(request);
        return rss.getChannel().getItems();
    }

}
