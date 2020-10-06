package com.omo.aibackend.service;

import com.omo.aibackend.payload.SignUpRequest;
import com.omo.aibackend.payload.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserClientService {

    private final ApiService<SignUpResponse> apiService;

    public SignUpResponse callPostExternalServer(SignUpRequest request) {
        return apiService.post("https://j3a509.p.ssafy.io:9004/api/auth/signup", HttpHeaders.EMPTY, request, SignUpResponse.class).getBody();
    }

}
