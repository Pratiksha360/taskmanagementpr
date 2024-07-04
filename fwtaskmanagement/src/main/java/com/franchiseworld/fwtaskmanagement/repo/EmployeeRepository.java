package com.franchiseworld.fwtaskmanagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franchiseworld.fwtaskmanagement.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByUsername(String username);

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByContact(String contact);

}
