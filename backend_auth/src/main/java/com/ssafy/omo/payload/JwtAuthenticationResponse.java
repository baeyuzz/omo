package com.ssafy.omo.payload;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private String companyName;
	public JwtAuthenticationResponse(String accessToken,String companyName) {
		this.accessToken = accessToken;
		this.companyName = companyName;
	}

}
