package com.franchiseworld.fwtaskmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.franchiseworld.fwtaskmanagement.dto.Employee;
import com.franchiseworld.fwtaskmanagement.repo.EmployeeRepository;
import com.franchiseworld.fwtaskmanagement.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployees() {
		List<Employee> list = employeeRepository.findAll();
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (list.isEmpty()) {

			structure.setMessage("Employees Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);
		} else {

			structure.setMessage("Employee  Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}

	}

	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {

		Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
		Optional<Employee> existingEmployee1 = employeeRepository.findByContact(employee.getContact());

		if (existingEmployee.isPresent() || existingEmployee1.isPresent()) {
			// Handle duplicate email scenario, e.g., return a conflict response
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Duplicate Employees data");
			structure.setStatus(HttpStatus.CONFLICT.value());
			structure.setData(employee);

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
		} else {
			// Save the employee if no duplicate found
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(employeeRepository.save(employee));

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
		}

	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("Employee saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(employeeRepository.save(employee));

		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}
}
