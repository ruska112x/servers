package karabalin.server.repositories;

import karabalin.server.entities.Group;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.repositories.interfaces.IGroupRepository;

import java.util.*;

public class GroupRepository implements IGroupRepository {
    private final Map<Long, Group> repo;

    public GroupRepository(DataBase dataBase) {
        this.repo = dataBase.getGroupsTable();
    }

    @Override
    public long add(Group group) {
        long currentId = !repo.isEmpty() ? Collections.max(repo.keySet()) + 1 : 1;
        repo.put(currentId, new Group(currentId, group.getName()));
        return currentId;
    }

    @Override
    public void update(Group group) throws RepositoryException {
        if (repo.containsKey(group.getId())) {
            repo.put(group.getId(), group);
        } else {
            throw new RepositoryException("Group with id=" + group.getId() + " not found in DB");
        }
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
