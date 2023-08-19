package by.ghoncharko.selectioninterview.error;

public class CannotUpdateQuestionGroupError extends Error{
    public CannotUpdateQuestionGroupError() {
    }

    public CannotUpdateQuestionGroupError(String message) {
        super(message);
    }

    public CannotUpdateQuestionGroupError(String message, Throwable cause) {
        super(message, cause);
    }
}
