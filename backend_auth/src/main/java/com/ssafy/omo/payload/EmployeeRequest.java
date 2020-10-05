package com.ssafy.omo.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EmployeeRequest {

	@NotBlank
	@Size(min = 10)
	private String name;

	@NotBlank
	@Size(min = 50)
	private String phone;

	@NotBlank
	@Size(min = 50)
	private String address;

}
