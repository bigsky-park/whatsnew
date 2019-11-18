package kr.bigskypark.whatsnew.collector.book.dto;

import lombok.Data;

/**
 * @see <a href="https://developers.naver.com/docs/search/book/"/>
 */
@Data
public class Item {

    private String title;

    private String link;

    private String image;

    private String author;

    private int price;

    private int discount;

    private String publisher;

    private String pubdate;

    private String isbn;

    private String description;

}
