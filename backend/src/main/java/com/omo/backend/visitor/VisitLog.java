package com.omo.backend.visitor;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VisitLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupCode;
    private String name;
    private String address;
    private String phone;

    @CreationTimestamp
    private LocalDateTime visitTime;

    @Builder
    public VisitLog(String groupCode, String name, String phone, String address) {
        this.groupCode = groupCode;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "VisitLog{" +
                "groupCode='" + groupCode + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", visitTime=" + visitTime +
                '}';
    }
}
