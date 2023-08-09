package by.ghoncharko.selectioninterview.error;

public class CannotUpdateQuestionTypeError extends Error{
    public CannotUpdateQuestionTypeError() {
    }

    public CannotUpdateQuestionTypeError(String message) {
        super(message);
    }

    public CannotUpdateQuestionTypeError(String message, Throwable cause) {
        super(message, cause);
    }
}
