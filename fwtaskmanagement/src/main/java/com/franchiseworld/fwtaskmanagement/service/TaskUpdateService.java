package com.franchiseworld.fwtaskmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franchiseworld.fwtaskmanagement.dto.Task;
import com.franchiseworld.fwtaskmanagement.dto.TaskUpdate;
import com.franchiseworld.fwtaskmanagement.repo.TaskUpdateRepository;

@Service
public class TaskUpdateService {
	 @Autowired
	    private TaskUpdateRepository taskUpdateRepository;

	    public List<TaskUpdate> getTaskUpdatesByTask(Task task) {
	        return taskUpdateRepository.findByTask(task);
	    }

	    public TaskUpdate saveTaskUpdate(TaskUpdate taskUpdate) {
	        return taskUpdateRepository.save(taskUpdate);
	    }

	    public Optional<TaskUpdate> getTaskUpdateById(Long id) {
	        return taskUpdateRepository.findById(id);
	    }
}
