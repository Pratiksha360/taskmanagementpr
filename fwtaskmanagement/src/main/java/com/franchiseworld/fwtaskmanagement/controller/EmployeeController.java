package com.franchiseworld.fwtaskmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ProjectService projectService;

/////////////////////////////////////////	for employees  ////////////////////////////////////////////////////

	@GetMapping("/fetchall")
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/fetch")
	public ResponseEntity<Employee> getEmployeeById(@RequestParam("id") Long id) {
		Optional<Employee> user = employeeService.getEmployeeById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/saveemp")
	public ResponseEntity<ResponseStructure<Employee>> createEmployee(@RequestBody Employee employee) {
		System.out.println("Received Employee: " + employee);
		return employeeService.saveEmployee(employee);
	}

	@PutMapping("/updateemp")
	public ResponseEntity<ResponseEntity<ResponseStructure<Employee>>> updateUser(@RequestParam("id") Long id,
			@RequestBody Employee userDetails) {
		Optional<Employee> user = employeeService.getEmployeeById(id);
		if (user.isPresent()) {
			Employee existingUser = user.get();
			existingUser.setFirstName(userDetails.getFirstName());
			existingUser.setMiddleName(userDetails.getMiddleName());
			existingUser.setLastName(userDetails.getLastName());
			existingUser.setEmail(userDetails.getEmail());
			existingUser.setPosition(userDetails.getPosition());
			existingUser.setProfilePicture(userDetails.getProfilePicture());
			existingUser.setContact(userDetails.getContact());
			existingUser.setAddress(userDetails.getAddress());
			existingUser.setGender(userDetails.getGender());
			existingUser.setBirthDate(userDetails.getBirthDate());
			return ResponseEntity.ok(employeeService.updateEmployee(existingUser));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/deleteemp")
	public ResponseEntity<Void> deleteUser(@RequestParam("id") Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

///////////////////////////////////////////////////////for project//////////////////////////////////////////////////
//
//	@PutMapping("/update/{projectId}")
//	public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project projectDetails) {
//		return projectService.updateProject(projectId, projectDetails).map(ResponseEntity::ok)
//				.orElse(ResponseEntity.notFound().build());
//	}
//
//	@PutMapping("/update/{projectId}/{taskId}")
//	public ResponseEntity<Task> updateTask(@PathVariable Long projectId, @PathVariable Long taskId,
//			@RequestBody Task taskDetails) {
//		return taskService.updateTask(projectId, taskId, taskDetails).map(ResponseEntity::ok)
//				.orElse(ResponseEntity.notFound().build());
//	}
//
////	@PostMapping("/saveproject")
////	public ResponseEntity<ResponseStructure<Project>> createProject(@RequestBody Project project) {
////
////		return projectService.saveProject(project);
////	}
////	 @PostMapping("/saveproject")
////	    public ResponseEntity<ResponseEntity<ResponseStructure<Project>>> createProject(@RequestBody Project project) {
////	        ResponseEntity<ResponseStructure<Project>> savedProject = projectService.saveProject(project);
////	        return ResponseEntity.ok(savedProject);
////	    }
//
//	@PostMapping("/saveproject")
//	public ResponseEntity<ResponseStructure<Project>> createProject(@RequestBody Project project) {
//		return projectService.saveProject(project);
//	}
//
///////////////////////////////////////	for task  /////////////////////////////////////////////////////////////
//
//	@PostMapping("/savetask")
//	public ResponseEntity<ResponseStructure<Task>> createTask(@RequestBody Task task) {
//		return taskService.saveTask(task);
//	}

}
