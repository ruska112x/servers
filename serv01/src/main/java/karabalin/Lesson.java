package karabalin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private Integer id;
    private LocalDate date;
    private int numberInSchedule;
    private Professor professor;
    private Group group;
}
