package com.omo.aibackend.service;

import com.omo.aibackend.payload.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ApiService<MemberSignUpResponse> authPostClientApiService;
    private final ApiService<MemberResponse> authGetClientApiService;
    private final ApiService<VisitorResponse> visitorPostClientApiService;

    /**
     * 멤버 관리 서버로 멤버 등록 POST 통신
     * */
    public MemberSignUpResponse callPostAuthExternalServer(String token, MemberSignUpRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return authPostClientApiService
                .post(
                        "https://j3a509.p.ssafy.io:9004/api/employee",
                        headers,
                        request,
                        MemberSignUpResponse.class)
                .getBody();
    }

    /**
     * 멤버 관리 서버로 방문자 정보 요청 GET 통신
     * */
    public MemberResponse callGetAuthExternalServer(String token, MemberRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return authGetClientApiService
                .get(
                        "https://j3a509.p.ssafy.io:9004/api/employee/" + request.getUserName() + "/" + request.getPhone(),
                        headers,
                        MemberResponse.class)
                .getBody();
    }

    /**
     * 방문자 관리 서버로 방문자 정보 POST 통신
     * */
    public VisitorResponse callPostVisitorExternalServer(VisitorRequest request) {
        return visitorPostClientApiService
                .post(
                        "https://j3a509.p.ssafy.io:8080/api/visitors",
                        HttpHeaders.EMPTY,
                        request,
                        VisitorResponse.class)
                .getBody();
    }

    /**
     * ******************************************
     *
     *  이하 함수들은 서버간 연결 테스트를 위한 함수들 입니다
     *
     * ******************************************
     * */

    /**
     * 멤버 관리 서버로 멤버 등록 POST 통신 TEST
     * */
    public MemberSignUpResponse callPostAuthTestExternalServer(MemberSignUpRequest request) {
        HttpHeaders headers = new HttpHeaders();
        // 로그인한 후 토큰값으로 넣어서 테스트 진행해야함
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjAyMDU5Nzg5LCJleHAiOjE2MDIwNjMzODl9.7xZ2FnK0okefEtDzeH96aLG2nBd8DKaSH-olMaud6PhAffChVfYVch7pdtcVbBSFtsjplN6BPt1V8GbLi8W7fg");
        headers.setContentType(MediaType.APPLICATION_JSON);

        return authPostClientApiService.post("http://localhost:9004/api/employee", headers, request, MemberSignUpResponse.class).getBody();
    }

    /**
     * 멤버 관리 서버로 방문자 정보 요청 GET 통신 TEST
     * */
    public MemberResponse callGetAuthTestExternalServer(MemberRequest request) {
        HttpHeaders headers = new HttpHeaders();
        // 로그인한 후 토큰값으로 넣어서 테스트 진행해야함
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjAyMDU5Nzg5LCJleHAiOjE2MDIwNjMzODl9.7xZ2FnK0okefEtDzeH96aLG2nBd8DKaSH-olMaud6PhAffChVfYVch7pdtcVbBSFtsjplN6BPt1V8GbLi8W7fg");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return authGetClientApiService.get("http://localhost:9004/api/employee/" + request.getUserName() + "/" + request.getPhone(), headers, MemberResponse.class).getBody();
    }

    /**
     * 방문자 관리 서버로 방문자 정보 POST 통신 TEST
     * */
    public VisitorResponse callPostVisitorTestExternalServer(VisitorRequest request) {
        return visitorPostClientApiService.post("http://localhost:8080/api/visitors", HttpHeaders.EMPTY, request, VisitorResponse.class).getBody();
    }

}