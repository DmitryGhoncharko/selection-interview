package by.ghoncharko.selectioninterview.error;

public class CannotCreateAnswerError extends Error{
    public CannotCreateAnswerError() {
    }

    public CannotCreateAnswerError(String message) {
        super(message);
    }

    public CannotCreateAnswerError(String message, Throwable cause) {
        super(message, cause);
    }
}
