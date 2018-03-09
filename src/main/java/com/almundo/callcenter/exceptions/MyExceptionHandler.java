package com.almundo.callcenter.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class MyExceptionHandler
{

    @ExceptionHandler(CustomError.class)
    public ResponseEntity<String> HandleResponse(CustomError ex)
    {
        ValidationError error = new ValidationErrorBuilder().fromOwnValidationError(ex.getOwnMessages(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ModelNotValidException.class)
    public ResponseEntity<ValidationError> HandleResponse(ModelNotValidException ex)
    {
        ValidationError error = new ValidationErrorBuilder().fromOwnValidationError(ex.getOwnMessages(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleException(MethodArgumentNotValidException exception)
    {
        ValidationError error = new ValidationErrorBuilder().fromBindingErrors(exception.getBindingResult(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }


    private class ValidationErrorBuilder
    {

        public ValidationError fromBindingErrors(Errors errors, HttpStatus status)
        {
            ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)", status);
            for (ObjectError objectError : errors.getAllErrors()) {
                error.addValidationError(objectError.getDefaultMessage());
            }
            return error;
        }

        public ValidationError fromOwnValidationError(String[] errors, HttpStatus status)
        {
            ValidationError error = new ValidationError("Validation failed. " + errors.length + " error(s)", status);
            for (String message : errors)
                error.addValidationError(message);
            return error;
        }
    }

    private class ValidationError
    {

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private List<String> details = new ArrayList<>();

        private final String error;

        private final int status;

        public ValidationError(String errorMessage, HttpStatus status)
        {
            this.error = errorMessage;
            this.status = status.value();
        }

        public void addValidationError(String error)
        {
            details.add(error);
        }

        public List<String> getDetails()
        {
            return details;
        }

        public String getError()
        {
            return error;
        }

        public int getStatus()
        {
            return status;
        }

    }


}
