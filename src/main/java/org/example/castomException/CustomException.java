package org.example.castomException;

public class CustomException extends NullPointerException{
private String message;

    public CustomException( String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
