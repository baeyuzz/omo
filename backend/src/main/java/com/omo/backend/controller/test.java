package com.omo.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.http.HttpHeaders;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class test {

   
    @PostMapping("uploadAudio4list")
    public ResponseEntity upload (@RequestBody final Map<String, Object> body, @RequestHeader final HttpHeaders headers) throws Exception {


        MultipartFile[] files =  (MultipartFile[]) body.get("audio");

        for (MultipartFile file : files) {
            String fileName= file.getOriginalFilename();
            File f= new File("C:\\ssafy\\project2\\pjt3\\s03p23a509\\AI\\Voice\\" + fileName);
            file.transferTo(f);
        }
        
        return new ResponseEntity<>(true, HttpStatus.OK);

    }
}
