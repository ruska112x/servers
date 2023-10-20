package karabalin.server.validators.student;

import karabalin.server.requests.student.AddStudentRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.LongValidator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddStudentValidator implements Validator<AddStudentRequest> {

    private final StringValidator stringValidator;
    private final LongValidator longValidator;

    public AddStudentValidator(StringValidator stringValidator, LongValidator longValidator) {
        this.stringValidator = stringValidator;
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(AddStudentRequest addStudentRequest) {
        var result = new ArrayList<String>();
        var surname = addStudentRequest.surname();
        var name = addStudentRequest.name();
        var patronymic = addStudentRequest.patronymic();
        var groupId = addStudentRequest.groupId();
        var status = addStudentRequest.status();
        result.addAll(stringValidator.validate(surname, "Surname"));
        result.addAll(stringValidator.validate(name, "name"));
        result.addAll(stringValidator.validate(patronymic, "Patronymic"));
        result.addAll(longValidator.validate(groupId, "GroupId"));
        result.addAll(stringValidator.validate(status, "Status"));
        return result;
    }
}
