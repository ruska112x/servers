package karabalin.server.services.interfaces;

import karabalin.server.exceptions.ServiceException;

import java.util.List;

public interface IGroupService<I, T, U extends List<T>> {
    I addGroup(String name);

    T updateGroup(T t) throws ServiceException;

    I deleteGroup(I i) throws ServiceException;

    T getGroup(I i) throws ServiceException;

    U getGroups() throws ServiceException;

}
