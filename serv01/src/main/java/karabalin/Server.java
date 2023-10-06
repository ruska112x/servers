package karabalin;

import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupRepository;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.responses.CommonResponse;
import karabalin.server.services.GroupService;
import karabalin.server.controllers.GroupController;
import karabalin.server.entities.Group;
import karabalin.server.requests.group.AddStudentGroupRequest;

import java.util.List;

public class Server {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        DataBase dataBase = new DataBase();
        GroupRepository groupRepository = new GroupRepository(dataBase);
        GroupService groupService = new GroupService(groupRepository);
        GroupController groupController = new GroupController(groupService);
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-102"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-103"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-104"));
        CommonResponse<Long> response = groupController.editStudentGroup(new EditStudentGroupRequest(112L, "MFS-101"));
        System.out.println(response.getDetails().toString());
        CommonResponse<List<Group>> responseGroup = groupController.getStudentGroups();
        System.out.println(responseGroup.getData().getBody().toString());
        System.out.println(responseGroup.getDetails().toString());
        groupController.deleteStudentGroup(new IdRequest(1L));
        responseGroup = groupController.getStudentGroups();
        System.out.println(responseGroup.getData().getBody().toString());
    }
}