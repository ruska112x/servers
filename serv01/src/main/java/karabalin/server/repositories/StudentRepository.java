package karabalin.server.repositories;

import karabalin.server.entities.Group;
import karabalin.server.entities.Student;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.repositories.interfaces.IStudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StudentRepository implements IStudentRepository {

    private final Map<Long, Student> studentMap;
    private final Map<Long, Group> groupMap;

    public StudentRepository(DataBase dataBase) {
        studentMap = dataBase.studentsTable();
        groupMap = dataBase.groupsTable();
    }


    @Override
    public long add(Student student) throws RepositoryException {
        long currentId = !studentMap.isEmpty() ? Collections.max(studentMap.keySet()) + 1 : 1;
        studentMap.put(currentId, new Student(currentId, student.getPatronymic(), student.getSurname(), student.getName(), student.getStatus(), student.getGroup()));
        return currentId;
    }

    @Override
    public Long update(Student student) throws RepositoryException {
        if (studentMap.containsKey(student.getId())) {
            studentMap.put(student.getId(), student);
            return student.getId();
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) throws RepositoryException {
        studentMap.remove(id);
    }

    @Override
    public Student getById(long id) throws RepositoryException {
        return studentMap.getOrDefault(id, null);
    }

    @Override
    public List<Student> getStudentsByGroupId(long groupId) throws RepositoryException {
        if (groupMap.containsKey(groupId)) {
            return studentMap.values().stream().filter(x -> x.getGroup().getId() == groupId).toList();
        } else {
            return null;
        }
    }
}
