package dev.paulovieira.employeemanager.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Paulo Vieira
 * @version 1.0
 * @since 31/12/2022
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_ENTITY,
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(problemDetail);
    }
}
