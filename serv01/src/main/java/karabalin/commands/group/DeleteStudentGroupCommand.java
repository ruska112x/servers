package karabalin.commands.group;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import karabalin.commands.ICommand;
import karabalin.server.controllers.GroupController;
import karabalin.server.requests.IdRequest;

public class DeleteStudentGroupCommand implements ICommand {

    private final GroupController groupController;
    private final ObjectMapper mapper;

    public DeleteStudentGroupCommand(GroupController groupController, ObjectMapper mapper) {
        this.groupController = groupController;
        this.mapper = mapper;
    }

    @Override
    public String execute(String json) throws JsonProcessingException {
        return mapper.writeValueAsString(groupController.deleteStudentGroup(mapper.readValue(json, IdRequest.class)));
    }
}
