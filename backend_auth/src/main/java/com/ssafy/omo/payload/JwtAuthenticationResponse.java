package com.ssafy.omo.payload;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private String code;
	public JwtAuthenticationResponse(String accessToken,String code) {
		this.accessToken = accessToken;
		this.code = code;
	}

}
