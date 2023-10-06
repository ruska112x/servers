package karabalin.server.repositories;

import karabalin.server.entities.Group;
import karabalin.server.repositories.interfaces.IGroupRepository;

import java.util.List;
import java.util.Map;

public class GroupRepository implements IGroupRepository<Long, Group, List<Group>> {
    private Map<Long, Group> repo;

    private Long currentId = 1L;

    public GroupRepository(DataBase dataBase) {
        this.repo = dataBase.getGroupsTable();
    }

    @Override
    public Long add(Group group) {
        repo.put(currentId, new Group(currentId, group.getName()));
        return currentId++;
    }

    @Override
    public Group update(Group group) {
        return repo.put(group.getId(), group);
    }

    @Override
    public Long deleteById(Long id) {
        repo.remove(id);
        return id;
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
