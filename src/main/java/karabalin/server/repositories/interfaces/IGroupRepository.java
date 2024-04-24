package karabalin.server.repositories.interfaces;

import karabalin.server.entities.GroupDTO;
import karabalin.server.exceptions.RepositoryException;

import java.util.List;

public interface IGroupRepository {

    long add(GroupDTO t) throws RepositoryException;

    Long update(GroupDTO t) throws RepositoryException;
    void deleteById(long id) throws RepositoryException;
    GroupDTO getById(long id) throws RepositoryException;

    List<GroupDTO> getAll() throws RepositoryException;
}
