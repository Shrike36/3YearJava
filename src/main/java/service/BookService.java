package service;

import DAO.BookDAO;
import connection.Utils;
import domain.Book;
import domain.Newspaper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BookService implements Service<Book>{

    private BookDAO bookDAO;

    public BookService() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO = new BookDAO(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Book getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO.setConnection(connection);
            return bookDAO.getById(id);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Book> getBooksInWarehouse() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO.setConnection(connection);
            return bookDAO.getBooksInWarehouse();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Book> getSoldBooks() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            bookDAO.setConnection(connection);
            return bookDAO.getSoldBooks();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
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
