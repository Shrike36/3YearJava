package service;

import DAO.NewspaperDAO;
import connection.Utils;
import domain.Journal;
import domain.Newspaper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class NewspaperService implements Service<Newspaper>{

    private NewspaperDAO newspaperDAO;

    public NewspaperService() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            newspaperDAO = NewspaperDAO.getInstance(connection);//new NewspaperDAO(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Newspaper getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            newspaperDAO.setConnection(connection);
            return newspaperDAO.getById(id);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Newspaper> getAll() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            newspaperDAO.setConnection(connection);
            return newspaperDAO.getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Newspaper newspaper) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            newspaperDAO.setConnection(connection);
            newspaperDAO.save(newspaper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            newspaperDAO.setConnection(connection);
            newspaperDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Newspaper> getNewspapersInWarehouse() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            newspaperDAO.setConnection(connection);
            return newspaperDAO.getNewspapersInWarehouse();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Newspaper> getSoldNewspapers() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            newspaperDAO.setConnection(connection);
            return newspaperDAO.getSoldNewspapers();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public void update(Newspaper newspaper) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            newspaperDAO.setConnection(connection);
            newspaperDAO.update(newspaper.getId(), newspaper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
