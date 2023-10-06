package karabalin;

import karabalin.server.exceptions.ServiceException;
import karabalin.server.repositories.DataBase;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.services.GroupService;
import karabalin.server.controllers.GroupController;
import karabalin.server.entities.Group;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.responses.ResponseEntity;

import java.util.List;

public class Server {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        DataBase dataBase = new DataBase();
        GroupService groupService = new GroupService(dataBase);
        GroupController groupController = new GroupController(groupService);
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-102"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-103"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-104"));
//        groupController.editStudentGroup(new EditStudentGroupRequest(112L, "MFS-101"));
        ResponseEntity<List<Group>> responseGroup = groupController.getStudentGroups();
        System.out.println(responseGroup.getBody().toString());
        groupController.deleteStudentGroup(new IdRequest(1L));
        responseGroup = groupController.getStudentGroups();
        System.out.println(responseGroup.getBody().toString());
    }
}