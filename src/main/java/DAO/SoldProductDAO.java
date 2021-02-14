package DAO;

import domain.DefaultProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SoldProductDAO implements DAO<DefaultProduct>{

    private Connection connection;

    public SoldProductDAO(Connection connection) {
        this.connection = connection;
    }

    public SoldProductDAO() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DefaultProduct getById(long id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM SoldProduct WHERE id  = " + id)) {
                while (rs.next()) {
                    return new DefaultProduct(rs.getLong("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + " not found");
    }

    @Override
    public List<DefaultProduct> getAll() {
        final List<DefaultProduct> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM SoldProduct")) {
                while (rs.next()) {
                    result.add(new DefaultProduct(rs.getLong("id"),
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
    public void save(DefaultProduct product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO SoldProduct(id,name) VALUES(?,?)")
        ) {
            int count = 1;
            preparedStatement.setLong(count++, product.getId());
            preparedStatement.setString(count++, product.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long id, DefaultProduct product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE SoldProduct SET name = ? WHERE id = ?"
        )) {

            int count = 1;
            preparedStatement.setString(count++, product.getName());
            preparedStatement.setLong(count, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM SoldProduct WHERE id = ?")
        ) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + id + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DefaultProduct getByName(String name) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM SoldProduct WHERE name like '" + name + "'")) {
                while (rs.next()) {
                    return new DefaultProduct(rs.getLong("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with name " + name + " not found");
    }
}
