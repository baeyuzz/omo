package com.omo.backend.visitor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(description = "방문자 명부 관리 API")
public class VisitController {

    private final VisitService visitService;

    @PostMapping("/visitors")
    @ResponseBody
    @ApiOperation(value = "방문자 명부 작성")
    public ResponseEntity<VisitLog> visit(@RequestBody VisitorRequest request) {
        Optional<VisitLog> visitLog = visitService.writeGuestBook(request);
        if(!visitLog.isPresent())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(visitLog.get(), HttpStatus.OK);
    }

    @GetMapping("/visitors/{groupCode}")
    @ResponseBody
    @ApiOperation(value = "방문자 명부 조회")
    public ResponseEntity getVisitorList(@PathVariable String groupCode) {
        Optional<List<VisitLog>> visitLogs = visitService.getVisitorList(groupCode);
        if(!visitLogs.isPresent())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(visitLogs.get(), HttpStatus.OK);
    }

}