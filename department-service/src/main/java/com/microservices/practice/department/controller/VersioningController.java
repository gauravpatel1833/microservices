package com.microservices.practice.department.controller;

import com.microservices.practice.department.vo.versioning.Name;
import com.microservices.practice.department.vo.versioning.StudentV1;
import com.microservices.practice.department.vo.versioning.StudentV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    /*Below are 4 approaches for versioning in RestController
    1. URL based  - Used by GIT hub
    2. Parameter Based - Amazon
    3. Header Based - Microsoft
    4. Media Type or produces - Github

    None is perfect as URL and parameter one cause URI pollution new urls.
    Header and Media can cause misuse of headers and caching problem as same url
    so it need to identify header,parameters as well.
    Also user who use web browser will not be able to use directly.*/


    /* 1. Below two methods are classic example of URL versioning where you change the url
    * and consumer can use url based on there needs*/

    @GetMapping("/v1/student")
    public StudentV1 getStudentV1(){
        return new StudentV1("Gaurav Patel");
    }

    @GetMapping("/v2/student")
    public StudentV2 getStudentV2(){
        return new StudentV2(new Name("Gaurav","Patel"));
    }


    /* 2. Below two methods are example of param based versioning where you pass the url parameter
     * and based on that parameter the method will be invoked
     * http://localhost:9001/student?version=2*/

    @GetMapping(value = "/student" , params = "version=1")
    public StudentV1 getParamStudentV1(){
        return new StudentV1("Gaurav Patel");
    }

    @GetMapping(value = "/student" , params = "version=2")
    public StudentV2 getParamStudentV2(){
        return new StudentV2(new Name("Gaurav","Patel"));
    }

    /* 3. Below two methods are example of header based versioning
       where you pass the header parameter
     * and based on that parameter the method will be invoked*/

    @GetMapping(value = "/student" , headers = "X-API-VERSION=1")
    public StudentV1 getHeaderStudentV1(){
        return new StudentV1("Gaurav Patel");
    }

    @GetMapping(value = "/student", headers = "X-API-VERSION=2")
    public StudentV2 getHeaderStudentV2(){
        return new StudentV2(new Name("Gaurav","Patel"));
    }

    /* 4. Below two methods are example of produces based versioning i.e mimetype
        where you pass the Accept parameter in header
     * and based on that parameter the method will be invoked*/

    @GetMapping(value = "/student" , produces = "application/vnd.company.app-v1+json")
    public StudentV1 getProducesStudentV1(){
        return new StudentV1("Gaurav Patel");
    }

    @GetMapping(value = "/student", produces = "application/vnd.company.app-v2+json")
    public StudentV2 getProducesStudentV2(){
        return new StudentV2(new Name("Gaurav","Patel"));
    }


}
