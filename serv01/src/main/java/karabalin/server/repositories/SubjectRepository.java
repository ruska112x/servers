package karabalin.server.repositories;

import karabalin.server.entities.SubjectDTO;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.repositories.dbentities.SubjectDB;
import karabalin.server.repositories.interfaces.ISubjectRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubjectRepository  implements ISubjectRepository {
    private final Map<Long, SubjectDB> subjectMap;

    public SubjectRepository(Map<Long, SubjectDB> subjectMap) {
        this.subjectMap = subjectMap;
    }

    @Override
    public long add(SubjectDTO subject) throws RepositoryException {
        long currentId = !subjectMap.isEmpty() ? Collections.max(subjectMap.keySet()) + 1 : 1;
        subjectMap.put(currentId, new SubjectDB(subject.name()));
        return currentId;
    }

    @Override
    public Long update(SubjectDTO subject) throws RepositoryException {
        Long subjectId = subject.id();
        if (subjectMap.containsKey(subjectId)) {
            subjectMap.put(subjectId, new SubjectDB(subject.name()));
            return subjectId;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) throws RepositoryException {
        subjectMap.remove(id);
    }

    @Override
    public SubjectDTO getById(long id) throws RepositoryException {
        var subject = subjectMap.getOrDefault(id, null);
        if (subject == null) {
            return null;
        } else {
            return new SubjectDTO(id, subject.name());
        }
    }

    @Override
    public List<SubjectDTO> getAll() throws RepositoryException {
        return subjectMap
                .entrySet()
                .stream()
                .map(entry -> new SubjectDTO(entry.getKey(), entry.getValue().name()))
                .collect(Collectors.toList());
    }
}
