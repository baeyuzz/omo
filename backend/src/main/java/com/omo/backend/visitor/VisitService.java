package com.omo.backend.visitor;

import com.omo.backend.visitor.VisitLog;
import com.omo.backend.visitor.VisitorRequest;
import com.omo.backend.visitor.VisitorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitService {

    private VisitorRepository visitorRepository;

    public Optional<VisitLog> writeGuestBook(VisitorRequest request) {
        VisitLog visitLog = visitorRepository.save(new VisitLog(request.getGroupCode(), request.getName()));
        return Optional.of(visitLog);
    }

    public Optional<List<VisitLog>> getVisitorList(String groupCode) {
        List<VisitLog> visitLog = visitorRepository.findByGroupCode(groupCode);
        return Optional.of(visitLog);
    }

    public Optional<List<VisitLog>> getVisitorListByTime(String groupCode, LocalDateTime time) {
        List<VisitLog> visitLog = visitorRepository.findByGroupCodeAndVisitTime(groupCode, time);
        return Optional.of(visitLog);
    }

}
