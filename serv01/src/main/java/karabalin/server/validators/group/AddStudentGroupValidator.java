package karabalin.server.validators.group;

import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddStudentGroupValidator implements Validator<AddStudentGroupRequest> {

    private final StringValidator stringValidator;

    public AddStudentGroupValidator(StringValidator stringValidator) {
        this.stringValidator = stringValidator;
    }

    @Override
    public List<String> validate(AddStudentGroupRequest addStudentGroupRequest) {
        var name = addStudentGroupRequest.getName();
        return new ArrayList<String>(stringValidator.validate(name, "Name"));
    }
}
