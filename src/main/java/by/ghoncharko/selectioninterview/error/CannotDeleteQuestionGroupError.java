package by.ghoncharko.selectioninterview.error;

public class CannotDeleteQuestionGroupError extends Error{
    public CannotDeleteQuestionGroupError() {
    }

    public CannotDeleteQuestionGroupError(String message) {
        super(message);
    }

    public CannotDeleteQuestionGroupError(String message, Throwable cause) {
        super(message, cause);
    }
}
