package karabalin.server.requests.student;

public record EditStudentRequest(Long id, String surname, String name, String patronymic, String status, Long groupId) {
}
