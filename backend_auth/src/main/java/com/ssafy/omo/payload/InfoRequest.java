package com.ssafy.omo.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class InfoRequest {

	@NotBlank
	@Size(min = 10)
	private String password;

	@NotBlank
	@Size(min = 10)
	private String newPassword;


}
