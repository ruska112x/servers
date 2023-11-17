package karabalin;

import karabalin.server.controllers.GroupController;
import karabalin.server.controllers.StudentController;
import karabalin.server.entities.GroupDTO;
import karabalin.server.entities.StudentDTO;
import karabalin.server.entities.StudentStatuses;
import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupRepository;
import karabalin.server.repositories.StudentRepository;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.requests.group.EditStudentGroupRequest;
import karabalin.server.requests.student.AddStudentRequest;
import karabalin.server.requests.student.EditStudentRequest;
import karabalin.server.services.GroupService;
import karabalin.server.services.StudentService;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;
import karabalin.server.validators.group.EditStudentGroupValidator;
import karabalin.server.validators.primitive.DateValidator;
import karabalin.server.validators.primitive.LongValidator;
import karabalin.server.validators.primitive.StringValidator;
import karabalin.server.validators.student.AddStudentValidator;
import karabalin.server.validators.student.EditStudentValidator;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        DateValidator dateValidator = new DateValidator();
        LongValidator longValidator = new LongValidator();
        StringValidator stringValidator = new StringValidator();
        IdRequestValidator idRequestValidator = new IdRequestValidator(longValidator);
        AddStudentGroupValidator addStudentGroupValidator = new AddStudentGroupValidator(stringValidator);
        EditStudentGroupValidator editStudentGroupValidator = new EditStudentGroupValidator(longValidator, stringValidator);
        AddStudentValidator addStudentValidator = new AddStudentValidator(stringValidator, longValidator);
        EditStudentValidator editStudentValidator = new EditStudentValidator(stringValidator, longValidator);
        DataBase db = new DataBase(
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>()
        );
        GroupRepository groupRepository = new GroupRepository(db);
        StudentRepository studentRepository = new StudentRepository(db);
        GroupService groupService = new GroupService(groupRepository);
        StudentService studentService = new StudentService(studentRepository);
        GroupController groupController = new GroupController(
                groupService,
                addStudentGroupValidator,
                editStudentGroupValidator,
                idRequestValidator
        );
        StudentController studentController = new StudentController(
                studentService,
                groupService,
                idRequestValidator,
                addStudentValidator,
                editStudentValidator
        );

        long mmb103ID = groupController
                .addStudentGroup(
                        new AddStudentGroupRequest("MMB-103")
                )
                .getBody()
                .getData();
        GroupDTO mmb103 = groupController
                .getStudentGroupById(
                        new IdRequest(mmb103ID)
                )
                .getBody()
                .getData();
        System.out.println(mmb103);

        long mmb104ID = groupController
                .addStudentGroup(
                        new AddStudentGroupRequest("MMB-104")
                )
                .getBody()
                .getData();
        GroupDTO mmb104 = groupController
                .getStudentGroupById(
                        new IdRequest(mmb104ID)
                )
                .getBody()
                .getData();
        System.out.println(mmb104);

        List<GroupDTO> groupDTOList = groupController
                .getStudentGroups()
                .getBody()
                .getData();
        System.out.println(groupDTOList);

        long hardID = studentController
                .addStudent(
                        new AddStudentRequest(
                                "Mikula",
                                "Gerhard",
                                "Stanislavovich",
                                mmb104ID,
                                StudentStatuses.STUDY
                        )
                )
                .getBody()
                .getData();
        StudentDTO hardDTO = studentController
                .getStudentById(
                        new IdRequest(hardID)
                )
                .getBody()
                .getData();
        System.out.println(hardDTO);

        long softID = studentController
                .addStudent(
                        new AddStudentRequest(
                                "Mikula",
                                "Gersoft",
                                "Stanislavovich",
                                mmb104ID,
                                StudentStatuses.STUDY
                        )
                )
                .getBody()
                .getData();
        StudentDTO softDTO = studentController
                .getStudentById(
                        new IdRequest(softID)
                )
                .getBody()
                .getData();
        System.out.println(softDTO);

        List<StudentDTO> studentDTOList = studentController
                .getStudentsByGroupId(new IdRequest(mmb104ID))
                .getBody()
                .getData();
        System.out.println(studentDTOList);

        studentController.editStudent(
                new EditStudentRequest(
                        softID,
                        "Mikula",
                        "Gersoft",
                        "Stanislavovich",
                        StudentStatuses.STUDY,
                        mmb103ID
                )
        );
        studentDTOList = studentController
                .getStudentsByGroupId(new IdRequest(mmb103ID))
                .getBody()
                .getData();
        System.out.println(studentDTOList);
    }
}