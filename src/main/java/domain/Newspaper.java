package domain;

import java.util.Date;

public class Newspaper{

    private long id;
    private long number;
    private Date date;

    public Newspaper(long id, long number, Date date) {
        this.id = id;
        this.number = number;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
