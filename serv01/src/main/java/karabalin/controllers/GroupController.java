package karabalin.controllers;

import karabalin.entities.Group;
import karabalin.requests.IdRequest;
import karabalin.requests.group.AddStudentGroupRequest;
import karabalin.requests.group.EditStudentGroupRequest;
import karabalin.responses.ResponseEntity;
import java.util.List;

public class GroupController {
    public ResponseEntity<List<Group>> getStudentGroups() {
        return null;
    }

    public ResponseEntity<Group> getStudentGroupById(IdRequest idRequest) {
        return null;
    }

    public ResponseEntity<Long> addStudentGroup(AddStudentGroupRequest addStudentGroupRequest) {
        return null;
    }

    public ResponseEntity<Long> editStudentGroup(EditStudentGroupRequest editStudentGroupRequest) {
        return null;
    }

    public ResponseEntity<Long> deleteStudentGroup(IdRequest idRequest) {
        return null;
    }
}
