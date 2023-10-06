package karabalin.server.responses;

public class ResponseEntity<T> {
    private final T body;
    private final Long httpStatus;

    public ResponseEntity(T body) {
        this.body = body;
        httpStatus = 404L;
    }

    public ResponseEntity(T body, Long httpStatus) {
        this.body = body;
        this.httpStatus = httpStatus;
    }

    public T getBody() {
        return body;
    }


    // TODO commonResponse<t>, exceptions
    // commonResponse<T> contains response<t>
    // and flag about succession
    // and List<String> of exceptions and errors
    public Long getHttpStatus() {
        return httpStatus;
    }
}
