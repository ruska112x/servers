package karabalin;

import karabalin.server.Service;
import karabalin.server.controllers.GroupController;
import karabalin.server.entities.Group;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.responses.ResponseEntity;

import java.util.List;

public class Server {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Service service = new Service();
        GroupController groupController = new GroupController(service);
        ResponseEntity<Long> response = groupController.addStudentGroup(new AddStudentGroupRequest("MMB-101"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-102"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-103"));
        groupController.addStudentGroup(new AddStudentGroupRequest("MMB-104"));
        ResponseEntity<List<Group>> responseGroup = groupController.getStudentGroups();
        System.out.println(responseGroup.getBody().toString());
    }
}