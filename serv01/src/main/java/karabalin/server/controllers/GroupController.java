package karabalin.server.controllers;

import karabalin.server.responses.CommonResponse;
import karabalin.server.services.GroupService;
import karabalin.server.entities.Group;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;
import karabalin.server.validators.group.EditStudentGroupValidator;

import java.util.ArrayList;
import java.util.List;

public class GroupController {

    private GroupService groupService;
    private AddStudentGroupValidator addStudentGroupValidator;

    private EditStudentGroupValidator editStudentGroupValidator;
    private IdRequestValidator idRequestValidator;

    public GroupController(GroupService groupService, AddStudentGroupValidator addStudentGroupValidator, EditStudentGroupValidator editStudentGroupValidator, IdRequestValidator idRequestValidator) {
        this.groupService = groupService;
        this.addStudentGroupValidator = addStudentGroupValidator;
        this.editStudentGroupValidator = editStudentGroupValidator;
        this.idRequestValidator = idRequestValidator;
    }

    public ResponseEntity<CommonResponse<List<Group>>> getStudentGroups() {
        var problems = new ArrayList<String>();
        long status = 200L;
        CommonResponse<List<Group>> response;
        try {
            response = new CommonResponse<>(groupService.getGroups());
        } catch (Exception e) {
            status = 422L;
            response = new CommonResponse<>(e.getMessage());
        }
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<CommonResponse<Group>> getStudentGroupById(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<Group> response;
        if (problems.isEmpty()) {
            try {
                response = new CommonResponse<>(groupService.getGroup(idRequest.getId()));
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

    public ResponseEntity<CommonResponse<Long>> addStudentGroup(AddStudentGroupRequest addStudentGroupRequest) {
        var problems = addStudentGroupValidator.validate(addStudentGroupRequest);
        long status = 201L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                response = new CommonResponse<>(groupService.addGroup(addStudentGroupRequest.getName()));
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

    public ResponseEntity<CommonResponse<Long>> editStudentGroup(EditStudentGroupRequest editStudentGroupRequest) {
        var problems = editStudentGroupValidator.validate(editStudentGroupRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                response = new CommonResponse<>(editStudentGroupRequest.getId());
                groupService.updateGroup(new Group(editStudentGroupRequest.getId(), editStudentGroupRequest.getName()));
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

    public ResponseEntity<CommonResponse<Long>> deleteStudentGroup(IdRequest idRequest) {
        var problems = idRequestValidator.validate(idRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {
            try {
                response = new CommonResponse<>(groupService.deleteGroup(idRequest.getId()));
            } catch (Exception e) {
                response = new CommonResponse<>(e.getMessage());
            }
        } else {
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, status);
    }
}
