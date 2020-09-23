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

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class test {

    @PostMapping("uploadAudio4list") // 음성 인식 페이지
    public ResponseEntity upload (@RequestParam (value = "audio") final MultipartFile file) throws Exception {

        String fileName= file.getOriginalFilename();
        File f= new File("C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\" + fileName);
        file.transferTo(f);
        
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @PostMapping("uploadAudio4member") // 음성 등록 페이지
    public ResponseEntity regMember (@RequestHeader final Map<String, Object> info, @RequestParam (value = "audio") final MultipartFile file) throws Exception {

        String name = (String) info.get("name");
        String addr = (String) info.get("addr");
        String phone = (String) info.get("phone");
        String code = (String) info.get("code");

        File f= new File("C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\" + code + name + phone + ".wav");
        file.transferTo(f);
        
        return new ResponseEntity<>(true, HttpStatus.OK);

    }
}