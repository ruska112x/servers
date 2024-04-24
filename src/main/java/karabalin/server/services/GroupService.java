package karabalin.server.services;

import karabalin.server.entities.GroupDTO;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.exceptions.ServiceException;
import karabalin.server.repositories.interfaces.IGroupRepository;
import karabalin.server.services.interfaces.IGroupService;

import java.util.List;

public class GroupService implements IGroupService {
    private final IGroupRepository groupsRepository;

    public GroupService(IGroupRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public long addGroup(GroupDTO group) throws ServiceException {
        try {
            return groupsRepository.add(group);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateGroup(GroupDTO group) throws ServiceException {
        try {
            if (groupsRepository.update(group) == null) {
                throw new ServiceException("Group with id = " + group.id() + " not found!");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteGroup(long id) throws ServiceException {
        try {
            groupsRepository.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public GroupDTO getGroup(long id) throws ServiceException {
        try {
            var result = groupsRepository.getById(id);
            if (result == null) {
                throw new ServiceException("Group with id = " + id + " not found!");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<GroupDTO> getGroups() throws ServiceException {
        try {
            return groupsRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
