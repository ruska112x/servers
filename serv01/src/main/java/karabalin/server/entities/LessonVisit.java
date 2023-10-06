package karabalin.server.entities;

import java.util.List;
import java.util.Objects;

public class LessonVisit {
    private Long id;
    private Lesson lesson;
    private List<Student> presentStudents;

    public LessonVisit(Long id, Lesson lesson, List<Student> presentStudents) {
        this.id = id;
        this.lesson = lesson;
        this.presentStudents = presentStudents;
    }

    public Long getId() {
        return id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public List<Student> getPresentStudents() {
        return presentStudents;
    }

    @Override
    public String toString() {
        return "LessonVisit{" +
                "id=" + id +
                ", lesson=" + lesson +
                ", presentStudents=" + presentStudents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonVisit that = (LessonVisit) o;
        return Objects.equals(id, that.id) && Objects.equals(lesson, that.lesson) && Objects.equals(presentStudents, that.presentStudents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lesson, presentStudents);
    }
}
