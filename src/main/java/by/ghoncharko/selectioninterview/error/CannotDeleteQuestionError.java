package by.ghoncharko.selectioninterview.error;

public class CannotDeleteQuestionError extends Error{
    public CannotDeleteQuestionError() {
    }

    public CannotDeleteQuestionError(String message) {
        super(message);
    }

    public CannotDeleteQuestionError(String message, Throwable cause) {
        super(message, cause);
    }
}
