package kr.bigskypark.whatsnew.collector.book.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * @see <a href="https://developers.naver.com/docs/search/book/"/>
 */
@Data
@JacksonXmlRootElement(localName = "rss")
public class Rss {

    @JacksonXmlProperty(isAttribute = true)
    private String version;

    private Channel channel;

}
