package karabalin;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends Human {
    private StudentStatus status;
    private Group group;

    public Student(Integer id, String patronymic, String surname, String name, StudentStatus status, Group group) {
        super(id, patronymic, surname, name);
        this.status = status;
        this.group = group;
    }
}
