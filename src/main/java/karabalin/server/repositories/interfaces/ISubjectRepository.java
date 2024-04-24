package karabalin.server.repositories.interfaces;

import karabalin.server.entities.SubjectDTO;
import karabalin.server.exceptions.RepositoryException;

import java.util.List;

public interface ISubjectRepository {
    long add(SubjectDTO subject) throws RepositoryException;
    Long update(SubjectDTO subject) throws RepositoryException;

    void deleteById(long id) throws RepositoryException;

    SubjectDTO getById(long id) throws RepositoryException;

    List<SubjectDTO> getAll() throws RepositoryException;
}
