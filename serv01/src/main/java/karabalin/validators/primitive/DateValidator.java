package karabalin.validators.primitive;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateValidator {
    public static boolean validateRegEx(String dateString) {
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
