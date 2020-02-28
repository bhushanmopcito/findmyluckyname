package com.clarion.luckyname.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873577783463861992L;
	
	@NotNull(message = "name cannot be empty")
	@NotEmpty(message = "name cannot be empty")
	@Size(min = 1, max = 20, message = "name cannot be greater than 20" )
	private String name;
	
	@NotNull(message = "dateOfBirth cannot be empty")
	@NotEmpty(message = "dateOfBirth cannot be empty")
	@Size(min = 9, max = 10, message = "DateOfBirth cannot be greater than 10" )
	private String dateOfBirth;

	public UserRequestDto() {
		super();
	}

	public UserRequestDto(String name, String dateOfBirth) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "UserRequestDto [name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
	}

}
