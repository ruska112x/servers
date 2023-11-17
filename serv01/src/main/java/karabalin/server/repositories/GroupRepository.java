package karabalin.server.repositories;

import karabalin.server.entities.Group;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.repositories.interfaces.IGroupRepository;

import java.util.*;

public class GroupRepository implements IGroupRepository {
    private final Map<Long, Group> groupMap;

    public GroupRepository(DataBase dataBase) {
        this.groupMap = dataBase.groupsTable();
    }

    @Override
    public long add(Group group) {
        long currentId = !groupMap.isEmpty() ? Collections.max(groupMap.keySet()) + 1 : 1;
        groupMap.put(currentId, new Group(currentId, group.getName()));
        return currentId;
    }

    @Override
    public Long update(Group group) throws RepositoryException {
        if (groupMap.containsKey(group.getId())) {
            groupMap.put(group.getId(), group);
            return group.getId();
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        groupMap.remove(id);
    }

    @Override
    public Group getById(long id) {
        return groupMap.getOrDefault(id, null);
    }

    @Override
    public List<Group> getAll() {
        return groupMap.values().stream().toList();
    }
}
