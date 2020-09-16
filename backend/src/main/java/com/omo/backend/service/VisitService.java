package com.omo.backend.service;

import com.omo.backend.model.Visitor;
import com.omo.backend.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    @Autowired
    private VisitorRepository visitorRepository;

    public Visitor writeGuestBook(String name) {
        Visitor visitor = visitorRepository.save(new Visitor(name));
        return visitor;
    }

}
