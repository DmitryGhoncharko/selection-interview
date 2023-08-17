package by.ghoncharko.selectioninterview.security.error;

public class UserNotFoundError extends Error{
    public UserNotFoundError() {
    }

    public UserNotFoundError(String message) {
        super(message);
    }

    public UserNotFoundError(String message, Throwable cause) {
        super(message, cause);
    }
}
