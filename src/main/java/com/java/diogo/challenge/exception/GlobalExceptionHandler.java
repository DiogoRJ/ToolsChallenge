package com.java.diogo.challenge.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(ZonedDateTime.now(ZoneId.systemDefault()), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno do servidor", exception.getMessage(), getPath());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(ZonedDateTime.now(ZoneId.systemDefault()), HttpStatus.NOT_FOUND.value(), "Recurso não encontrado", exception.getMessage(), getPath());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException exception) {
        ErrorMessage errorMessage = new ErrorMessage(ZonedDateTime.now(ZoneId.systemDefault()), HttpStatus.BAD_REQUEST.value(), "Requisição inválida", exception.getMessage(), getPath());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    private String getPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getRequestURI();
    }

    public static class ErrorMessage {
        private final String timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;

        public ErrorMessage(ZonedDateTime timestamp, int status, String error, String message, String path) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
            this.timestamp = timestamp.format(formatter);
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }
    }
}
