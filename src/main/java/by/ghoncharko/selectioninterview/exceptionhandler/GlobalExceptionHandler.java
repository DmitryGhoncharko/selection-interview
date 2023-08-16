package by.ghoncharko.selectioninterview.exceptionhandler;

import by.ghoncharko.selectioninterview.error.CannotCreateAnswerError;
import by.ghoncharko.selectioninterview.error.CannotCreateQuestionError;
import by.ghoncharko.selectioninterview.error.CannotCreateQuestionTypeError;
import by.ghoncharko.selectioninterview.error.CannotDeleteAnswerError;
import by.ghoncharko.selectioninterview.error.CannotDeleteQuestionError;
import by.ghoncharko.selectioninterview.error.CannotDeleteQuestionTypeError;
import by.ghoncharko.selectioninterview.error.CannotUpdateQuestionError;
import by.ghoncharko.selectioninterview.error.CannotUpdateQuestionTypeError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.PropertyAccessException;
import org.hibernate.StaleStateException;
import org.hibernate.TransactionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        ErrorResponse errorResponse = new ErrorResponse();
        for (FieldError fieldError : fieldErrors) {
            errorResponse.getErrors().add(fieldError.getDefaultMessage());
        }
        log.error("Validation error", fieldErrors);
        return errorResponse;
    }
    @ExceptionHandler({PersistenceException.class, HibernateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleBaseHibernateJpaExceptions(PersistenceException persistenceException, HibernateException hibernateException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(persistenceException.getMessage());
        errorResponse.getErrors().add(hibernateException.getMessage());
        log.error("Persistence or hibernate exception", errorResponse);
        return errorResponse;
    }
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse entityNotFoundException(EntityNotFoundException entityNotFoundException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(entityNotFoundException.getMessage());
        log.error("Entity not found exception", errorResponse);
        return errorResponse;
    }
    @ExceptionHandler(OptimisticLockException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse optimisticLockException(OptimisticLockException optimisticLockException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(optimisticLockException.getMessage());
        log.error("Optimistic lock exception",errorResponse);
        return errorResponse;
    }
    @ExceptionHandler(RollbackException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse rollbackException(RollbackException rollbackException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(rollbackException.getMessage());
        log.error("rollback exception",errorResponse);
        return errorResponse;
    }
    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse transactionException(TransactionException transactionException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(transactionException.getMessage());
        log.error("transaction exception", errorResponse);
        return errorResponse;
    }
    @ExceptionHandler({ConstraintViolationException.class, org.hibernate.exception.ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse constraintViolationException(ConstraintViolationException constraintViolationException, org.hibernate.exception.ConstraintViolationException violationException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(constraintViolationException.getMessage());
        errorResponse.getErrors().add(violationException.getMessage());
        log.error("Constraint violation exception",errorResponse);
        return errorResponse;
    }
    @ExceptionHandler(SQLGrammarException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse sqlGrammarException(SQLGrammarException sqlGrammarException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(sqlGrammarException.getMessage());
        log.error("Sql grammar exception",errorResponse);
        return errorResponse;
    }
    @ExceptionHandler(StaleStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse staleStateException(StaleStateException staleStateException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(staleStateException.getMessage());
        log.error("Stale state exception", errorResponse);
        return errorResponse;
    }
    @ExceptionHandler(PropertyAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse propertyAccessException(PropertyAccessException propertyAccessException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(propertyAccessException.getMessage());
        log.error("property access exception", errorResponse);
        return errorResponse;
    }
    @ExceptionHandler(CannotUpdateQuestionTypeError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotCreateQuestionTypeError(CannotUpdateQuestionTypeError cannotUpdateQuestionTypeError){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(cannotUpdateQuestionTypeError.getMessage());
        log.error(errorResponse.toString());
        return errorResponse;
    }
    @ExceptionHandler(CannotDeleteQuestionTypeError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotDeleteQuestionTypeError(CannotDeleteQuestionTypeError cannotDeleteQuestionTypeError){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(cannotDeleteQuestionTypeError.getMessage());
        log.error(errorResponse.toString());
        return errorResponse;
    }
    @ExceptionHandler(CannotCreateQuestionTypeError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotCreateQuestionTypeError(CannotCreateQuestionTypeError cannotCreateQuestionTypeError){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(cannotCreateQuestionTypeError.getMessage());
        log.error(errorResponse.toString());
        return errorResponse;
    }

    @ExceptionHandler(CannotUpdateQuestionError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotUpdateQuestionError(CannotUpdateQuestionError cannotUpdateQuestionError){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(cannotUpdateQuestionError.getMessage());
        log.error(errorResponse.toString());
        return errorResponse;
    }
    @ExceptionHandler(CannotDeleteQuestionError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotDeleteQuestionError(CannotDeleteQuestionError cannotDeleteQuestionError){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(cannotDeleteQuestionError.getMessage());
        log.error(errorResponse.toString());
        return errorResponse;
    }
    @ExceptionHandler(CannotCreateQuestionError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotCreateQuestionError(CannotCreateQuestionError cannotCreateQuestionError){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(cannotCreateQuestionError.getMessage());
        log.error(errorResponse.toString());
        return errorResponse;
    }
    @ExceptionHandler(CannotDeleteAnswerError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotDeleteAnswerError(CannotDeleteAnswerError cannotDeleteAnswerError){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(cannotDeleteAnswerError.getMessage());
        log.error(errorResponse.toString());
        return errorResponse;
    }
    @ExceptionHandler(CannotCreateAnswerError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse cannotCreateAnswerError(CannotCreateAnswerError cannotCreateAnswerError){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(cannotCreateAnswerError.getMessage());
        log.error(errorResponse.toString());
        return errorResponse;
    }
}
