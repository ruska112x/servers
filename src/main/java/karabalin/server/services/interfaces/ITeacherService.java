package karabalin.server.services.interfaces;

import karabalin.server.entities.TeacherDTO;
import karabalin.server.exceptions.ServiceException;

import java.util.List;

public interface ITeacherService {
    long add(TeacherDTO teacher) throws ServiceException;
    void update(TeacherDTO teacher) throws ServiceException;
    void delete(long id) throws ServiceException;
    TeacherDTO get(long id) throws ServiceException;
    List<TeacherDTO> getAll() throws ServiceException;
}
