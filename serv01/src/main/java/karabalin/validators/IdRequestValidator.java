package karabalin.validators;

import karabalin.requests.IdRequest;
import karabalin.validators.primitive.LongValidator;

import java.util.ArrayList;
import java.util.List;

public class IdRequestValidator implements Validator<IdRequest> {
    @Override
    public List<String> validate(IdRequest idRequest) {
        var result = new ArrayList<String>();
        var id = idRequest.getId();
        if (!LongValidator.notNull(id)) {
            result.add("Id is Null");
        }
        if (!LongValidator.moreThanZero(id)) {
            result.add("Id is less than 0");
        }
        return result;
    }
}
