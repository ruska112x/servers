package karabalin.server.requests.student;

public record EditStudentRequest(Long id, String lastName, String firstName, String middleName, Long groupId,
                                 String status) {
}
