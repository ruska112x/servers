package karabalin;

import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupRepository;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.responses.CommonResponse;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.responses.ResponseHandler;
import karabalin.server.services.GroupService;
import karabalin.server.controllers.GroupController;
import karabalin.server.entities.Group;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;
import karabalin.server.validators.group.EditStudentGroupValidator;

import java.util.List;

public class Server {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        DataBase dataBase = new DataBase();
        GroupRepository groupRepository = new GroupRepository(dataBase);
        GroupService groupService = new GroupService(groupRepository);
        GroupController groupController = new GroupController(groupService, new AddStudentGroupValidator(), new EditStudentGroupValidator(), new IdRequestValidator());
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-102"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-103"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-104"));
        ResponseEntity<CommonResponse<Long>> response = groupController.editStudentGroup(new EditStudentGroupRequest(112L, "MFS-101"));
        ResponseHandler<Long, CommonResponse<Long>> responseHandler = new ResponseHandler<>(response);
        responseHandler.getData();
        ResponseEntity<CommonResponse<List<Group>>> responseGroup = groupController.getStudentGroups();
        System.out.println(new ResponseHandler<>(responseGroup).getData());
        groupController.deleteStudentGroup(new IdRequest(1L));
        responseGroup = groupController.getStudentGroups();
        System.out.println(new ResponseHandler<>(responseGroup).getData());
    }
}