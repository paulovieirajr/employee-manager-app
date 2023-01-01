package dev.paulovieira.employeemanager.service.impl;

import dev.paulovieira.employeemanager.exception.*;
import dev.paulovieira.employeemanager.model.*;
import dev.paulovieira.employeemanager.repository.*;
import dev.paulovieira.employeemanager.service.*;
import jakarta.transaction.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author Paulo Vieira
 * @version 1.0
 * @since 31/12/2022
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee findById(Long id) throws EmployeeNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    @Override
    public Iterable<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return repository.save(employee);
    }

    @Override
    @Transactional
    public Employee update(Long id, Employee employee) throws EmployeeNotFoundException {
        Employee employeeToUpdate = findById(id);
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setEmail(employee.getEmail());
        employeeToUpdate.setPhone(employee.getPhone());
        employeeToUpdate.setJobTitle(employee.getJobTitle());
        return employeeToUpdate;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }
}
