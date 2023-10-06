package karabalin.server.repositories;

import java.util.List;

public interface GroupRepository<T, U extends List<T>> {

    Long add(T t);

    void update(T t);
    void deleteById(Long id);
    T getById(Long id);

    U getAll();
}
