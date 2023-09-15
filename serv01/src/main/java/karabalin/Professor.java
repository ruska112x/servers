package karabalin;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Professor extends Human {
    public Professor(Integer id, String patronymic, String surname, String name) {
        super(id, patronymic, surname, name);
    }
}
