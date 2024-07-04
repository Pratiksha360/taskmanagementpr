package com.franchiseworld.fwtaskmanagement.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeID;
	@Column(unique = true)
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	@Column(unique = true)
	@NotBlank(message = "email must be filled.")
	@NotNull(message = "email Can not be null")
	@Email(regexp = "[a-z0-9._$]+@[a-z]+\\.[a-z]{2,3}",message = "Enter proper Email")
	private String email;
	private Position position;
	private String profilePicture;
	@Column(unique = true)
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private String contact;
	private String address;
	private String gender;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String birthDate;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;
}
