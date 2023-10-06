package karabalin.server.controllers;

import karabalin.server.exceptions.ServiceException;
import karabalin.server.services.GroupService;
import karabalin.server.entities.Group;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;
import karabalin.server.validators.group.EditStudentGroupValidator;

import java.util.List;

public class GroupController {

    private GroupService groupService;
    private AddStudentGroupValidator addStudentGroupValidator;

    private EditStudentGroupValidator editStudentGroupValidator;
    private IdRequestValidator idRequestValidator;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
        addStudentGroupValidator = new AddStudentGroupValidator();
        editStudentGroupValidator = new EditStudentGroupValidator();
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
        ResponseEntity<Long> response;
        if (editStudentGroupValidator.validate(editStudentGroupRequest).isEmpty()) {
            try {
                response = new ResponseEntity<>(groupService.updateGroup(new Group(editStudentGroupRequest.getId(),
                        editStudentGroupRequest.getName())).getId());
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        } else {
            response = new ResponseEntity<>(null, 422L);
        }
        return response;
    }

    public ResponseEntity<Long> deleteStudentGroup(IdRequest idRequest) {
        ResponseEntity<Long> response;
        if (idRequestValidator.validate(idRequest).isEmpty()) {
            try {
                response = new ResponseEntity<>(groupService.deleteGroup(idRequest.getId()));
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        } else {
            response = new ResponseEntity<>(null, 422L);
        }
        return null;
    }
}
