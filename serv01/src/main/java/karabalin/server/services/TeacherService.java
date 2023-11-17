package karabalin.server.services;

import karabalin.server.entities.TeacherDTO;
import karabalin.server.exceptions.RepositoryException;
import karabalin.server.exceptions.ServiceException;
import karabalin.server.repositories.interfaces.ITeacherRepository;
import karabalin.server.services.interfaces.ITeacherService;

import java.util.List;

public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;

    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public long add(TeacherDTO teacher) throws ServiceException {
        try {
            return teacherRepository.add(teacher);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(TeacherDTO teacher) throws ServiceException {
        try {
            if (teacherRepository.update(teacher) == null) {
                throw new ServiceException("Teacher with id = " + teacher.id() + " not found!");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            teacherRepository.deleteById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public TeacherDTO get(long id) throws ServiceException {
        try {
            var result = teacherRepository.getById(id);
            if (result == null) {
                throw new ServiceException("Teacher with id = " + id + " not found!");
            }
            return result;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<TeacherDTO> getAll() throws ServiceException {
        try {
            return teacherRepository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
