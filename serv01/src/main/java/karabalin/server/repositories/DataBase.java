package karabalin.server.repositories;

import karabalin.server.entities.Group;
import karabalin.server.entities.Student;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private final Map<Long, Group> groupsTable;
    private final Map<Long, Student> studentsTable;

    public Map<Long, Group> getGroupsTable() {
        return groupsTable;
    }

    public Map<Long, Student> getStudentsTable() {
        return studentsTable;
    }

    public DataBase() {
        this.groupsTable = new HashMap<>();
        this.studentsTable = new HashMap<>();
    }
}
