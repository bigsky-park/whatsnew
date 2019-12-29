package kr.bigskypark.whatsnew.collector.book.client;

import kr.bigskypark.whatsnew.collector.book.dto.DetailBookSearchRequest;
import kr.bigskypark.whatsnew.collector.book.dto.Rss;

public interface BookSearchClient {

  Rss searchFor(DetailBookSearchRequest searchRequest);
}
