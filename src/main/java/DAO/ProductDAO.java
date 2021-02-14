package DAO;

import domain.*;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;

public class ProductDAO implements DAO<DefaultProduct> {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public ProductDAO() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DefaultProduct getById(long id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE id  = " + id)) {
                while (rs.next()) {
                    return new DefaultProduct(rs.getLong("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new IllegalStateException("Record with id " + id + "not found");
    }

    public DefaultProduct getByName(String name) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE name like '" + name + "'")) {
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

    @Override
    public List<DefaultProduct> getAll() {
        final List<DefaultProduct> result = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {
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
                "INSERT INTO Product(name) VALUES(?)")
        ) {
            int count = 1;
            //preparedStatement.setLong(count++, product.getId());
            preparedStatement.setString(count++, product.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long id, DefaultProduct product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Product SET name = ? WHERE id = ?"
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
                "DELETE FROM Product WHERE id = ?")
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