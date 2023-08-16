package by.ghoncharko.selectioninterview.error;

public class CannotDeleteQuestionTypeError extends Error{
    public CannotDeleteQuestionTypeError() {
    }

    public CannotDeleteQuestionTypeError(String message) {
        super(message);
    }

    public CannotDeleteQuestionTypeError(String message, Throwable cause) {
        super(message, cause);
    }
}
