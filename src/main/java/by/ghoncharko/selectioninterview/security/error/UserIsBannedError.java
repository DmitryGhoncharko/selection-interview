package by.ghoncharko.selectioninterview.security.error;

public class UserIsBannedError extends Error{
    public UserIsBannedError() {
    }

    public UserIsBannedError(String message) {
        super(message);
    }

    public UserIsBannedError(String message, Throwable cause) {
        super(message, cause);
    }
}
