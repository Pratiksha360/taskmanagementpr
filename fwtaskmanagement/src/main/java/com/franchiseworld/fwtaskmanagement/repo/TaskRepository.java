package com.franchiseworld.fwtaskmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franchiseworld.fwtaskmanagement.dto.Employee;
import com.franchiseworld.fwtaskmanagement.dto.Project;
import com.franchiseworld.fwtaskmanagement.dto.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByAssignedTo(Employee assignedTo);
    List<Task> findByProject(Project project);
    List<Task> findByProject_ProjectId(Long projectId);

}
