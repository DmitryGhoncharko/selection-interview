package by.ghoncharko.selectioninterview.error;

public class CannotCreateQuestionError extends Error{
    public CannotCreateQuestionError() {
    }

    public CannotCreateQuestionError(String message) {
        super(message);
    }

    public CannotCreateQuestionError(String message, Throwable cause) {
        super(message, cause);
    }
}
