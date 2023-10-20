package karabalin.server.controllers;

import karabalin.server.entities.Student;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.student.AddStudentRequest;
import karabalin.server.requests.student.EditStudentRequest;
import karabalin.server.responses.CommonResponse;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.validators.IdRequestValidator;

import java.util.List;

public class StudentController {

    private StudentService studentService;
    private IdRequestValidator idRequestValidator;

    public ResponseEntity<CommonResponse<List<Student>>> getStudentsByGroup(IdRequest idRequest) {

    }

    public ResponseEntity<CommonResponse<Student>> getStudentById(IdRequest idRequest) {
        return null;
    }

    public ResponseEntity<CommonResponse<Long>> addStudent(AddStudentRequest addStudentRequest) {
        return null;
    }

    public ResponseEntity<CommonResponse<Long>> editStudent(EditStudentRequest editStudentRequest) {
        return null;
    }

    public ResponseEntity<CommonResponse<Long>> deleteStudent(IdRequest idRequest) {
        return null;
    }
}
