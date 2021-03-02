package DAO;

import domain.Book;
import domain.Journal;
import domain.Newspaper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewspaperDAO implements DAO<Newspaper>{

    private Connection connection;

    private static NewspaperDAO instance;

    public static synchronized NewspaperDAO getInstance(Connection connection) {
        if (instance == null) {
            instance = new NewspaperDAO(connection);
        }
        return instance;
    }

    public NewspaperDAO(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Newspaper getById(long id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Newspaper WHERE id  = " + id)) {
                while (rs.next()) {
                    return new Newspaper(
                            rs.getLong("id"),
                            rs.getLong("number1"),
                            rs.getDate("date1")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public List<Newspaper> getAll() {
        final List<Newspaper> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Newspaper")) {
                while (rs.next()) {
                    result.add(new Newspaper(
                            rs.getLong("id"),
                            rs.getLong("number1"),
                            rs.getDate("date1")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void save(Newspaper newspaper) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Newspaper(id,number1,date1) VALUES(?,?,?)")
        ) {
            int count = 1;
            preparedStatement.setLong(count++, newspaper.getId());
            preparedStatement.setLong(count++, newspaper.getNumber());
            preparedStatement.setDate(count++, new java.sql.Date(newspaper.getDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long id, Newspaper newspaper) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Newspaper SET number1 = ?, date1 = ? WHERE id = ?"
        )) {

            int count = 1;
            preparedStatement.setLong(count++, newspaper.getNumber());
            preparedStatement.setDate(count++, new java.sql.Date(newspaper.getDate().getTime()));
            preparedStatement.setLong(count, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Newspaper WHERE id = ?")
        ) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + id + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Newspaper> getNewspapersInWarehouse() {
        final List<Newspaper> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Newspaper where id in (Select id from product)")) {
                while (rs.next()) {
                    result.add(new Newspaper(
                            rs.getLong("id"),
                            rs.getLong("number1"),
                            rs.getDate("date1")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public List<Newspaper> getSoldNewspapers() {
        final List<Newspaper> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Newspaper where id in (Select id from soldproduct)")) {
                while (rs.next()) {
                    result.add(new Newspaper(
                            rs.getLong("id"),
                            rs.getLong("number1"),
                            rs.getDate("date1")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
