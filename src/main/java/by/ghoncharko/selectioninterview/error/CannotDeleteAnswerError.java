package by.ghoncharko.selectioninterview.error;

public class CannotDeleteAnswerError extends Error{
    public CannotDeleteAnswerError() {
    }

    public CannotDeleteAnswerError(String message) {
        super(message);
    }

    public CannotDeleteAnswerError(String message, Throwable cause) {
        super(message, cause);
    }
}
