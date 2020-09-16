package com.omo.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collation = "visitor")
public class Visitor {
    @Id
    public String id;
    public String name;
    public LocalDateTime visitTime;

    public Visitor(){}

    public Visitor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + name + '\'' +
                ", visitTime=" + visitTime +
                '}';
    }
}
