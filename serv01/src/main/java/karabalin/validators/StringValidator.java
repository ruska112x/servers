package karabalin.validators;

public class StringValidator {
    public boolean notNull(String aString) {
        return aString != null;
    }

    public boolean notEmpty(String aString) {
        return !"".equals(aString);
    }

    public boolean lessThan(String aString) {
        return aString.length() < 255;
    }
}
