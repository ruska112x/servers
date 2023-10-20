package karabalin.server.services;

import karabalin.server.entities.Student;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.exceptions.ServiceException;
import karabalin.server.repositories.interfaces.IGroupRepository;
import karabalin.server.repositories.interfaces.IStudentRepository;
import karabalin.server.services.interfaces.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public long addStudent(Student student) throws ServiceException {
        try {
            return studentRepository.add(student);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateStudent(Student student) throws ServiceException {
        try {
            if (studentRepository.update(student) == null) {
                throw new ServiceException("Group with id = " + student.getId() + " not found!");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteStudent(long id) throws ServiceException {
        try {
            studentRepository.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Student getStudent(long id) throws ServiceException {
        try {
            studentRepository.getById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Student> getStudentsByGroupId(long groupId) throws ServiceException {
        try {
            var reslut = studentRepository.getStudentsByGroupId(groupId);
            if (reslut == null) {
                throw new ServiceException("Group with id = " + groupId + " not found!");
            } else {
                return reslut;
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
