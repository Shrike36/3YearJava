package service;

import DAO.ProductDAO;
import connection.Utils;
import domain.DefaultProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService implements Service<DefaultProduct>{

    private ProductDAO productDAO;

    public ProductService() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO = new ProductDAO(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DefaultProduct getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO.setConnection(connection);
            return productDAO.getById(id);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public DefaultProduct getByName(String name) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO.setConnection(connection);
            return productDAO.getByName(name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<DefaultProduct> getAll() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO.setConnection(connection);
            return productDAO.getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(DefaultProduct product) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO.setConnection(connection);
            productDAO.save(product);
            product.setId(productDAO.getByName(product.getName()).getId());
        } catch (SQLException e) {
            //productDAO.delete();
            System.out.println(e.getMessage());
        }
    }

    public void delete(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO.setConnection(connection);
            productDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(DefaultProduct product) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO.setConnection(connection);
            productDAO.update(product.getId(), product);
        } catch (SQLException e) {
            //productDAO.delete();
            System.out.println(e.getMessage());
        }
    }
}
