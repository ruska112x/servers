package karabalin.server.repositories;

import karabalin.server.entities.Group;
import karabalin.server.repositories.interfaces.IGroupRepository;

import java.util.List;
import java.util.Map;

public class GroupRepository implements IGroupRepository {
    private Map<Long, Group> repo;

    private Long currentId = 1L;

    public GroupRepository(DataBase dataBase) {
        this.repo = dataBase.getGroupsTable();
    }

    @Override
    public long add(Group group) {
        repo.put(currentId, new Group(currentId, group.getName()));
        return currentId++;
    }

    @Override
    public Group update(Group group) {
        return repo.put(group.getId(), group);
    }

    @Override
    public void deleteById(long id) {
        repo.remove(id);
    }

    @Override
    public Group getById(long id) {
        return repo.get(id);
    }

    @Override
    public List<Group> getAll() {
        return repo.values().stream().toList();
    }
}
