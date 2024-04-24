package karabalin.server.validators.lesson;

import karabalin.server.requests.lesson.EditLessonRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class EditLessonValidator implements Validator<EditLessonRequest> {
    private final LongValidator longValidator;

    public EditLessonValidator(LongValidator longValidator) {
        this.longValidator = longValidator;
    }


    @Override
    public List<String> validate(EditLessonRequest editLessonRequest) {
        var result = new ArrayList<String>();
        var id = editLessonRequest.id();
        var teacherId = editLessonRequest.teacherId();
        var groupId = editLessonRequest.groupId();
        result.addAll(longValidator.validate(id, "Id"));
        result.addAll(longValidator.validate(teacherId, "TeacherId"));
        result.addAll(longValidator.validate(groupId, "GroupId"));
        return result;
    }
}
