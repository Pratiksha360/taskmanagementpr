package com.franchiseworld.fwtaskmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.franchiseworld.fwtaskmanagement.dto.Project;
import com.franchiseworld.fwtaskmanagement.dto.Task;
import com.franchiseworld.fwtaskmanagement.service.ProjectService;
import com.franchiseworld.fwtaskmanagement.service.TaskService;
import com.franchiseworld.fwtaskmanagement.util.ResponseStructure;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private ProjectService projectService;

	@GetMapping
	public List<Task> getAllTasks(@RequestParam Long projectId) {
		Optional<Project> project = projectService.getProjectById(projectId);
		return project.map(taskService::getTasksByProject).orElseGet(List::of);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		Optional<Task> task = taskService.getTaskById(id);
		return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Task>> createTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}

	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) {
		List<Task> tasks = taskService.getTasksByProjectId(projectId);
		if (tasks.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(tasks);
	}

	@PutMapping("/{projectId}/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable Long projectId, @PathVariable Long taskId,
			@RequestBody Task taskDetails) {
		return taskService.updateTask(projectId, taskId, taskDetails).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
