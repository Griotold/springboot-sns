package com.griotold.springboot_sns.controller;

import com.griotold.springboot_sns.domain.user.UserException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleValidationException(
      MethodArgumentNotValidException e) {
    List<FieldError> fieldErrors =
        e.getBindingResult().getFieldErrors().stream()
            .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
            .toList();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ValidationErrorResponse("입력값이 올바르지 않습니다", fieldErrors));
  }

  @ExceptionHandler(UserException.EmailAlreadyExists.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(
      UserException.EmailAlreadyExists e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
  }

  @ExceptionHandler(UserException.UserNotFound.class)
  public ResponseEntity<ErrorResponse> handleUserNotFound(UserException.UserNotFound e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
  }

  public record ErrorResponse(String message) {}

  public record ValidationErrorResponse(String message, List<FieldError> errors) {}

  public record FieldError(String field, String message) {}
}
