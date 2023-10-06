package karabalin.server.controllers;

import karabalin.server.services.GroupService;
import karabalin.server.entities.Group;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;

import java.util.List;

public class GroupController {

    private GroupService groupService;
    private AddStudentGroupValidator addStudentGroupValidator;
    private IdRequestValidator idRequestValidator;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
        addStudentGroupValidator = new AddStudentGroupValidator();
        idRequestValidator = new IdRequestValidator();
    }

    public ResponseEntity<List<Group>> getStudentGroups() {
        return new ResponseEntity<>(groupService.getGroups(), 200L);
    }

    public ResponseEntity<Group> getStudentGroupById(IdRequest idRequest) {
        ResponseEntity<Group> response;
        if (idRequestValidator.validate(idRequest).isEmpty()) {
            response = new ResponseEntity<>(groupService.getGroup(idRequest.getId()));
        } else {
            response = new ResponseEntity<>(null, 422L);
        }
        return response;
    }

    public ResponseEntity<Long> addStudentGroup(AddStudentGroupRequest addStudentGroupRequest) {
        ResponseEntity<Long> response;
        if (addStudentGroupValidator.validate(addStudentGroupRequest).isEmpty()) {
            response = new ResponseEntity<>(groupService.addGroup(addStudentGroupRequest.getName()));
        } else {
            response = new ResponseEntity<>(null, 422L);
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
