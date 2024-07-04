package com.franchiseworld.fwtaskmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.franchiseworld.fwtaskmanagement.dto.Employee;
import com.franchiseworld.fwtaskmanagement.dto.Project;
import com.franchiseworld.fwtaskmanagement.dto.Task;
import com.franchiseworld.fwtaskmanagement.service.EmployeeService;
import com.franchiseworld.fwtaskmanagement.service.ProjectService;
import com.franchiseworld.fwtaskmanagement.service.TaskService;
import com.franchiseworld.fwtaskmanagement.util.ResponseStructure;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private TaskService taskService;

	@GetMapping("/byProjectHead")
	public ResponseEntity<ResponseStructure<List<Project>>> getAllProjects(@RequestParam("id") Long projectHeadId) {
		ResponseStructure<List<Project>> responseStructure = new ResponseStructure<>();
		Optional<Employee> projectHead = employeeService.getEmployeeById(projectHeadId);
		if (projectHead.isPresent()) {
			List<Project> projects = projectService.getProjectsByProjectHead(projectHead.get());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Projects fetched successfully");
			responseStructure.setData(projects);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Project head not found");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<ResponseStructure<Project>> getProjectById(@PathVariable Long id) {
		ResponseStructure<Project> responseStructure = new ResponseStructure<>();
		Optional<Project> project = projectService.getProjectById(id);
		if (project.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Project found");
			responseStructure.setData(project.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Project not found");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Project>> createProject(@RequestBody Project project) {
		return projectService.saveProject(project);
	}

	@PutMapping("/{projectId}")
	public ResponseEntity<ResponseStructure<Project>> updateProject(@PathVariable Long projectId,
			@RequestBody Project projectDetails) {
		ResponseStructure<Project> responseStructure = new ResponseStructure<>();
		Optional<Project> updatedProject = projectService.updateProject(projectId, projectDetails);
		if (updatedProject.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Project updated successfully");
			responseStructure.setData(updatedProject.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Project not found");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{projectId}/{taskId}")
	public ResponseEntity<ResponseStructure<Task>> updateTask(@PathVariable Long projectId, @PathVariable Long taskId,
			@RequestBody Task taskDetails) {
		ResponseStructure<Task> responseStructure = new ResponseStructure<>();
		Optional<Task> updatedTask = taskService.updateTask(projectId, taskId, taskDetails);
		if (updatedTask.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Task updated successfully");
			responseStructure.setData(updatedTask.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Task not found");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
}
