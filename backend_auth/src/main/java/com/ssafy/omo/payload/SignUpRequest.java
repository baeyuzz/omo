package com.ssafy.omo.payload;

import com.ssafy.omo.model.role.RoleName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.ssafy.omo.model.role.RoleName.ROLE_ADMIN;

@Data
public class SignUpRequest {

	@NotBlank
	@Size(min = 3, max = 15)
	private String username;

	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;

	@NotBlank
	@Size(min = 6, max = 20)
	private RoleName name = ROLE_ADMIN;
}
