package karabalin.server.controllers;

import karabalin.server.entities.SubjectDTO;
import karabalin.server.entities.TeacherDTO;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.subject.AddSubjectRequest;
import karabalin.server.requests.subject.EditSubjectRequest;
import karabalin.server.requests.teacher.AddTeacherRequest;
import karabalin.server.requests.teacher.EditTeacherRequest;
import karabalin.server.responses.CommonResponse;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.services.interfaces.ITeacherService;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.teacher.AddTeacherValidator;
import karabalin.server.validators.teacher.EditTeacherValidator;

import java.util.List;

public class TeacherController {
    private final ITeacherService teacherService;
    private final AddTeacherValidator addTeacherValidator;
    private final EditTeacherValidator editTeacherValidator;

    private final IdRequestValidator idRequestValidator;

    public TeacherController(
            ITeacherService teacherService,
            AddTeacherValidator addTeacherValidator,
            EditTeacherValidator editTeacherValidator,
            IdRequestValidator idRequestValidator
    ) {
        this.teacherService = teacherService;
        this.addTeacherValidator = addTeacherValidator;
        this.editTeacherValidator = editTeacherValidator;
        this.idRequestValidator = idRequestValidator;
    }

    public ResponseEntity<CommonResponse<List<TeacherDTO>>> getTeachers() {
        long status = 200L;
        CommonResponse<List<TeacherDTO>> response;
        try {
            var subjectList = teacherService.getAll();
            response = new CommonResponse<>(subjectList);
        } catch (Exception e) {
            status = 422L;
            response = new CommonResponse<>(e.getMessage());
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<TeacherDTO>> getTeacherById(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<TeacherDTO> response;
        if (problems.isEmpty()) {
            try {
                var subject = teacherService.get(idRequest.id());
                response = new CommonResponse<>(subject);
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

    public ResponseEntity<CommonResponse<Long>> addTeacher(AddTeacherRequest addTeacherRequest) {
        var problems = addTeacherValidator.validate(addTeacherRequest);
        long status = 201L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var id = teacherService.add(new TeacherDTO(null, addTeacherRequest.surname(), addTeacherRequest.name(), addTeacherRequest.patronymic()));
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

    public ResponseEntity<CommonResponse<Long>> editTeacher(EditTeacherRequest editTeacherRequest) {
        var problems = editTeacherValidator.validate(editTeacherRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                teacherService.update(new TeacherDTO(editTeacherRequest.id(), editTeacherRequest.surname(), editTeacherRequest.name(), editTeacherRequest.patronymic()));
                response = new CommonResponse<>(editTeacherRequest.id());
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

    public ResponseEntity<CommonResponse<Long>> deleteTeacher(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                teacherService.delete(idRequest.id());
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
}
