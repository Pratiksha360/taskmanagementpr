package com.franchiseworld.fwtaskmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.franchiseworld.fwtaskmanagement.dto.Task;
import com.franchiseworld.fwtaskmanagement.dto.TaskUpdate;
import com.franchiseworld.fwtaskmanagement.service.TaskService;
import com.franchiseworld.fwtaskmanagement.service.TaskUpdateService;

@RestController
@RequestMapping("/taskupdates")
public class TaskUpdateController {
	
    @Autowired
    private TaskUpdateService taskUpdateService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/fetchAll")
    public List<TaskUpdate> getAllTaskUpdates(@RequestParam Long taskId) {
        Optional<Task> task = taskService.getTaskById(taskId);
        return task.map(taskUpdateService::getTaskUpdatesByTask).orElseGet(List::of);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskUpdate> getTaskUpdateById(@PathVariable Long id) {
        Optional<TaskUpdate> taskUpdate = taskUpdateService.getTaskUpdateById(id);
        return taskUpdate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public TaskUpdate createTaskUpdate(@RequestBody TaskUpdate taskUpdate) {
        return taskUpdateService.saveTaskUpdate(taskUpdate);
    }

}
