package karabalin.server.requests.student;

public record AddStudentRequest(String lastName, String firstName, String middleName, Long groupId, String status) {
}
