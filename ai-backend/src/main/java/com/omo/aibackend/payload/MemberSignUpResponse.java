package com.omo.aibackend.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpResponse implements Serializable {
    private String name;
    private String phone;
    private String address;
}
