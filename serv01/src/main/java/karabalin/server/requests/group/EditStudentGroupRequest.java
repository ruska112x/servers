package karabalin.server.requests.group;

public class EditStudentGroupRequest {
    private final Long id;
    private final String name;

    public EditStudentGroupRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
