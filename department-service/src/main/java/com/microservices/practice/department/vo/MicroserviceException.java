package com.microservices.practice.department.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MicroserviceException {

    private Date errorTime;
    private String errorCode;
    private String errorResponse;
    private String errorDetails;
}
