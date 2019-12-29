package kr.bigskypark.whatsnew.collector.book.service;

import kr.bigskypark.whatsnew.collector.book.client.BookSearchClient;
import kr.bigskypark.whatsnew.collector.book.client.NaverBookCategories;
import kr.bigskypark.whatsnew.collector.book.dto.Book;
import kr.bigskypark.whatsnew.collector.book.dto.DetailBookSearchRequest;
import kr.bigskypark.whatsnew.collector.book.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookSearchEngine {

  private final BookSearchClient bookSearchClient;

  public List<Book> search(
      final String title, final int categoryCode, final ZonedDateTime runAt, final int minusDays) {
    final var dateAfter = TimeUtils.toDateStringMinusDays(runAt, minusDays);
    final var dateTo = TimeUtils.toDateString(runAt);
    final var category = NaverBookCategories.fromCode(categoryCode);

    final var request =
        DetailBookSearchRequest.builder()
            // display is max number of search results.
            // TODO: currently it is fixed to 30. make it configurable
            .display(30)
            .dTitle(title)
            .dCatg(category.code())
            .dDafr(dateAfter)
            .dDato(dateTo)
            .build();
    final var rss = bookSearchClient.searchFor(request);
    return rss.getChannel().getItems().stream().map(Book::fromItem).collect(Collectors.toList());
  }
}
