package com.example.cruddemo.service;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.exception.ResourceNotFoundException;
import com.example.cruddemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    // READ - all
    public List<Employee> findAll() {
        return repository.findAll();
    }

    // READ - by id
    public Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // UPDATE
    public Employee update(Long id, Employee updated) {
        Employee existing = findById(id);
        existing.setName(updated.getName());
        existing.setDepartment(updated.getDepartment());
        existing.setEmail(updated.getEmail());
        existing.setSalary(updated.getSalary());
        return repository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        Employee existing = findById(id);
        repository.delete(existing);
    }
}
