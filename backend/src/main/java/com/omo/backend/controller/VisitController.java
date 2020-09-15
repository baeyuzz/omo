package com.omo.backend.controller;

import com.omo.backend.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VisitController {

    @PostMapping("/visitation")
    public ResponseEntity<ApiResponse> visit() {

        return new ResponseEntity<ApiResponse>(HttpStatus.OK);
    }
}
