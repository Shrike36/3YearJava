package DAO;

import domain.Book;
import domain.Journal;
import domain.Journal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO implements DAO<Journal> {

    private Connection connection;

    public JournalDAO(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Journal getById(long id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Journal WHERE id  = " + id)) {
                while (rs.next()) {
                    return new Journal(
                            rs.getLong("id"),
                            rs.getLong("number1"),
                            rs.getDate("date1"),
                            rs.getInt("countOfPages")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + "not found");
    }

    @Override
    public List<Journal> getAll() {
        final List<Journal> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Journal")) {
                while (rs.next()) {
                    result.add(new Journal(
                            rs.getLong("id"),
                            rs.getLong("number1"),
                            rs.getDate("date1"),
                            rs.getInt("countOfPages")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void save(Journal journal) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Journal(id,number1,date1,countOfPages) VALUES(?,?,?,?)")
        ) {
            int count = 1;
            preparedStatement.setLong(count++, journal.getId());
            preparedStatement.setLong(count++, journal.getNumber());
            preparedStatement.setDate(count++, new java.sql.Date(journal.getDate().getTime()));
            preparedStatement.setInt(count++, journal.getCountOfPages());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long id, Journal journal) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Journal SET number1 = ?, date1 = ?, countOfPages = ? WHERE id = ?"
        )) {

            int count = 1;
            preparedStatement.setLong(count++, journal.getNumber());
            preparedStatement.setDate(count++, new java.sql.Date(journal.getDate().getTime()));
            preparedStatement.setInt(count++, journal.getCountOfPages());
            preparedStatement.setLong(count, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Journal WHERE id = ?")
        ) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + id + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Journal> getJournalsInWarehouse() {
        final List<Journal> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Journal where id in (Select id from product)")) {
                while (rs.next()) {
                    result.add(new Journal(
                            rs.getLong("id"),
                            rs.getLong("number1"),
                            rs.getDate("date1"),
                            rs.getInt("countOfPages")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public List<Journal> getSoldJournals() {
        final List<Journal> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Journal where id in (Select id from soldproduct)")) {
                while (rs.next()) {
                    result.add(new Journal(
                            rs.getLong("id"),
                            rs.getLong("number1"),
                            rs.getDate("date1"),
                            rs.getInt("countOfPages")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
