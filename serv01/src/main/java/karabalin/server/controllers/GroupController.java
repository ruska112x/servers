package karabalin.server.controllers;

import karabalin.server.exceptions.ServiceException;
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

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
        addStudentGroupValidator = new AddStudentGroupValidator();
        editStudentGroupValidator = new EditStudentGroupValidator();
        idRequestValidator = new IdRequestValidator();
    }

    public CommonResponse<List<Group>> getStudentGroups() {
        var flag = false;
        var problems = new ArrayList<String>();
        ResponseEntity<List<Group>> response;
        try {
            response = new ResponseEntity<>(groupService.getGroups(), 200L);
        } catch (ServiceException e) {
            problems.add(e.getMessage());
            
            response = new ResponseEntity<>(null, 200L);
        }
        return new CommonResponse<>(flag, response, problems);
    }

    public CommonResponse<Group> getStudentGroupById(IdRequest idRequest) {
        var flag = false;
        var problems = idRequestValidator.validate(idRequest);
        ResponseEntity<Group> response;
        if (problems.isEmpty()) {
            try {
                response = new ResponseEntity<>(groupService.getGroup(idRequest.getId()));
                flag = true;
            } catch (ServiceException e) {
                response = new ResponseEntity<>(null, 422L);
                problems.add(e.getMessage());
                
            }
        } else {
            response = new ResponseEntity<>(null, 422L);
        }
        return new CommonResponse<>(flag, response, problems);
    }


    // TODO переделать все методы под такой тип
    // ловить любые ислючения
    public ResponseEntity<CommonResponse<Long>> addStudentGroup(AddStudentGroupRequest addStudentGroupRequest) {
        var problems = addStudentGroupValidator.validate(addStudentGroupRequest);
        long status = 200L;
        CommonResponse<Long> response;
        if (problems.isEmpty()) {

            try {
                response = new CommonResponse<>(groupService.addGroup(addStudentGroupRequest.getName()));
            } catch (ServiceException e) {
                response = new CommonResponse<>(e.getMessage(), problems);
            }
        } else {
            response = new CommonResponse<>("Error while validate", problems);
        }
        return new ResponseEntity<>(response, 422L);
    }

    public CommonResponse<Long> editStudentGroup(EditStudentGroupRequest editStudentGroupRequest) {
        var flag = false;
        var problems = editStudentGroupValidator.validate(editStudentGroupRequest);
        ResponseEntity<Long> response;
        if (problems.isEmpty()) {
            try {
                response = new ResponseEntity<>(groupService.updateGroup(new Group(editStudentGroupRequest.getId(),
                        editStudentGroupRequest.getName())).getId(), 200L);
            } catch (ServiceException e) {
                problems.add(e.getMessage());
                
                response = new ResponseEntity<>(null, 422L);
            }
        } else {
            response = new ResponseEntity<>(null, 422L);
        }
        return new CommonResponse<>(flag, response, problems);
    }

    public CommonResponse<Long> deleteStudentGroup(IdRequest idRequest) {
        var flag = false;
        var problems = idRequestValidator.validate(idRequest);
        ResponseEntity<Long> response;
        if (problems.isEmpty()) {
            try {
                response = new ResponseEntity<>(groupService.deleteGroup(idRequest.getId()));
            } catch (ServiceException e) {
                problems.add(e.getMessage());
                
                response = new ResponseEntity<>(null, 422L);
            }
        } else {
            response = new ResponseEntity<>(null, 422L);
        }
        return new CommonResponse<>(flag, response, problems);
    }
}
