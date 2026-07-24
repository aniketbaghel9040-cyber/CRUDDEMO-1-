package com.example.cruddemo.repository;

import com.example.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method — Spring Data JPA implements this automatically
    Optional<Employee> findByEmail(String email);
}
