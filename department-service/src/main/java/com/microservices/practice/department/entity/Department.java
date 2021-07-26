package com.microservices.practice.department.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @Size(min = 2,message = "Department name should have at least 2 characters")
    private String departmentName;

    @Size(min = 3,message = "Department Address should have at least 3 characters")
    private String departmentAddress;
    private String departmentCode;
}
