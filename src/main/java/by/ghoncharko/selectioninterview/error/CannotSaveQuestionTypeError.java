package by.ghoncharko.selectioninterview.error;

public class CannotSaveQuestionTypeError extends Error{
    public CannotSaveQuestionTypeError() {
    }

    public CannotSaveQuestionTypeError(String message) {
        super(message);
    }

    public CannotSaveQuestionTypeError(String message, Throwable cause) {
        super(message, cause);
    }
}
