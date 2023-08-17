package by.ghoncharko.selectioninterview.security.error;

public class CannotRegisterUserError extends Error{
    public CannotRegisterUserError() {
    }

    public CannotRegisterUserError(String message) {
        super(message);
    }

    public CannotRegisterUserError(String message, Throwable cause) {
        super(message, cause);
    }
}
