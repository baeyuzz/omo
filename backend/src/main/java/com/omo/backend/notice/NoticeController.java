package com.omo.backend.notice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Api(description = "공지사항 API")
public class NoticeController{

    private NoticeService noticeService;

    @GetMapping("/notice")
    @ResponseBody
    @ApiOperation(value = "공지사항 조회")
    public ResponseEntity<List<Notice>> getNoticeList() {
        Optional<List<Notice>> noticeList = noticeService.getNoticeList();
        if(!noticeList.isPresent())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(noticeList.get(), HttpStatus.OK);
    }
}
