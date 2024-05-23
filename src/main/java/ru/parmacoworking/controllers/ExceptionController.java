package ru.parmacoworking.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.parmacoworking.exceptions.*;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityIllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseEntity handleEntityIllegalArgumentException(EntityIllegalArgumentException e){
        return createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseEntity handleEntityAlreadyExistsException(EntityAlreadyExistsException e){
        return createErrorResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseEntity handleEntityConflictException(EntityConflictException e){
        return createErrorResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityHasDetailsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseEntity handleEntityHasDetailsException(EntityHasDetailsException e){
        return createErrorResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseEntity handleEntityNotFoundException(EntityNotFoundException e){
        return createErrorResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    private static ErrorResponseEntity createErrorResponseEntity(BaseException e, HttpStatus httpStatus){
        return new ErrorResponseEntity(e.getMessage(), httpStatus.getReasonPhrase(), httpStatus.value());
    }
}
