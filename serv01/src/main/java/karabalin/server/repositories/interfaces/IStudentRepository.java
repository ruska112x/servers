package karabalin.server.repositories.interfaces;

import karabalin.server.entities.Student;
import karabalin.server.exceptions.RepositoryException;

import java.util.List;

public interface IStudentRepository {
    long add(Student student) throws RepositoryException;

    Long update(Student student) throws RepositoryException;

    void deleteById(long id) throws RepositoryException;

    Student getById(long id) throws RepositoryException;

    List<Student> getStudentsByGroupId(long groupId) throws RepositoryException;
}
