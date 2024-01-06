package ma.enset.recsys.Dao;

import java.util.List;

public interface Dao <T> {
    void save(T o);
    void removeById(long id);
    T getById(long id);
    List<T> getAll();
    void update(T o);
}