package com.franchiseworld.fwtaskmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.franchiseworld.fwtaskmanagement.dto.Employee;
import com.franchiseworld.fwtaskmanagement.dto.Project;
import com.franchiseworld.fwtaskmanagement.repo.EmployeeRepository;
import com.franchiseworld.fwtaskmanagement.repo.ProjectRepository;
import com.franchiseworld.fwtaskmanagement.util.ResponseStructure;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Project> getProjectsByProjectHead(Employee projectHead) {
		return projectRepository.findByProjectHead(projectHead);
	}

	@Transactional
	public ResponseEntity<ResponseStructure<Project>> saveProject(Project project) {
		ResponseStructure<Project> responseStructure = new ResponseStructure<>();
		if (project.getEmployee() == null || project.getEmployee().getEmployeeID() == null) {
			responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Project must have a valid employee");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}

		Optional<Employee> optionalProjectHead = employeeRepository.findById(project.getEmployee().getEmployeeID());
		if (optionalProjectHead.isEmpty()) {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Project head is not present");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		} else {
			project.setProjectHead(optionalProjectHead.get());
			project.setCreatedAt(LocalDateTime.now());
			project.setUpdatedAt(LocalDateTime.now());
			Project savedProject = projectRepository.save(project);

			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Project created successfully");
			responseStructure.setData(savedProject);

			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		}
	}

	public Optional<Project> getProjectById(Long id) {
		return projectRepository.findById(id);
	}

	public Optional<Project> updateProject(Long projectId, Project projectDetails) {
		return projectRepository.findById(projectId).map(project -> {
			project.setProjectName(projectDetails.getProjectName());
			project.setDescription(projectDetails.getDescription());
			project.setStartDate(projectDetails.getStartDate());
			project.setStatus(projectDetails.getStatus());
			project.setEndDate(projectDetails.getEndDate());
			project.setUpdatedAt(LocalDateTime.now());
			return projectRepository.save(project);
		});
	}
}
