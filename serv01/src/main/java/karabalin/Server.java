package karabalin;

import karabalin.server.IServer;
import karabalin.server.controllers.GroupController;
import karabalin.server.controllers.StudentController;
import karabalin.server.controllers.SubjectController;
import karabalin.server.entities.GroupDTO;
import karabalin.server.entities.StudentDTO;
import karabalin.server.entities.StudentStatuses;
import karabalin.server.entities.SubjectDTO;
import karabalin.server.repositories.DataBase;
import karabalin.server.repositories.GroupRepository;
import karabalin.server.repositories.StudentRepository;
import karabalin.server.repositories.SubjectRepository;
import karabalin.server.requests.IdRequest;
import karabalin.server.requests.group.AddStudentGroupRequest;
import karabalin.server.requests.student.AddStudentRequest;
import karabalin.server.requests.student.EditStudentRequest;
import karabalin.server.requests.subject.AddSubjectRequest;
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
import java.util.List;

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

    }

    @Override
    public void executeRequest(String endPoint, String json) {

    }

    /*public static void main(String[] args) {
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

        long cppId = subjectController
                .addSubject(new AddSubjectRequest("C++"))
                .getBody()
                .getData();

        SubjectDTO cppDTO = subjectController
                .getSubjectById(new IdRequest(cppId))
                .getBody()
                .getData();
        System.out.println(cppDTO);

        long oodId = subjectController
                .addSubject(new AddSubjectRequest("OOD"))
                .getBody()
                .getData();

        SubjectDTO oodDTO = subjectController
                .getSubjectById(new IdRequest(oodId))
                .getBody()
                .getData();
        System.out.println(oodDTO);

        long mathId = subjectController
                .addSubject(new AddSubjectRequest("Math"))
                .getBody()
                .getData();

        SubjectDTO mathDTO = subjectController
                .getSubjectById(new IdRequest(mathId))
                .getBody()
                .getData();
        System.out.println(mathDTO);

        List<SubjectDTO> subjectDTOList = subjectController
                .getSubjects()
                .getBody()
                .getData();
        System.out.println(subjectDTOList);
    }*/
}