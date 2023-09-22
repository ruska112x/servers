package karabalin.requests.student;

public class AddStudentRequest {
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final Long groupId;

    private final String status;

    public AddStudentRequest(String lastName, String firstName, String middleName, Long groupId, String status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.groupId = groupId;
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getStatus() {
        return status;
    }
}
