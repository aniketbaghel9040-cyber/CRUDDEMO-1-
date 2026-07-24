package com.example.cruddemo.controller;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // CREATE -> 201 Created
    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        Employee saved = service.create(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // READ ALL -> 200 OK
    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // READ ONE -> 200 OK (404 handled globally if not found)
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // UPDATE -> 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(service.update(id, employee));
    }

    // DELETE -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
