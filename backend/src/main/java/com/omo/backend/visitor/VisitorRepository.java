package com.omo.backend.visitor;

import com.omo.backend.visitor.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<VisitLog, Long> {

    VisitLog findByName(String name);
    List<VisitLog> findByGroupCode(String groupCode);
    List<VisitLog> findByGroupCodeAndVisitTime(String groupCode, LocalDateTime time);
}
