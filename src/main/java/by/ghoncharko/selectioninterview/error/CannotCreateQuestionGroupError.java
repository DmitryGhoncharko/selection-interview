package by.ghoncharko.selectioninterview.error;

public class CannotCreateQuestionGroupError  extends Error{
    public CannotCreateQuestionGroupError() {
    }

    public CannotCreateQuestionGroupError(String message) {
        super(message);
    }

    public CannotCreateQuestionGroupError(String message, Throwable cause) {
        super(message, cause);
    }
}
