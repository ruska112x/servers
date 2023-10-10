package karabalin.server.validators.group;

import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddStudentGroupValidator implements Validator<AddStudentGroupRequest> {

    private StringValidator stringValidator;

    public AddStudentGroupValidator(StringValidator stringValidator) {
        this.stringValidator = stringValidator;
    }

    @Override
    public List<String> validate(AddStudentGroupRequest addStudentGroupRequest) {
        var result = new ArrayList<String>();
        var name = addStudentGroupRequest.getName();
        if (!stringValidator.notNull(name)) {
            result.add("Name is null!");
        }
        if (!stringValidator.notEmpty(name)) {
            result.add("Name is empty!");
        }
        if (!stringValidator.lessThan(name, 255)) {
            result.add("Name is longer than!");
        }
        return result;
    }
}
