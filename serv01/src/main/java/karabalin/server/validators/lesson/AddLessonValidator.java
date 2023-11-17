package karabalin.server.validators.lesson;

import karabalin.server.requests.lesson.AddLessonRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class AddLessonValidator implements Validator<AddLessonRequest> {
    private final LongValidator longValidator;

    public AddLessonValidator(LongValidator longValidator) {
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(AddLessonRequest addLessonRequest) {
        var result = new ArrayList<String>();
        var teacherId = addLessonRequest.teacherId();
        var groupId = addLessonRequest.groupId();
        result.addAll(longValidator.validate(teacherId, "TeacherId"));
        result.addAll(longValidator.validate(groupId, "GroupId"));
        return result;
    }
}
