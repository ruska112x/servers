package karabalin.server.validators.teacher;

import karabalin.server.requests.teacher.EditTeacherRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.LongValidator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class EditTeacherValidator implements Validator<EditTeacherRequest> {
    private final StringValidator stringValidator;
    private final LongValidator longValidator;

    public EditTeacherValidator(StringValidator stringValidator, LongValidator longValidator) {
        this.stringValidator = stringValidator;
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(EditTeacherRequest editTeacherRequest) {
        var result = new ArrayList<String>();
        var id = editTeacherRequest.id();
        var surname = editTeacherRequest.surname();
        var name = editTeacherRequest.name();
        var patronymic = editTeacherRequest.patronymic();
        result.addAll(longValidator.validate(id, "TeacherId"));
        result.addAll(stringValidator.validate(surname, "TeacherSurname"));
        result.addAll(stringValidator.validate(name, "TeacherName"));
        result.addAll(stringValidator.validate(patronymic, "TeacherPatronymic"));
        return result;
    }
}
