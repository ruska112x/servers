package karabalin.server.repositories;

import karabalin.server.entities.TeacherDTO;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.repositories.dbentities.TeacherDB;
import karabalin.server.repositories.interfaces.ITeacherRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeacherRepository implements ITeacherRepository {
    private final Map<Long, TeacherDB> teacherMap;

    public TeacherRepository(DataBase dataBase) {
        this.teacherMap = dataBase.teachersTable();
    }

    @Override
    public long add(TeacherDTO teacher) throws RepositoryException {
        long currentId = !teacherMap.isEmpty() ? Collections.max(teacherMap.keySet()) + 1 : 1;
        teacherMap.put(currentId, new TeacherDB(teacher.surname(), teacher.name(), teacher.patronymic()));
        return currentId;
    }

    @Override
    public Long update(TeacherDTO teacher) throws RepositoryException {
        Long teacherId = teacher.id();
        if (teacherMap.containsKey(teacherId)) {
            teacherMap.put(teacherId, new TeacherDB(teacher.surname(), teacher.name(), teacher.patronymic()));
            return teacherId;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) throws RepositoryException {
        teacherMap.remove(id);
    }

    @Override
    public TeacherDTO getById(long id) throws RepositoryException {
        var teacher = teacherMap.getOrDefault(id, null);
        if (teacher == null) {
            return null;
        } else {
            return new TeacherDTO(id, teacher.surname(), teacher.name(), teacher.patronymic());
        }
    }

    @Override
    public List<TeacherDTO> getAll() throws RepositoryException {
        return teacherMap.entrySet().stream().map(entry -> {
            var teacher = entry.getValue();
            return new TeacherDTO(entry.getKey(), teacher.surname(), teacher.name(), teacher.patronymic());
        }).collect(Collectors.toList());
    }
}
