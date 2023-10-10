package karabalin.server.responses;

import karabalin.server.exceptions.HandelException;

public class ResponseHandler<T, U extends CommonResponse<T>> {
    private U response;

    public ResponseHandler(ResponseEntity<U> response) {
        this.response = response.getBody();
    }

    public T getData() {
        if (response.isSuccess()) {
            return response.getData();
        } else {
            System.out.println(response.getError() != null ? response.getError() : "Error is null");
            if (response.getDetails() != null) {
                for (String string : response.getDetails()) {
                    System.out.println(string);
                }
            }
            return null;
        }
    }
}
