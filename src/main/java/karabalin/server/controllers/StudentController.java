package karabalin.server.controllers;

import karabalin.server.entities.GroupDTO;
import karabalin.server.entities.StudentDTO;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.student.AddStudentRequest;
import karabalin.server.requests.student.EditStudentRequest;
import karabalin.server.responses.CommonResponse;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.services.StudentService;
import karabalin.server.services.interfaces.IGroupService;
import karabalin.server.services.interfaces.IStudentService;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.student.AddStudentValidator;
import karabalin.server.validators.student.EditStudentValidator;

import java.util.List;

public class StudentController {

    private final IStudentService studentService;
    private final IGroupService groupService;
    private final IdRequestValidator idRequestValidator;

    private final AddStudentValidator addStudentValidator;

    private final EditStudentValidator editStudentValidator;

    public StudentController(StudentService studentService, IGroupService groupService, IdRequestValidator idRequestValidator, AddStudentValidator addStudentValidator, EditStudentValidator editStudentValidator) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.idRequestValidator = idRequestValidator;
        this.addStudentValidator = addStudentValidator;
        this.editStudentValidator = editStudentValidator;
    }

    public ResponseEntity<CommonResponse<List<StudentDTO>>> getStudentsByGroupId(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<List<StudentDTO>> response;
        if (problems.isEmpty()) {
            try {
                var studentList = studentService.getStudentsByGroupId(idRequest.id());
                response = new CommonResponse<>(studentList);
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

    public ResponseEntity<CommonResponse<StudentDTO>> getStudentById(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<StudentDTO> response;
        if (problems.isEmpty()) {
            try {
                var student = studentService.getStudent(idRequest.id());
                response = new CommonResponse<>(student);
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

    public ResponseEntity<CommonResponse<Long>> addStudent(AddStudentRequest addStudentRequest) {
        var problems = addStudentValidator.validate(addStudentRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var group = groupService.getGroup(addStudentRequest.groupId());
                var id = studentService.addStudent(
                        new StudentDTO(
                                null,
                                addStudentRequest.surname(),
                                addStudentRequest.name(),
                                addStudentRequest.patronymic(),
                                addStudentRequest.status(),
                                group
                        )
                );
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

    public ResponseEntity<CommonResponse<Long>> editStudent(EditStudentRequest editStudentRequest) {
        var problems = editStudentValidator.validate(editStudentRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var group = groupService.getGroup(editStudentRequest.groupId());
                studentService.updateStudent(new StudentDTO(editStudentRequest.id(), editStudentRequest.surname(), editStudentRequest.name(), editStudentRequest.patronymic(), editStudentRequest.status(), group));
                response = new CommonResponse<>(editStudentRequest.id());
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

    public ResponseEntity<CommonResponse<Long>> deleteStudent(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                studentService.deleteStudent(idRequest.id());
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
