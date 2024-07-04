package com.franchiseworld.fwtaskmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.franchiseworld.fwtaskmanagement.dto.Employee;
import com.franchiseworld.fwtaskmanagement.dto.Project;
import com.franchiseworld.fwtaskmanagement.dto.Task;
import com.franchiseworld.fwtaskmanagement.repo.TaskRepository;
import com.franchiseworld.fwtaskmanagement.util.ResponseStructure;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksByAssignedTo(Employee assignedTo) {
        return taskRepository.findByAssignedTo(assignedTo);
    }

    public List<Task> getTasksByProject(Project project) {
        return taskRepository.findByProject(project);
    }

    public ResponseEntity<ResponseStructure<Task>> saveTask(Task task) {
        ResponseStructure<Task> structure = new ResponseStructure<>();
        structure.setMessage("Task saved successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(taskRepository.save(task));

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProject_ProjectId(projectId);
    }
    
    public Optional<Task> updateTask(Long projectId, Long taskId, Task taskDetails) {
        return (Optional<Task>) taskRepository.findById(taskId).map(task -> {
            if (task.getProject().getProjectId().equals(projectId)) {
                task.setTaskName(taskDetails.getTaskName());
                task.setDescription(taskDetails.getDescription());
                task.setStatus(taskDetails.getStatus());
                task.setEstimatedTime(taskDetails.getEstimatedTime());
                task.setStartDate(taskDetails.getStartDate());
                task.setEndDate(taskDetails.getEndDate());
                task.setUpdatedAt(taskDetails.getUpdatedAt());
                return Optional.of(taskRepository.save(task));
            } else {
                return Optional.empty();
            }
        }).orElse(Optional.empty());
    }
}
