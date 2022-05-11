package com.guava.customerms.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.guava.customerms.dto.ErrorResponseDto;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    public static final String VALIDATION_FAILED_MSG = "Validation failed!";
    public static final String INTERNAL_ERROR_MSG = "Request cannot be processed by the server!";


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {
        String errorDetail = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        return getError(BAD_REQUEST, VALIDATION_FAILED_MSG, errorDetail, ex, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleHibernateConstraintViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        String errorDetail = ex.getLocalizedMessage();
        return getError(BAD_REQUEST, VALIDATION_FAILED_MSG, errorDetail, ex, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  WebRequest request) {
        String errorDetail = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        return getError(BAD_REQUEST, VALIDATION_FAILED_MSG, errorDetail, ex, request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String errorDetail = ex.getMessage();
        return getError(INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MSG, errorDetail, ex, request);
    }

    private ResponseEntity<Object> getError(
            HttpStatus status, String message, String errorDetail, Exception ex, WebRequest request) {
        log.error("{} : {}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(status)
                .body(ErrorResponseDto.builder()
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .message(message)
                        .errorDetail(errorDetail)
                        .path(request.getDescription(false))
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
