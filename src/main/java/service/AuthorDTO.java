package service;

import DAO.AuthorDAO;
import connection.Utils;
import domain.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AuthorDTO {

    private AuthorDAO authorDAO;
    private long id;

    public AuthorDTO() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            authorDAO = new AuthorDAO(connection);
            id = authorDAO.getAll().get(authorDAO.getAll().size()-1).getId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Author getById(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            authorDAO.setConnection(connection);
            return authorDAO.getById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Author> getAll() {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            authorDAO.setConnection(connection);
            return authorDAO.getAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(Author author) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            authorDAO.setConnection(connection);
            if (author.getId() <= this.id & author.getId() != 0){
                authorDAO.update(author.getId(), author);
            } else if (author.getId() == 0){
                this.id++;
                author.setId(this.id);
                authorDAO.save(author);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(long id) {
        try (Connection connection = DriverManager.getConnection(Utils.URL, Utils.USER, Utils.PASSWORD)
        ) {
            authorDAO.setConnection(connection);
            authorDAO.delete(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
