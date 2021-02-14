package domain;

import java.util.Date;

public class Book {

    private long id;
    private int countOfPages;
    private String author;
    private String publishing;

    public Book(long id, int countOfPages, String author, String publishing) {
        this.id = id;
        this.countOfPages = countOfPages;
        this.author = author;
        this.publishing = publishing;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }
}
