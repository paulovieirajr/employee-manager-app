package dev.paulovieira.employeemanager.repository;

import dev.paulovieira.employeemanager.model.*;
import org.springframework.data.jpa.repository.*;

/**
 * @author Paulo Vieira
 * @version 1.0
 * @since 31/12/2022
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    void deleteEmployeeById(Long id);

    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    boolean existsByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.phone = ?1")
    boolean existsByPhone(String phone);
}

