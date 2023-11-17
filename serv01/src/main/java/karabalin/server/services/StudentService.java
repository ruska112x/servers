package karabalin.server.services;

import karabalin.server.entities.StudentDTO;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.exceptions.ServiceException;
import karabalin.server.repositories.interfaces.IStudentRepository;
import karabalin.server.services.interfaces.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public long addStudent(StudentDTO student) throws ServiceException {
        try {
            return studentRepository.add(student);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateStudent(StudentDTO student) throws ServiceException {
        try {
            if (studentRepository.update(student) == null) {
                throw new ServiceException("Group with id = " + student.id() + " not found!");
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
    public StudentDTO getStudent(long id) throws ServiceException {
        try {
            var result = studentRepository.getById(id);
            if (result == null) {
                throw new ServiceException("Student with id = " + id + " not found!");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<StudentDTO> getStudentsByGroupId(long groupId) throws ServiceException {
        try {
            var result = studentRepository.getStudentsByGroupId(groupId);
            if (result == null) {
                throw new ServiceException("Group with id = " + groupId + " not found!");
            } else {
                return result;
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
