package karabalin.server.services.interfaces;

import karabalin.server.entities.GroupDTO;
import karabalin.server.exceptions.ServiceException;

import java.util.List;

public interface IGroupService {
    long addGroup(GroupDTO group) throws ServiceException;

    void updateGroup(GroupDTO t) throws ServiceException;

    void deleteGroup(long i) throws ServiceException;

    GroupDTO getGroup(long i) throws ServiceException;

    List<GroupDTO> getGroups() throws ServiceException;

}
