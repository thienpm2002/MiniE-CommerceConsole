package repositories;

import java.util.List;

public interface RepositoryInterface<L, T> {
    public void save(T entity);

    public T findById(L id);

    public List<T> findAll();

    public void deleteById(L id);
}
