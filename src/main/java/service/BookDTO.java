package service;

import DAO.BookDAO;
import connection.Utils;
import domain.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BookDTO {

    private BookDAO bookDAO;
    private long id;

    public BookDTO() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO = new BookDAO(connection);
            id = bookDAO.getAll().get(bookDAO.getAll().size()-1).getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Book getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO.setConnection(connection);
            return bookDAO.getById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Book> getAll() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO.setConnection(connection);
            return bookDAO.getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Book book) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO.setConnection(connection);
            book.setId(book.getId());
            bookDAO.save(book);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO.setConnection(connection);
            bookDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
