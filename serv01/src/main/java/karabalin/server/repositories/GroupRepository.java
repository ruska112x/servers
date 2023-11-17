package karabalin.server.repositories;

import karabalin.server.entities.GroupDTO;
import karabalin.server.repositories.dbentities.GroupDB;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.repositories.interfaces.IGroupRepository;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class GroupRepository implements IGroupRepository {
    private final Map<Long, GroupDB> groupMap;

    public GroupRepository(DataBase dataBase) {
        this.groupMap = dataBase.groupsTable();
    }

    @Override
    public long add(GroupDTO group) {
        long currentId = !groupMap.isEmpty() ? Collections.max(groupMap.keySet()) + 1 : 1;
        groupMap.put(currentId, new GroupDB(group.name()));
        return currentId;
    }

    @Override
    public Long update(GroupDTO group) throws RepositoryException {
        Long groupId = group.id();
        if (groupMap.containsKey(groupId)) {
            groupMap.put(groupId, new GroupDB(group.name()));
            return groupId;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        groupMap.remove(id);
    }

    @Override
    public GroupDTO getById(long id) {
        var group = groupMap.getOrDefault(id, null);
        if (group == null) {
            return null;
        } else {
            return new GroupDTO(id, group.name());
        }
    }

    @Override
    public List<GroupDTO> getAll() {
        return groupMap.entrySet().stream().map(entry -> new GroupDTO(entry.getKey(), entry.getValue().name())).collect(Collectors.toList());
    }
}
