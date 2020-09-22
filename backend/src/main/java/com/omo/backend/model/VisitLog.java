package com.omo.backend.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

//@Document(collation = "visitor")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VisitLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupCode;
    private String name;

    @CreationTimestamp
    private LocalDateTime visitTime;

    @Builder
    public VisitLog(String groupCode, String name) {
        this.groupCode = groupCode;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "groupCode='" + groupCode + '\'' +
                ", name='" + name + '\'' +
                ", visitTime=" + visitTime +
                '}';
    }
}
