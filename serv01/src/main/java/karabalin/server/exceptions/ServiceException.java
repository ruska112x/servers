package karabalin.server.exceptions;

public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    // TODO: 10/13/23 notfoundexc + статус код 404 
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
