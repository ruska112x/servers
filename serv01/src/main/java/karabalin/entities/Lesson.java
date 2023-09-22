package karabalin.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Lesson {
    private Long id;
    private LocalDate date;
    private int numberInSchedule;
    private Teacher teacher;
    private Group group;

    public Lesson(Long id, LocalDate date, int numberInSchedule, Teacher teacher, Group group) {
        this.id = id;
        this.date = date;
        this.numberInSchedule = numberInSchedule;
        this.teacher = teacher;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNumberInSchedule() {
        return numberInSchedule;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", date=" + date +
                ", numberInSchedule=" + numberInSchedule +
                ", teacher=" + teacher +
                ", group=" + group +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return numberInSchedule == lesson.numberInSchedule && Objects.equals(id, lesson.id) && Objects.equals(date, lesson.date) && Objects.equals(teacher, lesson.teacher) && Objects.equals(group, lesson.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, numberInSchedule, teacher, group);
    }
}
