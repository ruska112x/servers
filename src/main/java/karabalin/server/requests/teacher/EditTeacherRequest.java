package karabalin.server.requests.teacher;

public record EditTeacherRequest(long id, String surname, String name, String patronymic) {
}
