package karabalin.server.services.interfaces;

import karabalin.server.entities.LessonDTO;
import karabalin.server.exceptions.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface ILessonService {
    long add(LessonDTO lesson) throws ServiceException;
    void update(LessonDTO lesson) throws ServiceException;
    void delete(long id) throws ServiceException;
    LessonDTO get(long id) throws ServiceException;
    List<LessonDTO> getLessonsByGroup(LocalDate start, LocalDate end, long id) throws ServiceException;
    List<LessonDTO> getLessonsByTeacher(LocalDate start, LocalDate end, long id) throws ServiceException;
}
