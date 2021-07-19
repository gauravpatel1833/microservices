package com.microservices.practice.user.service;

import com.microservices.practice.user.entity.User;
import com.microservices.practice.user.repository.UserRepository;
import com.microservices.practice.user.vo.Department;
import com.microservices.practice.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        Department department = null;
        if(user!=null) {
            /*DEPARTMENT-SERVICE is the application name of MS registered in service registory of Eureka Server*/
            department =
                    restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmenId(), Department.class);
        }

        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
