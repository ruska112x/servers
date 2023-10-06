package karabalin.server;

import karabalin.server.entities.Group;
import karabalin.server.repositories.GroupsRepository;

import java.util.List;

public class Service {
    private GroupsRepository groupsRepository;

    public Service() {
        groupsRepository = new GroupsRepository();
    }

    public Long addGroup(String name) {
        return groupsRepository.add(new Group(null, name));
    }

    public Group getGroup(Long id) {
        return groupsRepository.getById(id);
    }
 // TODO serviceException, interface
    public List<Group> getGroups() {
        return groupsRepository.getAll();
    }

}
