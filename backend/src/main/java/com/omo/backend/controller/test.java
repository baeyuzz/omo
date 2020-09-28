package com.omo.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;



import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class test {

    @PostMapping("/uploadAudio4list") // 음성 인식 페이지
    public ResponseEntity upload (@RequestHeader final Map<String, Object> info, @RequestParam (value = "audio") final MultipartFile file) throws Exception {

        String code = (String) info.get("code");

        String fileName= file.getOriginalFilename();
        File f= new File("C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\9조\\" + fileName); // 나중엔 9조 대신 code 넣어야함
        file.transferTo(f);
        

        System.out.println("Python Call");
        String[] command = new String[3];
        command[0] = "python";
        command[1] = "C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\test.py";
        command[2] = "ssafy"; // 여기도 9조 대신 code(기관명) 넣어야함
        try {
            execPython(command);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @PostMapping("/uploadAudio4member") // 음성 등록 페이지
    public ResponseEntity regMember (@RequestHeader final Map<String, Object> info, @RequestParam (value = "audio") final MultipartFile file) throws Exception {

        String name = (String) info.get("name");
        String addr = (String) info.get("addr");
        String phone = (String) info.get("phone");
        String code = (String) info.get("code");


        String nplusp = name + phone;

        nplusp = "유진2";
        code = "ssafy";

        String path = "C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\" + code + "\\data\\" + nplusp;

        File newfile = new File(path);

        if (!newfile.mkdirs()) {
    
            System.out.println("폴더 생성 실패");
        }


        File f= new File(path + "\\record.wav");
        file.transferTo(f);
        
        

        System.out.println("Python Call");
        String[] command = new String[4];
        command[0] = "python";
        command[1] = "C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\mfcctonpy.py";
        command[2] = code;
        command[3] = nplusp;

        try {
            execPython(command);
        } catch (Exception e) {
            e.printStackTrace();
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
        // } // mfcc를 .npy 로 저장

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    public static void execPython(String[] command) throws IOException, InterruptedException { // python 실행 함수
        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; i++) {
            commandLine.addArgument(command[i]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);
        int result = executor.execute(commandLine);
        System.out.println("result: " + result);
        System.out.println("output: " + outputStream.toString());

    }    
}

