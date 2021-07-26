package com.microservices.practice.department.service;

import com.microservices.practice.department.entity.Department;
import com.microservices.practice.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment method of Department Service");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("Inside findDepartmentById method of Department Service");
        return departmentRepository.findByDepartmentId(departmentId);
    }

    /*@Transactional annotiation is used since JPA need transaction for save/delete operations
    * Alternate way to delete using entity manager
    * https://howtodoinjava.com/jpa/jpa-remove-delete-entity-example/*/

    @Transactional
    public Long deleteDepartmentById(Long departmentId) {
        log.info("Inside deleteDepartmentById method of Department Service");
        return departmentRepository.deleteByDepartmentId(departmentId);
    }

    public List<Department> findAll() {
        log.info("Inside findAll method of Department Service");
        return departmentRepository.findAll();
    }
}
