package DAO;

import domain.Book;
import domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements DAO<Book> {

    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Book getById(long id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Book WHERE id  = " + id)) {
                while (rs.next()) {
                    return new Book(rs.getLong("id"),
                            rs.getInt("countOfPages"),
                            rs.getLong("author"),
                            rs.getLong("publishing")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public List<Book> getAll() {
        final List<Book> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Book")) {
                while (rs.next()) {
                    result.add(new Book(rs.getLong("id"),
                            rs.getInt("countOfPages"),
                            rs.getLong("author"),
                            rs.getLong("publishing")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void save(Book book) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Book(id,countOfPages,author,publishing) VALUES(?,?,?,?)")
        ) {
            int count = 1;
            preparedStatement.setLong(count++, book.getId());
            preparedStatement.setInt(count++, book.getCountOfPages());
            preparedStatement.setLong(count++, book.getAuthorId());
            preparedStatement.setLong(count++, book.getPublishingId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long id, Book book) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Book SET countOfPages = ?, author = ?, publishing = ? WHERE id = ?"
        )) {

            int count = 1;
            preparedStatement.setInt(count++, book.getCountOfPages());
            preparedStatement.setLong(count++, book.getAuthorId());
            preparedStatement.setLong(count++, book.getPublishingId());
            preparedStatement.setLong(count, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Book WHERE id = ?")
        ) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + id + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
