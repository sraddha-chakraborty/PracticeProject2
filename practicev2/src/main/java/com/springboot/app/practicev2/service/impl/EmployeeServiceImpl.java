package com.springboot.app.practicev2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.app.practicev2.exception.ResourceNotFoundException;
//import com.springboot.app.practicev2.exception.ResourceNotFoundException;
import com.springboot.app.practicev2.model.Employee;
import com.springboot.app.practicev2.repository.EmployeeRepository;
import com.springboot.app.practicev2.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		try
		{
			return employeeRepository.findById(id).get();
		}
		catch(Exception e)
		{
			new ResourceNotFoundException("Employee", "Id", id);
			return null;
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		Employee existingEmployee = new Employee();
		try
		{
			existingEmployee = employeeRepository.findById(id).get();
			
			existingEmployee.setFirstName(employee.getFirstName());
			existingEmployee.setLastName(employee.getLastName());
			existingEmployee.setEmail(employee.getEmail());
			
			employeeRepository.save(existingEmployee);
			return existingEmployee;
		}
		catch (Exception e)
		{
			new ResourceNotFoundException("Employee", "Id", id);
			return null;
		}
	}

	@Override
	public void deleteEmployee(long id) {
		try
		{
			employeeRepository.findById(id);
			employeeRepository.deleteById(id);
		}
		catch (Exception e)
		{
			new ResourceNotFoundException("Employee", "Id", id);
		}
	}
	
}