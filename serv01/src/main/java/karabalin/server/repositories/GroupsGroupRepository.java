package karabalin.server.repositories;

import karabalin.server.entities.Group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupsGroupRepository implements GroupRepository<Group, List<Group>> {


    // TODO interface
    private Map<Long, Group> repo;

    private Long currentId = 1L;

    public GroupsGroupRepository(DataBase dataBase) {
        this.repo = dataBase.getGroupsTable();
    }

    @Override
    public Long add(Group group) {
        repo.put(currentId, new Group(currentId, group.getName()));
        return currentId++;
    }

    @Override
    public void update(Group group) {
        repo.put(group.getId(), group);
    }

    @Override
    public void deleteById(Long id) {
        repo.remove(id);
    }

    @Override
    public Group getById(Long id) {
        return repo.get(id);
    }

    @Override
    public List<Group> getAll() {
        return repo.values().stream().toList();
    }
}
