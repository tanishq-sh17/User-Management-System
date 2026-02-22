package com.springtan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exp){

        ErrorResponse userNotFound = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(exp.getMessage())
                .build();

        return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleContactNotFoundException(ContactNotFoundException exp){

        ErrorResponse contactNotFound = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(exp.getMessage())
                .build();

        return new ResponseEntity<>(contactNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<MethodArgumentErrorResponse>> handleMethodArgumentException(MethodArgumentNotValidException ex){

        List<MethodArgumentErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new MethodArgumentErrorResponse(LocalDateTime.now(), fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        ErrorResponse methodArgumentErrorResponse = ErrorResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(methodArgumentErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
