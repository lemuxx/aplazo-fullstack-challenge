package mx.aplazo.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import mx.aplazo.demo.customers.exception.CustomerNotFoundException;
import mx.aplazo.demo.exception.models.ErrorResponse;
import mx.aplazo.demo.exception.util.ErrorResponseBuilder;
import mx.aplazo.demo.loans.exception.InvalidLoanRequestException;
import mx.aplazo.demo.loans.exception.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex, HttpServletRequest request) {
        ErrorResponseBuilder errorResponseBuilder = new ErrorResponseBuilder();
        return errorResponseBuilder.toResponse(ex.getMessage(), request, "ER-APZ-01", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFound(LoanNotFoundException ex, HttpServletRequest request) {
        ErrorResponseBuilder errorResponseBuilder = new ErrorResponseBuilder();
        return errorResponseBuilder.toResponse(ex.getMessage(), request, "ER-APZ-03", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleArgumentMismatch(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ErrorResponseBuilder errorResponseBuilder = new ErrorResponseBuilder();
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return errorResponseBuilder.toResponse(message, request, "ER-APZ-02", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLoanRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoanRequest(InvalidLoanRequestException ex, HttpServletRequest request) {
        ErrorResponseBuilder errorResponseBuilder = new ErrorResponseBuilder();
        return errorResponseBuilder.toResponse(ex.getMessage(), request, "ER-APZ-04", HttpStatus.BAD_REQUEST);
    }

}
