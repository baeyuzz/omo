package com.omo.backend.repository;

import com.omo.backend.model.Visitor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitorRepository extends MongoRepository<Visitor, String> {

    public Visitor findByName(String name);
    public List<Visitor> findByVisitTime(LocalDateTime time);
}
