package karabalin.server.validators.lesson;

import karabalin.server.requests.lesson.GetLessonsByTeacherRequest;
import karabalin.server.validators.Validator;
import karabalin.server.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class GetLessonByTeacherValidator implements Validator<GetLessonsByTeacherRequest> {
    private final LongValidator longValidator;

    public GetLessonByTeacherValidator(LongValidator longValidator) {
        this.longValidator = longValidator;
    }

    @Override
    public List<String> validate(GetLessonsByTeacherRequest getLessonsByTeacherRequest) {
        var teacherId = getLessonsByTeacherRequest.teacherId();
        return new ArrayList<>(longValidator.validate(teacherId, "TeacherId"));
    }
}
