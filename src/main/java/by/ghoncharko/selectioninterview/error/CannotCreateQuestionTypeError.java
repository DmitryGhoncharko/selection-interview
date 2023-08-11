package by.ghoncharko.selectioninterview.error;

public class CannotCreateQuestionTypeError extends Error{
    public CannotCreateQuestionTypeError() {
    }

    public CannotCreateQuestionTypeError(String message) {
        super(message);
    }

    public CannotCreateQuestionTypeError(String message, Throwable cause) {
        super(message, cause);
    }
}
