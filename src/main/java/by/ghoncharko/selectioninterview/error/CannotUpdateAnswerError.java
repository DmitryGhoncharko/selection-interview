package by.ghoncharko.selectioninterview.error;

public class CannotUpdateAnswerError extends Error{
    public CannotUpdateAnswerError() {
    }

    public CannotUpdateAnswerError(String message) {
        super(message);
    }

    public CannotUpdateAnswerError(String message, Throwable cause) {
        super(message, cause);
    }
}
