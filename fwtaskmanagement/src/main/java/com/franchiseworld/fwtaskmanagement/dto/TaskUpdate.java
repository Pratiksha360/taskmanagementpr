package com.franchiseworld.fwtaskmanagement.dto;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TaskUpdate {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long updateID;
    private String updateDescription;
    private String status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Date updateDate;

    @ManyToOne
    private Task task;

    @ManyToOne
    private Employee updatedBy;
    
    @ManyToOne
    private Project project;

}
