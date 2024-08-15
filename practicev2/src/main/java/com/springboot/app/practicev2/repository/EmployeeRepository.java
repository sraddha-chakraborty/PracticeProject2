package com.springboot.app.practicev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.practicev2.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
