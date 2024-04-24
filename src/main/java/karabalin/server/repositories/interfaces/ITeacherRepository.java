package karabalin.server.repositories.interfaces;

import karabalin.server.entities.TeacherDTO;
import karabalin.server.exceptions.RepositoryException;

import java.util.List;

public interface ITeacherRepository {
    long add(TeacherDTO teacher) throws RepositoryException;
    Long update(TeacherDTO teacher) throws RepositoryException;
    void deleteById(long id) throws RepositoryException;
    TeacherDTO getById(long id) throws RepositoryException;
    List<TeacherDTO> getAll() throws RepositoryException;
}
