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
import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupRepository;
import karabalin.server.repositories.StudentRepository;
import karabalin.server.repositories.SubjectRepository;
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

    private final Map<String, ICommand> commands;

    public Server() {
        DateValidator dateValidator = new DateValidator();
        LongValidator longValidator = new LongValidator();
        StringValidator stringValidator = new StringValidator();
        IdRequestValidator idRequestValidator = new IdRequestValidator(longValidator);
        AddStudentGroupValidator addStudentGroupValidator = new AddStudentGroupValidator(stringValidator);
        EditStudentGroupValidator editStudentGroupValidator = new EditStudentGroupValidator(longValidator, stringValidator);
        AddStudentValidator addStudentValidator = new AddStudentValidator(stringValidator, longValidator);
        EditStudentValidator editStudentValidator = new EditStudentValidator(stringValidator, longValidator);
        AddSubjectValidator addSubjectValidator = new AddSubjectValidator(stringValidator);
        EditSubjectValidator editSubjectValidator = new EditSubjectValidator(stringValidator, longValidator);
        DataBase dataBase = new DataBase(
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>()
        );
        GroupRepository groupRepository = new GroupRepository(dataBase);
        StudentRepository studentRepository = new StudentRepository(dataBase);
        SubjectRepository subjectRepository = new SubjectRepository(dataBase);
        GroupService groupService = new GroupService(groupRepository);
        StudentService studentService = new StudentService(studentRepository);
        SubjectService subjectService = new SubjectService(subjectRepository);
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
        SubjectController subjectController = new SubjectController(
                subjectService,
                addSubjectValidator,
                editSubjectValidator,
                idRequestValidator
        );

        ObjectMapper mapper = new ObjectMapper();

        commands = new HashMap<>();

        commands.put("addGroup", new AddGroupCommand(groupController, mapper));
        commands.put("deleteGroup", new DeleteStudentGroupCommand(groupController, mapper));
        commands.put("editGroup", new EditStudentGroupCommand(groupController, mapper));
        commands.put("getGroupById", new GetStudentGroupByIdCommand(groupController, mapper));
        commands.put("getGroups", new GetStudentGroupsCommand(groupController, mapper));

        commands.put("addStudent", new AddStudentCommand(studentController, mapper));
        commands.put("deleteStudent", new DeleteStudentCommand(studentController, mapper));
        commands.put("editStudent", new EditStudentCommand(studentController, mapper));
        commands.put("getStudent", new GetStudentCommand(studentController, mapper));
        commands.put("getStudentsByGroupId", new GetStudentsByGroupIdCommand(studentController, mapper));
    }

    @Override
    public String executeRequest(String endPoint, String json) throws JsonProcessingException {
        return commands.getOrDefault(endPoint, new DefaultCommand()).executeToJSON(json);
    }
}