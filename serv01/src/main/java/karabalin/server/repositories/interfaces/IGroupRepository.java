package karabalin.server.repositories.interfaces;

import java.util.List;

public interface IGroupRepository<I, T, U extends List<T>> {

    I add(T t);

    T update(T t);
    I deleteById(I id);
    T getById(Long id);

    U getAll();
}
