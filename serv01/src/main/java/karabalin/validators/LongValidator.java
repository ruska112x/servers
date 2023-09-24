package karabalin.validators;

public class LongValidator {

    public boolean notNull(Long aLong) {
        return aLong != null;
    }

    public boolean moreThanZero(Long aLong) {
        return aLong.compareTo(0L) > 0;
    }
}
