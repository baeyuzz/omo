package com.omo.backend.repository;

import com.omo.backend.model.VisitLog;
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
