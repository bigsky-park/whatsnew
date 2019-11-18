package kr.bigskypark.whatsnew.collector.book.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("naver.api")
public class NaverApiProperties {

    public static final String CLIENT_ID_HEADER = "X-Naver-Client-Id";

    public static final String CLIENT_SECRET_HEADER = "X-Naver-Client-Secret";

    private String scheme;

    private String host;

    private String paths;

    private String clientId;

    private String clientSecret;

}
