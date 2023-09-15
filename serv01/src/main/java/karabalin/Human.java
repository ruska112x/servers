package karabalin;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Human {
    protected Integer id;
    protected String patronymic;
    protected String surname;
    protected String name;
}
