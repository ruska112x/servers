package karabalin.server.controllers;

import karabalin.server.entities.Student;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.student.AddStudentRequest;
import karabalin.server.requests.student.EditStudentRequest;
import karabalin.server.responses.ResponseEntity;

import java.util.List;

public class StudentController {
    public ResponseEntity<List<Student>> getStudentsByGroup(IdRequest idRequest) {
        return null;
    }

    public ResponseEntity<Student> getStudentById(IdRequest idRequest) {
        return null;
    }

    public ResponseEntity<Long> addStudent(AddStudentRequest addStudentRequest) {
        return null;
    }

    public ResponseEntity<Long> editStudent(EditStudentRequest editStudentRequest) {
        return null;
    }

    public ResponseEntity<Long> deleteStudent(IdRequest idRequest) {
        return null;
    }
}
