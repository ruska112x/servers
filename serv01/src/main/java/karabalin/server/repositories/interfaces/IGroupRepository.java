package karabalin.server.repositories.interfaces;

import java.util.List;

public interface IGroupRepository<I, T, U extends List<T>> {

    I add(T t);

    void update(T t);
    void deleteById(Long id);
    T getById(Long id);

    U getAll();
}
