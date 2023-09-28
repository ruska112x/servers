package karabalin.validators.primitive;

public class StringValidator {
    public static boolean notNull(String aString) {
        return aString != null;
    }

    public static boolean notEmpty(String aString) {
        return !"".equals(aString);
    }

    public static boolean lessThan(String aString, int maxLength) {
        return aString.length() < maxLength;
    }
}
