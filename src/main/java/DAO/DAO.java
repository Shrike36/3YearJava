package DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    T getById(long id);

    List<T> getAll();

    void save(T t);

    void update(long id, T t);

    void delete(long id);
}