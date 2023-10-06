package karabalin.server.controllers;

import karabalin.server.Service;
import karabalin.server.entities.Group;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;

import java.util.List;

public class GroupController {

    private Service service;
    private AddStudentGroupValidator addStudentGroupValidator;
    private IdRequestValidator idRequestValidator;

    public GroupController(Service service) {
        this.service = service;
        addStudentGroupValidator = new AddStudentGroupValidator();
        idRequestValidator = new IdRequestValidator();
    }

    public ResponseEntity<List<Group>> getStudentGroups() {
        return null;
    }

    public ResponseEntity<Group> getStudentGroupById(IdRequest idRequest) {
        ResponseEntity<Group> response;
        if (idRequestValidator.validate(idRequest).isEmpty()) {
            response = new ResponseEntity<>(service.getGroup(idRequest.getId()));
        } else {
            response = new ResponseEntity<>(null, 500L);
        }
        return response;
    }

    public ResponseEntity<Long> addStudentGroup(AddStudentGroupRequest addStudentGroupRequest) {
        ResponseEntity<Long> response;
        if (addStudentGroupValidator.validate(addStudentGroupRequest).isEmpty()) {
            response = new ResponseEntity<>(service.addGroup(addStudentGroupRequest.getName()));
        } else {
            response = new ResponseEntity<>(null, 500L);
        }
        return response;
    }

    public ResponseEntity<Long> editStudentGroup(EditStudentGroupRequest editStudentGroupRequest) {
        return null;
    }

    public ResponseEntity<Long> deleteStudentGroup(IdRequest idRequest) {
        return null;
    }
}
