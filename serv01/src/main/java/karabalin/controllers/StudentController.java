package karabalin.controllers;

import karabalin.entities.Student;
import karabalin.requests.IdRequest;
import karabalin.requests.student.AddStudentRequest;
import karabalin.requests.student.EditStudentRequest;
import karabalin.responses.ResponseEntity;

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
