package karabalin.repositories;

import karabalin.entities.Group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupsRepository implements Repository<Group, List<Group>> {

    private final Map<Long, Group> repo;

    private Long currentId = 1L;

    public GroupsRepository() {
        repo = new HashMap<>();
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
