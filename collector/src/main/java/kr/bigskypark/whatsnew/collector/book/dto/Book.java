package kr.bigskypark.whatsnew.collector.book.dto;

import lombok.Data;

@Data
public class Book {

    private String title;

    private String author;

    private String link;

    private String publisher;

    private String pubdate;

    public static Book fromItem(final Item item) {
        final var book = new Book();
        book.setTitle(item.getTitle());
        book.setAuthor(item.getAuthor());
        book.setLink(item.getLink());
        book.setPublisher(item.getPublisher());
        book.setPubdate(item.getPubdate());
        return book;
    }

}
