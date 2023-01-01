package dev.paulovieira.employeemanager.service;

import dev.paulovieira.employeemanager.exception.*;
import dev.paulovieira.employeemanager.model.*;

/**
 * @author Paulo Vieira
 * @version 1.0
 * @since 31/12/2022
 */
public interface EmployeeService {

    Employee findById(Long id) throws EmployeeNotFoundException;

    Iterable<Employee> findAll();

    Employee save(Employee employee);

    Employee update(Long id, Employee employee) throws EmployeeNotFoundException;

    void deleteById(Long id);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
