package service;

import DAO.PublishingDAO;
import connection.Utils;
import domain.Publishing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class PublishingDTO {

    private PublishingDAO publishingDAO;
    private long id;

    public PublishingDTO() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            publishingDAO = new PublishingDAO(connection);
            id = publishingDAO.getAll().get(publishingDAO.getAll().size()-1).getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Publishing getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            publishingDAO.setConnection(connection);
            return publishingDAO.getById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Publishing> getAll() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            publishingDAO.setConnection(connection);
            return publishingDAO.getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Publishing publishing) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            publishingDAO.setConnection(connection);
            if (publishing.getId() <= this.id & publishing.getId() != 0){
                publishingDAO.update(publishing.getId(), publishing);
            } else if (publishing.getId() == 0){
                this.id++;
                publishing.setId(this.id);
                publishingDAO.save(publishing);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            publishingDAO.setConnection(connection);
            publishingDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
