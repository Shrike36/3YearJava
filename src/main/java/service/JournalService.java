package service;

import DAO.JournalDAO;
import connection.Utils;
import domain.Book;
import domain.Journal;
import domain.Newspaper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JournalService implements Service<Journal>{

    private JournalDAO journalDAO;

    public JournalService() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO = new JournalDAO(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Journal getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO.setConnection(connection);
            return journalDAO.getById(id);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Journal> getAll() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO.setConnection(connection);
            return journalDAO.getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Journal journal) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO.setConnection(connection);
            journalDAO.save(journal);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO.setConnection(connection);
            journalDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Journal> getJournalsInWarehouse() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO.setConnection(connection);
            return journalDAO.getJournalsInWarehouse();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Journal> getSoldJournals() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO.setConnection(connection);
            return journalDAO.getSoldJournals();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return null;
        }
    }
}
