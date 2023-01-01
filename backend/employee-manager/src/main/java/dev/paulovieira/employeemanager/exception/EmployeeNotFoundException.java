package dev.paulovieira.employeemanager.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * @author Paulo Vieira
 * @version 1.0
 * @since 31/12/2022
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EmployeeNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
