package karabalin.server.repositories;

import karabalin.server.entities.SubjectDTO;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.repositories.dbentities.SubjectDB;
import karabalin.server.repositories.interfaces.ISubjectRepository;

import java.util.List;
import java.util.Map;

public class SubjectRepository  implements ISubjectRepository {
    private final Map<Long, SubjectDB> subjectMap;

    public SubjectRepository(Map<Long, SubjectDB> subjectMap) {
        this.subjectMap = subjectMap;
    }

    @Override
    public long add(SubjectDTO subject) throws RepositoryException {

    }

    @Override
    public Long update(SubjectDTO subject) throws RepositoryException {

    }

    @Override
    public void deleteById(long id) throws RepositoryException {

    }

    @Override
    public SubjectDTO getById(long id) throws RepositoryException {

    }

    @Override
    public List<SubjectDTO> getAll() throws RepositoryException {

    }
}
