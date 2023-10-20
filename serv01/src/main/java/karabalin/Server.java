package karabalin;

import karabalin.server.controllers.StudentController;
import karabalin.server.entities.Student;
import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupRepository;
import karabalin.server.repositories.StudentRepository;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.requests.student.AddStudentRequest;
import karabalin.server.responses.CommonResponse;
import karabalin.server.responses.ResponseEntity;
import karabalin.server.responses.ResponseHandler;
import karabalin.server.services.GroupService;
import karabalin.server.controllers.GroupController;
import karabalin.server.entities.Group;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.services.StudentService;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;
import karabalin.server.validators.group.EditStudentGroupValidator;
import karabalin.server.validators.primitive.LongValidator;
import karabalin.server.validators.primitive.StringValidator;
import karabalin.server.validators.student.AddStudentValidator;

import java.util.List;

public class Server {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        DataBase dataBase = new DataBase();
        GroupRepository groupRepository = new GroupRepository(dataBase);
        GroupService groupService = new GroupService(groupRepository);
        LongValidator longValidator = new LongValidator();
        StringValidator stringValidator = new StringValidator();
        GroupController groupController = new GroupController(
                groupService,
                new AddStudentGroupValidator(stringValidator),
                new EditStudentGroupValidator(longValidator, stringValidator),
                new IdRequestValidator(longValidator));
        StudentController studentController = new StudentController(new StudentService(new StudentRepository(dataBase)), new IdRequestValidator(longValidator), new AddStudentValidator(stringValidator, longValidator));
        var id103 = groupController.addStudentGroup(new AddStudentGroupRequest("MMB-103")).getBody().getData();
        var id104 = groupController.addStudentGroup(new AddStudentGroupRequest("MMB-104")).getBody().getData();
        studentController.addStudent(new AddStudentRequest("Stanislavovich", "Mikula", "Gerhard", id104, "STUDY"));
        studentController.addStudent(new AddStudentRequest("Stanislavovich2", "Mikula", "Gerhard", id103, "STUDY"));
        studentController.addStudent(new AddStudentRequest("Stanislavovich3", "Mikula", "Gerhard", id103, "STUDY"));
        System.out.println(groupController.getStudentGroups().getBody().getData());
        System.out.println(studentController.getStudentsByGroupId(new IdRequest(id104)).getBody().getData());
        System.out.println(studentController.getStudentsByGroupId(new IdRequest(id103)).getBody().getData());
        // TODO: 10/13/23 обёртку для всего этого init, start, send(строка содержащая ендпоинт и жсон строку), парсить и десериализовывать в реквест, респонс хендлер не нужен по сути он заменяет фронт
    }
}