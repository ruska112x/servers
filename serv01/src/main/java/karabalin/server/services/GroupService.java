package karabalin.server.services;

import karabalin.server.entities.Group;
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
    public Group updateGroup(Group group) {
        groupsRepository.update(group);
        return group;
    }

    @Override
    public void deleteGroup(Long id) {
        groupsRepository.deleteById(id);
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
