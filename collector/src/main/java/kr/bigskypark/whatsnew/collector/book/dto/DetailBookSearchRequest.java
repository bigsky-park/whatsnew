package kr.bigskypark.whatsnew.collector.book.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DetailBookSearchRequest {

  @Builder.Default private int display = -1;

  private String dTitle;

  private String dAuth;

  private String dCont;

  private String dIsbn;

  private String dPubl;

  private String dDafr;

  private String dDato;

  private String dCatg;
}
