package domain;

import java.util.Date;

public class Journal extends Newspaper{

    private int countOfPages;

    public Journal(long id, long number, Date date, int countOfPages) {
        super(id, number, date);
        this.countOfPages = countOfPages;
    }

    public int getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(int countOfPages) {
        this.countOfPages = countOfPages;
    }
}
