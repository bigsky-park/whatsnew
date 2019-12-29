package kr.bigskypark.whatsnew.collector.book.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

/** @see <a href="https://developers.naver.com/docs/search/book/"/> */
@Data
public class Channel {

  private String title;

  private String link;

  private String description;

  private String lastBuildDate;

  private int total;

  private int start;

  private int display;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "item")
  private List<Item> items;
}
