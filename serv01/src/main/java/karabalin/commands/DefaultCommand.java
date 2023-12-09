package karabalin.commands;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DefaultCommand implements ICommand {
    @Override
    public String execute(String json) throws JsonProcessingException {
        return "No such EndPoint!";
    }
}
