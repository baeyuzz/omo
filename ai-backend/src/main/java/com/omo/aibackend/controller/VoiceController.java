package com.omo.aibackend.controller;

import com.omo.aibackend.payload.*;
import com.omo.aibackend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class VoiceController {

    private final ClientService clientService;

    @PostMapping("/uploadAudio4list") // 음성 인식 페이지
    public ResponseEntity upload (@RequestHeader final Map<String, Object> info, @RequestParam (value = "audio") final MultipartFile file) throws Exception {
        String code = (String) info.get("code");

        String fileName= file.getOriginalFilename();
        File f= new File("C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\" + code + "\\" + fileName);
        file.transferTo(f);

        System.out.println("Python Call");
        String[] command = new String[3];
        command[0] = "python";
        command[1] = "C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\test.py";
        command[2] = code;

        // python code 실행하는 부분
        try {
            execPython(command,0);
        } catch (Exception e) {
            e.printStackTrace();
            execPython(command,1);
        }
        System.out.println("res : " + res);
        String name = res.split("_")[0];
        String phone = res.split("_")[1];

        // 1. 방문자 인식이 되었다면
        // 2. backend_auth 서버로 방문자 정보 요청
        // 3. 방문자 관리 서버에 명부 작성 요청
        if(res != null) {   // 1. 방문자 인식 성공

            // 2. 인식한 방문자의 정보 요청
            ResponseEntity memberResponse = clientService.callGetAuthExternalServer(
                    new MemberRequest().builder()
                            .name(name)
                            .phone(phone)
                            .build());

            if(memberResponse.getStatusCode() != HttpStatus.OK) {

            } else {

            }

            // 3. 받아온 정보로 방문자 관리 서버에 전송
            ResponseEntity visitorResponse = clientService.callPostVisitorExternalServer(
                    new VisitorRequest().builder()
                            .groupCode(code)
                            .name(name)
                            .phone(phone)
                            //.address(address)
                            .build());

            if(visitorResponse.getStatusCode() != HttpStatus.OK) {
                System.out.println("방문자 서버 에러");
                return new ResponseEntity<>(false, HttpStatus.OK);
            } else {
                System.out.println(visitorResponse.getBody());
//                return new ResponseEntity(response.getBody(), HttpStatus.OK);
            }

            return new ResponseEntity<>(res, HttpStatus.OK);
        } else
            return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @PostMapping("/uploadAudio4member") // 음성 등록 페이지
    public ResponseEntity regMember (@RequestHeader final Map<String, Object> info, @RequestParam (value = "audio") final MultipartFile file) throws Exception {

        String code = (String) info.get("code");
        String name = (String) info.get("name");
        String phone = (String) info.get("phone");
        String address = (String) info.get("addr");

        ResponseEntity response = clientService.callPostAuthExternalServer(
                new MemberSignUpRequest().builder()
                        .code(code)
                        .name(name)
                        .phone(phone)
                        .address(address)
                        .build()
        );

        if(response.getStatusCode() != HttpStatus.OK) return new ResponseEntity<>(false, HttpStatus.OK);

        String nplusp = name + "_" + phone;

        String path = "C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\" + code + "\\data\\" + nplusp;

        File newfile = new File(path);

        if (!newfile.mkdirs()) {
            System.out.println("폴더 생성 실패");
        }

        // .wav 파일 로컬에 저장
        File f= new File(path + "\\record.wav");
        file.transferTo(f);

        // python 실행 부분
        System.out.println("Python Call");
        String[] command = new String[4];
        command[0] = "python";
        command[1] = "C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\mfcctonpy.py";
        command[2] = code;
        command[3] = nplusp;

        try {
            execPython(command,0);
        } catch (Exception e) {
            e.printStackTrace();
            execPython(command,1);
        } // mfcc를 .npy 로 저장


        // 여기는 training 하는 코드인데 굳이 java에서 안하고 python에서 하면 될 둣
        // System.out.println("Python Call 2");
        // String[] command = new String[4];
        // command[0] = "python";
        // command[1] = "C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\voiceRecognition.py";
        // command[2] = code;

        // try {
        //     execPython(command);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    public static void execPython(String[] command, int idx) throws IOException, InterruptedException { // python 실행 함수
        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; i++) {
            commandLine.addArgument(command[i]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();

        executor.setExitValue(idx);

        executor.setStreamHandler(pumpStreamHandler);
        int result = executor.execute(commandLine);
        System.out.println("result: " + result);
        System.out.println("output: " + outputStream.toString());

        String output = outputStream.toString();
        int idx2 = output.lastIndexOf("result : ");

        String res = output.substring(idx2+9);
        
        if(idx2 == -1)
            return "success";

        return res;

    }

    @PostMapping("/test")
    public ResponseEntity VisitLogExternalServerRequestTest() {
        ResponseEntity response = clientService.callPostVisitorTestExternalServer(
                new VisitorRequest().builder()
                        .groupCode("ssafy")
                        .name("최문경")
                        .phone("010-8535-3684")
                        .address("서울 송파구")
                        .build());

        if(response.getStatusCode() != HttpStatus.OK) {
            System.out.println("방문자 서버 에러");
            return new ResponseEntity<>(false, HttpStatus.OK);
        } else {
            System.out.println(response.getBody());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }

}
