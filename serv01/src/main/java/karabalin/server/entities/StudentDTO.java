package karabalin.server.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public record StudentDTO(
        Long id,
        String surname,
        String name,
        String patronymic,
        String status,
        GroupDTO group
) {
}
