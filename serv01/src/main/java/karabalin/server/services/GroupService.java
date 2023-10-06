package karabalin.server.services;

import karabalin.server.entities.Group;
import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupsGroupRepository;

import java.util.List;

public class GroupService {
    private GroupsGroupRepository groupsRepository;

    public GroupService(DataBase dataBase) {
        groupsRepository = new GroupsGroupRepository(dataBase);
    }

    public Long addGroup(String name) {
        return groupsRepository.add(new Group(null, name));
    }

    public Group getGroup(Long id) {
        return groupsRepository.getById(id);
    }
 // TODO serviceException, interface to service and repository
    public List<Group> getGroups() {
        return groupsRepository.getAll();
    }

}
