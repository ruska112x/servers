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
        var lastName = editStudentRequest.lastName();
        var firstName = editStudentRequest.firstName();
        var middleName = editStudentRequest.middleName();
        var groupId = editStudentRequest.groupId();
        var status = editStudentRequest.status();
        result.addAll(stringValidator.validate(lastName, "LastName"));
        result.addAll(stringValidator.validate(firstName, "FirstName"));
        result.addAll(stringValidator.validate(middleName, "MiddleName"));
        result.addAll(longValidator.validate(groupId, "GroupId"));
        result.addAll(stringValidator.validate(status, "Status"));
        return result;
    }
}
