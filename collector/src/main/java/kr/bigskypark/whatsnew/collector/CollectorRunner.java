package kr.bigskypark.whatsnew.collector;

import kr.bigskypark.whatsnew.collector.book.client.BookSearchClient;
import kr.bigskypark.whatsnew.collector.book.client.NaverBookCategories;
import kr.bigskypark.whatsnew.collector.book.dto.DetailBookSearchRequest;
import kr.bigskypark.whatsnew.collector.book.dto.Rss;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CollectorRunner implements CommandLineRunner {

    private final BookSearchClient bookSearchClient;

    @Override
    public void run(String... args) throws Exception {
        // run example
        DetailBookSearchRequest request = DetailBookSearchRequest.builder()
                .display(10)
                .dTitle("쿠버네티스")
                .dCatg(NaverBookCategories.COMPUTER_IT_GENERAL.code())
                .dDafr("20191001")
                .dDato("20191101")
                .build();

        Rss rss = bookSearchClient.searchFor(request);
        System.out.println(rss);

        System.exit(0);
    }

}
