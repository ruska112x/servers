package karabalin.server.entities.additional;

public enum StudentStatus {
    STUDY, SABBATICAL, EXPELLED;

    public static StudentStatus getStatusByString(String string) {
        if (string.equalsIgnoreCase("study")) {
            return STUDY;
        }
        if (string.equalsIgnoreCase("sabbatical")) {
            return SABBATICAL;
        }
        if (string.equalsIgnoreCase("expelled")) {
            return EXPELLED;
        }
        return null;
    }
}
