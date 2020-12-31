package service;

import DAO.ProductDAO;
import DAO.SoldProductDAO;
import connection.Utils;
import domain.DefaultProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SoldProductDTO {

    private SoldProductDAO productDAO;
    private long id;

    public SoldProductDTO() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO = new SoldProductDAO(connection);
            id = productDAO.getAll().get(productDAO.getAll().size()-1).getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DefaultProduct getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            productDAO.setConnection(connection);
            return productDAO.getById(id);
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
            if (product.getId() <= this.id & product.getId() != 0){
                productDAO.update(product.getId(), product);
            } else if (product.getId() == 0){
                this.id++;
                product.setId(this.id);
                productDAO.save(product);
            }
        } catch (SQLException e) {
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

}