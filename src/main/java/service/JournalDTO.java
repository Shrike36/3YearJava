package service;

import DAO.JournalDAO;
import connection.Utils;
import domain.Journal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JournalDTO {

    private JournalDAO journalDAO;
    private long id;

    public JournalDTO() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO = new JournalDAO(connection);
            id = journalDAO.getAll().get(journalDAO.getAll().size()-1).getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Journal getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            journalDAO.setConnection(connection);
            return journalDAO.getById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            journal.setId(journal.getId());
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
    
}
