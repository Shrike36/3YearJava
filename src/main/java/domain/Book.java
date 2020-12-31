package domain;

import java.util.Date;

public class Book {

    private long id;
    private int countOfPages;
    private long authorId;
    private long publishingId;

    public Book(long id, int countOfPages, long authorId, long publishingId) {
        this.id = id;
        this.countOfPages = countOfPages;
        this.authorId = authorId;
        this.publishingId = publishingId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(int countOfPages) {
        this.countOfPages = countOfPages;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getPublishingId() {
        return publishingId;
    }

    public void setPublishingId(long publishingId) {
        this.publishingId = publishingId;
    }
}
