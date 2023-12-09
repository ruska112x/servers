package karabalin.commands;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ICommand {
    String execute(String json) throws JsonProcessingException;
}
