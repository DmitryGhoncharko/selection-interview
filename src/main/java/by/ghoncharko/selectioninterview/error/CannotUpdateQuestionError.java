package by.ghoncharko.selectioninterview.error;

public class CannotUpdateQuestionError extends Error{
    public CannotUpdateQuestionError() {
    }

    public CannotUpdateQuestionError(String message) {
        super(message);
    }

    public CannotUpdateQuestionError(String message, Throwable cause) {
        super(message, cause);
    }
}
