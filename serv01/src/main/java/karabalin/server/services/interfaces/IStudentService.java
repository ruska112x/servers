package karabalin.server.services.interfaces;

import karabalin.server.entities.Student;
import karabalin.server.exceptions.ServiceException;

import java.util.List;

public interface IStudentService {
    long addStudent(Student student) throws ServiceException;

    void updateStudent(Student student) throws ServiceException;

    void deleteStudent(long id) throws ServiceException;

    Student getStudent(long id) throws ServiceException;

    List<Student> getStudentsByGroupId(long groupId) throws ServiceException;
}
