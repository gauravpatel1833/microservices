package com.microservices.practice.department.vo.versioning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    private String firstName;
    private String lastName;
}
