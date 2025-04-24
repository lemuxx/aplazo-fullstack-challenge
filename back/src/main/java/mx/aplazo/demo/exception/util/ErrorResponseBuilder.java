package mx.aplazo.demo.exception.util;

import jakarta.servlet.http.HttpServletRequest;
import mx.aplazo.demo.exception.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseBuilder {
    public ResponseEntity<ErrorResponse> toResponse(String message, HttpServletRequest request, String code, HttpStatus error) {
        ErrorResponse body = new ErrorResponse(code, error.toString(), System.currentTimeMillis(), message, request.getRequestURI());
        return ResponseEntity.status(error)
                .body(body);
    }
}
