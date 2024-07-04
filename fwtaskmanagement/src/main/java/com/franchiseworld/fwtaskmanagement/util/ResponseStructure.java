package com.franchiseworld.fwtaskmanagement.util;

import org.springframework.http.ResponseEntity;

import com.franchiseworld.fwtaskmanagement.dto.Project;
import com.franchiseworld.fwtaskmanagement.dto.Task;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private String message;
	private int status;
	private T data;

}
