package karabalin.server.requests.student;

public record AddStudentRequest(String surname, String name, String patronymic, Long groupId, String status) {
}
