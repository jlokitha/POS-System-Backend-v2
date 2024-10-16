package lk.ijse.possystembackendv2.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException() {
        super();
    }
    public ItemNotFoundException(String message) {
        super(message);
    }
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
