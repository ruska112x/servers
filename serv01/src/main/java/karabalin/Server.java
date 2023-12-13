package karabalin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import karabalin.commands.*;
import karabalin.commands.group.*;
import karabalin.commands.student.*;
import karabalin.server.IServer;
import karabalin.server.controllers.GroupController;
import karabalin.server.controllers.StudentController;
import karabalin.server.controllers.SubjectController;
import karabalin.server.entities.GroupDTO;
import karabalin.server.entities.StudentDTO;
import karabalin.server.entities.StudentStatuses;
import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupRepository;
import karabalin.server.repositories.StudentRepository;
import karabalin.server.repositories.SubjectRepository;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.services.GroupService;
import karabalin.server.services.StudentService;
import karabalin.server.services.SubjectService;
import karabalin.server.validators.IdRequestValidator;
import karabalin.server.validators.group.AddStudentGroupValidator;
import karabalin.server.validators.group.EditStudentGroupValidator;
import karabalin.server.validators.primitive.DateValidator;
import karabalin.server.validators.primitive.LongValidator;
import karabalin.server.validators.primitive.StringValidator;
import karabalin.server.validators.student.AddStudentValidator;
import karabalin.server.validators.student.EditStudentValidator;
import karabalin.server.validators.subject.AddSubjectValidator;
import karabalin.server.validators.subject.EditSubjectValidator;

import java.util.HashMap;
import java.util.Map;

public class Server implements IServer {
    private final DateValidator dateValidator;
    private final LongValidator longValidator;

    private final StringValidator stringValidator;

    private final IdRequestValidator idRequestValidator;

    private final AddStudentGroupValidator addStudentGroupValidator;

    private final EditStudentGroupValidator editStudentGroupValidator;

    private final AddStudentValidator addStudentValidator;

    private final EditStudentValidator editStudentValidator;

    private final AddSubjectValidator addSubjectValidator;

    private final EditSubjectValidator editSubjectValidator;

    private final DataBase dataBase;

    private final GroupRepository groupRepository;

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    private final GroupService groupService;

    private final StudentService studentService;

    private final SubjectService subjectService;
    private final GroupController groupController;

    private final StudentController studentController;

    private final SubjectController subjectController;

    private final ObjectMapper mapper;
    private final Map<String, ICommand> commands;

    public Server() {
        dateValidator = new DateValidator();
        longValidator = new LongValidator();
        stringValidator = new StringValidator();
        idRequestValidator = new IdRequestValidator(longValidator);
        addStudentGroupValidator = new AddStudentGroupValidator(stringValidator);
        editStudentGroupValidator = new EditStudentGroupValidator(longValidator, stringValidator);
        addStudentValidator = new AddStudentValidator(stringValidator, longValidator);
        editStudentValidator = new EditStudentValidator(stringValidator, longValidator);
        addSubjectValidator = new AddSubjectValidator(stringValidator);
        editSubjectValidator = new EditSubjectValidator(stringValidator, longValidator);
        dataBase = new DataBase(
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>()
        );
        groupRepository = new GroupRepository(dataBase);
        studentRepository = new StudentRepository(dataBase);
        subjectRepository = new SubjectRepository(dataBase);
        groupService = new GroupService(groupRepository);
        studentService = new StudentService(studentRepository);
        subjectService = new SubjectService(subjectRepository);
        groupController = new GroupController(
                groupService,
                addStudentGroupValidator,
                editStudentGroupValidator,
                idRequestValidator
        );
        studentController = new StudentController(
                studentService,
                groupService,
                idRequestValidator,
                addStudentValidator,
                editStudentValidator
        );
        subjectController = new SubjectController(
                subjectService,
                addSubjectValidator,
                editSubjectValidator,
                idRequestValidator
        );

        mapper = new ObjectMapper();

        commands = new HashMap<>();

        commands.put("addGroup", new AddGroupCommand(groupController, mapper));
        commands.put("deleteGroup", new DeleteStudentGroupCommand(groupController, mapper));
        commands.put("editGroup", new EditStudentGroupCommand(groupController, mapper));
        commands.put("getGroupByID", new GetStudentGroupByIdCommand(groupController, mapper));
        commands.put("getGroups", new GetStudentGroupsCommand(groupController, mapper));

        commands.put("addStudent", new AddStudentCommand(studentController, mapper));
        commands.put("deleteStudent", new DeleteStudentCommand(studentController, mapper));
        commands.put("editStudent", new EditStudentCommand(studentController, mapper));
        commands.put("getStudent", new GetStudentCommand(studentController, mapper));
        commands.put("getStudentsByGroupId", new GetStudentsByGroupIdCommand(studentController, mapper));
    }

    @Override
    public String executeRequest(String endPoint, String json) throws JsonProcessingException {
        return commands.getOrDefault(endPoint, new DefaultCommand()).execute(json);
    }

    public static void main(String[] args) throws JsonProcessingException {
        Server server = new Server();
        String jsonStudent = new ObjectMapper().writeValueAsString(
                new StudentDTO(
                        null,
                        "Mikual",
                        "Gerhard",
                        "Stanislavovich",
                        StudentStatuses.STUDY,
                        new GroupDTO(
                                null,
                                "MMB-104"
                        )));
        String json = new ObjectMapper().writeValueAsString(
                new AddStudentGroupRequest(
                        "MMB-104"
                )
        );

        System.out.println(json);
        System.out.println(server.executeRequest("addGroup", json));
    }
}