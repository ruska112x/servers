package karabalin.server.services;

import karabalin.server.entities.Group;
import karabalin.server.exceptions.ServiceException;
import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupRepository;
import karabalin.server.services.interfaces.IGroupService;

import java.util.List;

public class GroupService implements IGroupService<Long, Group, List<Group>> {
    private GroupRepository groupsRepository;

    public GroupService(DataBase dataBase) {
        groupsRepository = new GroupRepository(dataBase);
    }

    @Override
    public Long addGroup(String name) {
        return groupsRepository.add(new Group(null, name));
    }

    @Override
    public Group updateGroup(Group group) throws ServiceException {
        if (groupsRepository.update(group) == null) {
            throw new ServiceException("Group with id = " + group.getId() + " not found");
        }
        return group;
    }

    @Override
    public Long deleteGroup(Long id) throws ServiceException {
        if (groupsRepository.deleteById(id) == null) {
            throw new ServiceException("Group with id = " + id + " not found");
        }
        return id;
    }

    @Override
    public Group getGroup(Long id) {
        return groupsRepository.getById(id);
    }
    // TODO serviceException

    @Override
    public List<Group> getGroups() {
        return groupsRepository.getAll();
    }

}
