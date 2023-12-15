package karabalin.commands.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import karabalin.commands.ICommand;
import karabalin.server.controllers.GroupController;
import karabalin.server.requests.group.AddStudentGroupRequest;

public class AddGroupCommand implements ICommand {
    private final GroupController groupController;
    private final ObjectMapper mapper;

    public AddGroupCommand(GroupController groupController, ObjectMapper mapper) {
        this.groupController = groupController;
        this.mapper = mapper;
    }

    @Override
    public String executeToJSON(String json) throws JsonProcessingException {
        return mapper.writeValueAsString(groupController.addStudentGroup(mapper.readValue(json, AddStudentGroupRequest.class)));
    }
}
