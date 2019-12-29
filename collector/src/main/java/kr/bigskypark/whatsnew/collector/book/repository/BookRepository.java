package kr.bigskypark.whatsnew.collector.book.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bigskypark.whatsnew.collector.book.dto.Book;
import kr.bigskypark.whatsnew.collector.book.util.TimeUtils;
import kr.bigskypark.whatsnew.core.Category;
import kr.bigskypark.whatsnew.core.Period;
import kr.bigskypark.whatsnew.core.dto.Item;
import kr.bigskypark.whatsnew.core.storage.S3KeyResolver;
import kr.bigskypark.whatsnew.core.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BookRepository {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private final Storage storage;

  public void saveAll(
      final String user,
      final Category category,
      final ZonedDateTime runAt,
      final Period period,
      final String keyword,
      final List<Book> books) {
    final var itemToPersist =
        Item.builder()
            .username(user)
            .category(category)
            .runAt(runAt)
            .data(toJsonNodeList(books))
            .build();

    final var date = TimeUtils.toDateString(runAt);
    final var path = S3KeyResolver.resolveForItem(category, date, user, period, keyword);
    storage.putItem(path, itemToPersist);
  }

  private static List<JsonNode> toJsonNodeList(final List<Book> books) {
    return books.stream()
        .map(book -> MAPPER.convertValue(book, JsonNode.class))
        .collect(Collectors.toList());
  }
}
