package karabalin.server.services.interfaces;

import java.util.List;

public interface IGroupService<I, T, U extends List<T>> {
    I addGroup(String name);

    T updateGroup(T t);

    void deleteGroup(I i);

    T getGroup(I i);

    U getGroups();

}
