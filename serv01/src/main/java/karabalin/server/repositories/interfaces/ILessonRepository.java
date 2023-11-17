package karabalin.server.repositories.interfaces;

import karabalin.server.entities.LessonDTO;
import karabalin.server.entities.TeacherDTO;
import karabalin.server.exceptions.RepositoryException;

import java.time.LocalDate;
import java.util.List;

public interface ILessonRepository {
    Long add(LessonDTO lesson) throws RepositoryException;
    Long update(LessonDTO lesson) throws RepositoryException;
    void deleteById(long id) throws RepositoryException;
    LessonDTO getById(long id) throws RepositoryException;
    List<LessonDTO> getLessonsByGroup(LocalDate start, LocalDate end, long id) throws RepositoryException;
    List<LessonDTO> getLessonsByTeacher(LocalDate start, LocalDate end, long id) throws RepositoryException;
}
