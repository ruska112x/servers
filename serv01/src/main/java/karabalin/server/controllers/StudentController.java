package karabalin.server.controllers;

import karabalin.server.entities.Group;
import karabalin.server.entities.Student;
import karabalin.server.entities.additional.StudentStatus;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.student.AddStudentRequest;
import karabalin.server.requests.student.EditStudentRequest;
import karabalin.server.responses.CommonResponse;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.services.StudentService;
import karabalin.server.services.interfaces.IStudentService;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;
import karabalin.server.validators.student.AddStudentValidator;

import java.util.List;

public class StudentController {

    private final IStudentService studentService;
    private final IdRequestValidator idRequestValidator;

    private final AddStudentValidator addStudentValidator;

    public StudentController(StudentService studentService, IdRequestValidator idRequestValidator, AddStudentValidator addStudentValidator) {
        this.studentService = studentService;
        this.idRequestValidator = idRequestValidator;
        this.addStudentValidator = addStudentValidator;
    }

    public ResponseEntity<CommonResponse<List<Student>>> getStudentsByGroupId(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<List<Student>> response;
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

    public ResponseEntity<CommonResponse<Student>> getStudentById(IdRequest idRequest) {
        return null;
    }

    public ResponseEntity<CommonResponse<Long>> addStudent(AddStudentRequest addStudentRequest) {
        var problems = addStudentValidator.validate(addStudentRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                var id = studentService.addStudent(new Student(null, addStudentRequest.patronymic(), addStudentRequest.surname(), addStudentRequest.name(), StudentStatus.getStatusByString(addStudentRequest.status()), new Group(addStudentRequest.groupId(), "")));
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
        return null;
    }

    public ResponseEntity<CommonResponse<Long>> deleteStudent(IdRequest idRequest) {
        return null;
    }
}
