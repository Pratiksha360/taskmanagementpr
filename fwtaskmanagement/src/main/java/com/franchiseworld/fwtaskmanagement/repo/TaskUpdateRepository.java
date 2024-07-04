package com.franchiseworld.fwtaskmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franchiseworld.fwtaskmanagement.dto.Task;
import com.franchiseworld.fwtaskmanagement.dto.TaskUpdate;

public interface TaskUpdateRepository extends JpaRepository<TaskUpdate, Long>{

	List<TaskUpdate> findByTask(Task task);
}
