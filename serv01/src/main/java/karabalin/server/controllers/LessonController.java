package karabalin.server.controllers;

import karabalin.server.entities.LessonDTO;
import karabalin.server.entities.TeacherDTO;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.lesson.AddLessonRequest;
import karabalin.server.requests.lesson.EditLessonRequest;
import karabalin.server.requests.lesson.GetLessonsByGroupRequest;
import karabalin.server.requests.lesson.GetLessonsByTeacherRequest;
import karabalin.server.requests.teacher.AddTeacherRequest;
import karabalin.server.requests.teacher.EditTeacherRequest;
import karabalin.server.responses.CommonResponse;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.services.interfaces.IGroupService;
import karabalin.server.services.interfaces.ILessonService;
import karabalin.server.services.interfaces.ITeacherService;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.lesson.AddLessonValidator;
import karabalin.server.validators.lesson.EditLessonValidator;
import karabalin.server.validators.lesson.GetLessonByTeacherValidator;
import karabalin.server.validators.lesson.GetLessonsByGroupValidator;

import java.util.List;

public class LessonController {
    private final ILessonService lessonService;

    private final IGroupService groupService;

    private final ITeacherService teacherService;

    private final AddLessonValidator addLessonValidator;
    private final EditLessonValidator editLessonValidator;
    private final GetLessonByTeacherValidator getLessonByTeacherValidator;
    private final GetLessonsByGroupValidator getLessonsByGroupValidator;

    private final IdRequestValidator idRequestValidator;

    public LessonController(
            ILessonService lessonService,
            IGroupService groupService,
            ITeacherService teacherService,
            AddLessonValidator addLessonValidator,
            EditLessonValidator editLessonValidator,
            GetLessonByTeacherValidator getLessonByTeacherValidator,
            GetLessonsByGroupValidator getLessonsByGroupValidator,
            IdRequestValidator idRequestValidator
    ) {
        this.lessonService = lessonService;
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.addLessonValidator = addLessonValidator;
        this.editLessonValidator = editLessonValidator;
        this.getLessonByTeacherValidator = getLessonByTeacherValidator;
        this.getLessonsByGroupValidator = getLessonsByGroupValidator;
        this.idRequestValidator = idRequestValidator;
    }

    public ResponseEntity<CommonResponse<Long>> addLesson(AddLessonRequest addLessonRequest) {
        var problems = addLessonValidator.validate(addLessonRequest);
        long status = 201L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var teacher = teacherService.get(addLessonRequest.teacherId());
                var group = groupService.getGroup(addLessonRequest.groupId());
                var id = lessonService.add(new LessonDTO(null, addLessonRequest.date(), addLessonRequest.numberInSchedule(), teacher, group));
                response = new CommonResponse<>(id);
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<Long>> editLesson(EditLessonRequest editLessonRequest) {
        var problems = editLessonValidator.validate(editLessonRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var teacher = teacherService.get(editLessonRequest.teacherId());
                var group = groupService.getGroup(editLessonRequest.groupId());
                lessonService.update(new LessonDTO(editLessonRequest.id(), editLessonRequest.date(), editLessonRequest.numberInSchedule(), teacher, group));
                response = new CommonResponse<>(editLessonRequest.id());
            } catch (Exception e) {
                status = 404L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<Long>> deleteLesson(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                lessonService.delete(idRequest.id());
                response = new CommonResponse<>(idRequest.id());
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<LessonDTO>> getLessonById(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status= 200L;
        CommonResponse<LessonDTO> response;
        if (problems.isEmpty()) {
            try {
                var lesson = lessonService.get(idRequest.id());
                response = new CommonResponse<>(lesson);
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<List<LessonDTO>>> getLessonsByGroup(GetLessonsByGroupRequest getLessonsByGroupRequest) {
        var problems = getLessonsByGroupValidator.validate(getLessonsByGroupRequest);
        long status= 200L;
        CommonResponse<List<LessonDTO>> response;
        if (problems.isEmpty()) {
            try {
                var lessons = lessonService.getLessonsByGroup(getLessonsByGroupRequest.start(), getLessonsByGroupRequest.end(), getLessonsByGroupRequest.groupId());
                response = new CommonResponse<>(lessons);
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<List<LessonDTO>>> getLessonsByTeacher(GetLessonsByTeacherRequest getLessonsByTeacherRequest) {
        var problems = getLessonByTeacherValidator.validate(getLessonsByTeacherRequest);
        long status= 200L;
        CommonResponse<List<LessonDTO>> response;
        if (problems.isEmpty()) {
            try {
                var lessons = lessonService.getLessonsByTeacher(getLessonsByTeacherRequest.start(), getLessonsByTeacherRequest.end(), getLessonsByTeacherRequest.teacherId());
                response = new CommonResponse<>(lessons);
            } catch (Exception e) {
                status = 422L;
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            status = 422L;
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }
}
