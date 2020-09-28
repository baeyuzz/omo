package com.ssafy.omo.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InfoRequest {

	@NotBlank
	private String street;

	@NotBlank
	private String suite;

	@NotBlank
	private String city;

	@NotBlank
	private String zipcode;

	private String bs;

	private String website;

	private String phone;

}
