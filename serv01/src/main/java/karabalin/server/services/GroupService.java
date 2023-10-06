package karabalin.server.services;

import karabalin.server.entities.Group;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.exceptions.ServiceException;
import karabalin.server.repositories.interfaces.IGroupRepository;
import karabalin.server.services.interfaces.IGroupService;

import java.util.List;

public class GroupService implements IGroupService {
    private IGroupRepository groupsRepository;

    public GroupService(IGroupRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public long addGroup(String name) throws ServiceException {
        try {
            return groupsRepository.add(new Group(null, name));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Group updateGroup(Group group) throws ServiceException {
        try {
            if (groupsRepository.update(group) == null) {
                throw new ServiceException("Group with id = " + group.getId() + " not found");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return group;
    }

    @Override
    public long deleteGroup(long id) throws ServiceException {
        try {
            groupsRepository.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return id;
    }

    @Override
    public Group getGroup(long id) throws ServiceException {
        try {
            return groupsRepository.getById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    // TODO serviceException

    @Override
    public List<Group> getGroups() throws ServiceException {
        try {
            return groupsRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
