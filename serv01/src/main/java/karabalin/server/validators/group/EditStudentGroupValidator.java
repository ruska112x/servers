package karabalin.server.validators.group;

import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.LongValidator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class EditStudentGroupValidator implements Validator<EditStudentGroupRequest> {
    @Override
    public List<String> validate(EditStudentGroupRequest editStudentGroupRequest) {
        var result = new ArrayList<String>();
        var id = editStudentGroupRequest.getId();
        var name = editStudentGroupRequest.getName();
        if (!LongValidator.notNull(id)) {
            result.add("Id is null!");
        }
        if (!LongValidator.moreThanZero(id)) {
            result.add("Id is less than 0!");
        }
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
