package com.omo.aibackend.service;

import com.omo.aibackend.payload.*;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ApiService<ResponseEntity> clientApiService;

    /**
     * 멤버 관리 서버로 멤버 등록 POST 통신
     * */
    public ResponseEntity callPostAuthExternalServer(MemberSignUpRequest request) {
        return clientApiService.post("https://j3a509.p.ssafy.io:9004/api/auth/member", HttpHeaders.EMPTY, request).getBody();
    }

    /**
     * 멤버 관리 서버로 방문자 정보 요청 GET 통신
     * */
    public ResponseEntity callGetAuthExternalServer(MemberRequest request) {
        return clientApiService.get("https://j3a509.p.ssafy.io:9004/api/auth/member/" + request.getName() + "/" + request.getPhone(), HttpHeaders.EMPTY).getBody();
    }

    /**
     * 방문자 관리 서버로 방문자 정보 POST 통신
     * */
    public ResponseEntity callPostVisitorExternalServer(VisitorRequest request) {
        return clientApiService.post("https://j3a509.p.ssafy.io:8080/api/visitors", HttpHeaders.EMPTY, request).getBody();
    }

    /**
     * 방문자 관리 서버로 방문자 정보 POST 통신 TEST
     * */
    public ResponseEntity callPostVisitorTestExternalServer(VisitorRequest request) {
        return clientApiService.post("http://localhost:8080/api/visitors", HttpHeaders.EMPTY, request).getBody();
    }

}
