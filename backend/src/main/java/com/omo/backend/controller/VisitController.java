package com.omo.backend.controller;

import com.omo.backend.model.VisitLog;
import com.omo.backend.payload.VisitorRequest;
import com.omo.backend.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class VisitController {

    private VisitService visitService;

    @PostMapping("/visitor")
    @ResponseBody
    public ResponseEntity<VisitLog> visit(@RequestBody VisitorRequest request) {
        Optional<VisitLog> visitLog = visitService.writeGuestBook(request);
        if(!visitLog.isPresent())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(visitLog.get(), HttpStatus.OK);
    }

    @GetMapping("/visitors/{groupCode}")
    @ResponseBody
    public ResponseEntity getVisitorList(@PathVariable String groupCode) {
        Optional<List<VisitLog>> visitLogs = visitService.getVisitorList(groupCode);
        if(!visitLogs.isPresent())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(visitLogs.get(), HttpStatus.OK);
    }

}
