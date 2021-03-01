package service;

import connection.Utils;
import domain.DefaultProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface Service<T> {

    T getById(long id);

    List<T> getAll();

    void save(T product);

    void delete(long id);

    void update(T product);
}
