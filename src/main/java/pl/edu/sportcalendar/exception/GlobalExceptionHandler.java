package pl.edu.sportcalendar.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.sportcalendar.wrapper.ResponseWrapper;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseWrapper<Object>> handleResourceNotFound(EntityNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<ResponseWrapper<Object>> buildError(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(ResponseWrapper.withError(status, message));
    }
}
