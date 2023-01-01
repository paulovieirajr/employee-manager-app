package dev.paulovieira.employeemanager.controller;

import dev.paulovieira.employeemanager.exception.*;
import dev.paulovieira.employeemanager.model.*;
import dev.paulovieira.employeemanager.repository.*;
import dev.paulovieira.employeemanager.service.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Paulo Vieira
 * @version 1.0
 * @since 31/12/2022
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Employee>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
//        if (employeeRepository.existsByEmail(employee.getEmail())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//        if (employeeRepository.existsByPhone(employee.getPhone())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(service.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.findById(id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
