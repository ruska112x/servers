package karabalin.server.requests.student;

public record AddStudentRequest(String patronymic, String surname, String name, Long groupId, String status) {
}
