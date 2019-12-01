package kr.bigskypark.whatsnew.collector.book;

import kr.bigskypark.whatsnew.collector.DataCollectingJob;
import kr.bigskypark.whatsnew.core.dto.JobConfiguration;
import org.springframework.stereotype.Component;

// TODO: consider using lazy initialization
@Component
public class BookDataCollectingJob implements DataCollectingJob {

    @Override
    public void run(final JobConfiguration configuration) {
        // TODO: get values from configuration & create book search request

        // TODO: do search
//        final var request = DetailBookSearchRequest.builder()
//                .display(8)
//                .dTitle("쿠버네티스")
//                .dCatg(NaverBookCategories.COMPUTER_IT_GENERAL.code())
//                .dDafr("20190999")
//                .dDato("20191099")
//                .build();
//
//        final var rss = bookSearchClient.searchFor(request);
//        System.out.println(rss);

        // TODO: put searched item to storage
    }

}
