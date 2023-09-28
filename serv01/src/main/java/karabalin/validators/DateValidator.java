package karabalin.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateValidator {
    public static boolean validateRegEx(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
