package com.franchiseworld.fwtaskmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franchiseworld.fwtaskmanagement.dto.Employee;
import com.franchiseworld.fwtaskmanagement.dto.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findByProjectHead(Employee projectHead);

}
