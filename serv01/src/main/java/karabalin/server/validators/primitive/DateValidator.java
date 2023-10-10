package karabalin.server.validators.primitive;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
    public boolean validateRegEx(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    public boolean validateRegEx(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
