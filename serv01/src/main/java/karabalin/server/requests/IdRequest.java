package karabalin.server.requests;

public class IdRequest {
    private final Long id;

    public IdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
