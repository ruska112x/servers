package karabalin.server.validators.student;

import karabalin.server.requests.student.EditStudentRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.LongValidator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class EditStudentValidator implements Validator<EditStudentRequest> {
    private final StringValidator stringValidator;
    private final LongValidator longValidator;

    public EditStudentValidator(StringValidator stringValidator, LongValidator longValidator) {
        this.stringValidator = stringValidator;
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(EditStudentRequest editStudentRequest) {
        var result = new ArrayList<String>();
        var surname = editStudentRequest.surname();
        var name = editStudentRequest.name();
        var patronymic = editStudentRequest.patronymic();
        var groupId = editStudentRequest.groupId();
        var status = editStudentRequest.status();
        result.addAll(stringValidator.validate(surname, "LastName"));
        result.addAll(stringValidator.validate(name, "FirstName"));
        result.addAll(stringValidator.validate(patronymic, "MiddleName"));
        result.addAll(longValidator.validate(groupId, "GroupId"));
        result.addAll(stringValidator.validate(status, "Status"));
        return result;
    }
}
