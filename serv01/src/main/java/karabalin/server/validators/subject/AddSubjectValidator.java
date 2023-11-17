package karabalin.server.validators.subject;

import karabalin.server.requests.subject.AddSubjectRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class AddSubjectValidator implements Validator<AddSubjectRequest> {

    private final StringValidator stringValidator;

    public AddSubjectValidator(StringValidator stringValidator) {
        this.stringValidator = stringValidator;
    }

    @Override
    public List<String> validate(AddSubjectRequest addSubjectRequest) {
        return new ArrayList<String>(stringValidator.validate(addSubjectRequest.name(), "SubjectName"));
    }
}
