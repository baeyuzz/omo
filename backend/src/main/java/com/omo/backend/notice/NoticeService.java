package com.omo.backend.notice;

import com.omo.backend.notice.Notice;
import com.omo.backend.notice.NoticeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoticeService {

    private NoticeRepository noticeRepository;

    public Optional<List<Notice>> getNoticeList() {
        List<Notice> notices = noticeRepository.findAll();
        return Optional.of(notices);
    }
}
