package karabalin.server.validators.teacher;

import karabalin.server.requests.teacher.AddTeacherRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddTeacherValidator implements Validator<AddTeacherRequest> {
    private final StringValidator stringValidator;

    public AddTeacherValidator(StringValidator stringValidator) {
        this.stringValidator = stringValidator;
    }

    @Override
    public List<String> validate(AddTeacherRequest addTeacherRequest) {
        var result = new ArrayList<String>();
        var surname = addTeacherRequest.surname();
        var name = addTeacherRequest.name();
        var patronymic = addTeacherRequest.patronymic();
        result.addAll(stringValidator.validate(surname, "TeacherSurname"));
        result.addAll(stringValidator.validate(name, "TeacherName"));
        result.addAll(stringValidator.validate(patronymic, "TeacherPatronymic"));
        return result;
    }
}
