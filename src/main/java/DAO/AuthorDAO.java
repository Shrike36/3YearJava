package DAO;

import domain.Author;
import domain.Book;
import domain.DefaultProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO implements DAO<Author>{

    private Connection connection;

    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Author getById(long id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Author WHERE id  = " + id)) {
                while (rs.next()) {
                    return new Author(rs.getLong("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public List<Author> getAll() {

        final List<Author> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Author")) {
                while (rs.next()) {
                    result.add(new Author(rs.getLong("id"),
                            rs.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void save(Author author) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Author(name) VALUES(?,?)")
        ) {
            int count = 1;
            preparedStatement.setString(count++, author.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long id, Author author) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Author SET name = ? WHERE id = ?"
        )) {

            int count = 1;
            preparedStatement.setString(count++, author.getName());
            preparedStatement.setLong(count, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Author WHERE id = ?")
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
