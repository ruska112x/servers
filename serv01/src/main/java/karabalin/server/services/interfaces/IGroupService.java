package karabalin.server.services.interfaces;

import karabalin.server.entities.Group;
import karabalin.server.exceptions.ServiceException;

import java.util.List;

public interface IGroupService {
    long addGroup(String name) throws ServiceException;

    Group updateGroup(Group t) throws ServiceException;

    long deleteGroup(long i) throws ServiceException;

    Group getGroup(long i) throws ServiceException;

    List<Group> getGroups() throws ServiceException;

}
