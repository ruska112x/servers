package karabalin.server.validators.group;

import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddStudentGroupValidator implements Validator<AddStudentGroupRequest> {
    @Override
    public List<String> validate(AddStudentGroupRequest addStudentGroupRequest) {
        var result = new ArrayList<String>();
        var name = addStudentGroupRequest.getName();
        if (!StringValidator.notNull(name)) {
            result.add("Name is null!");
        }
        if (!StringValidator.notEmpty(name)) {
            result.add("Name is empty!");
        }
        if (!StringValidator.lessThan(name, 255)) {
            result.add("Name is longer than!");
        }
        return result;
    }
}
