package karabalin.validators.primitive;

public class LongValidator {

    public static boolean notNull(Long aLong) {
        return aLong != null;
    }

    public static boolean moreThanZero(Long aLong) {
        return aLong.compareTo(0L) > 0;
    }
}
