package DAO;

import domain.Publishing;
import domain.Publishing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublishingDAO implements DAO<Publishing>{

    private Connection connection;

    public PublishingDAO(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Publishing getById(long id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Publishing WHERE id  = " + id)) {
                while (rs.next()) {
                    return new Publishing(rs.getLong("id"),
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
    public List<Publishing> getAll() {

        final List<Publishing> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Publishing")) {
                while (rs.next()) {
                    result.add(new Publishing(rs.getLong("id"),
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
    public void save(Publishing publishing) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Publishing(name) VALUES(?,?)")
        ) {
            int count = 1;
            preparedStatement.setString(count++, publishing.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long id, Publishing publishing) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Publishing SET name = ? WHERE id = ?"
        )) {

            int count = 1;
            preparedStatement.setString(count++, publishing.getName());
            preparedStatement.setLong(count, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Publishing WHERE id = ?")
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
