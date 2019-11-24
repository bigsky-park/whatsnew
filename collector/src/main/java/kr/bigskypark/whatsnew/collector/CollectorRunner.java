package kr.bigskypark.whatsnew.collector;

import kr.bigskypark.whatsnew.collector.book.client.BookSearchClient;
import kr.bigskypark.whatsnew.collector.book.client.NaverBookCategories;
import kr.bigskypark.whatsnew.collector.book.dto.DetailBookSearchRequest;
import kr.bigskypark.whatsnew.collector.book.dto.Rss;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class CollectorRunner implements ApplicationRunner {

    private final BookSearchClient bookSearchClient;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        // run example
        final var request = DetailBookSearchRequest.builder()
                .display(10)
                .dTitle("쿠버네티스")
                .dCatg(NaverBookCategories.COMPUTER_IT_GENERAL.code())
                .dDafr("20191001")
                .dDato("20191101")
                .build();

        final var rss = bookSearchClient.searchFor(request);
        System.out.println(rss);
    }

}
