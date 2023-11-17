package karabalin.server.validators.lesson;

import karabalin.server.requests.lesson.GetLessonsByGroupRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class GetLessonsByGroupValidator implements Validator<GetLessonsByGroupRequest> {
    private final LongValidator longValidator;

    public GetLessonsByGroupValidator(LongValidator longValidator) {
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(GetLessonsByGroupRequest getLessonsByGroupRequest) {
        var groupId = getLessonsByGroupRequest.groupId();
        return new ArrayList<>(longValidator.validate(groupId, "GroupId"));
    }
}
