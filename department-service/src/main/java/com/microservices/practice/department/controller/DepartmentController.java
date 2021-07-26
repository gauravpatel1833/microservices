package com.microservices.practice.department.controller;

import com.microservices.practice.department.entity.Department;
import com.microservices.practice.department.service.DepartmentService;
import com.microservices.practice.department.vo.DepartmentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /*@Valid annotiation is adding for input validation from spring-boot-starter-validation
    where on pojo fields you can define annotiation and error messages for validation*/

    @PostMapping("/")
    public Department saveDepartment(@Valid @RequestBody Department department){
        log.info("Inside saveDepartment method of Department Controller");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/")
    public List<Department> getAllDepartments(){
        log.info("Inside getAllDepartments method of Department Controller");
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId){
        log.info("Inside findDepartmentById method of Department Controller");
        Department department = departmentService.findDepartmentById(departmentId);
        if(department == null){
            throw new DepartmentNotFoundException("Department not found for id:"+departmentId);
        }
        return department;
    }

    /*Implementing hateoas to have respone with link to other apis you can use a wrapper EntityModel
    * to avoid changing the actual object and without hardcoding the url links*/
    @GetMapping("/{id}/hateoas")
    public EntityModel<Department> findDepartmentByIdHateOAS(@PathVariable("id") Long departmentId){
        log.info("Inside findDepartmentByIdHateOAS method of Department Controller");
        Department department = departmentService.findDepartmentById(departmentId);
        if(department == null){
            throw new DepartmentNotFoundException("Department not found for id:"+departmentId);
        }

        EntityModel<Department> model = EntityModel.of(department);

        /*This will scan the url link path and add in the response for getAllDepartments method*/
        WebMvcLinkBuilder linkToDepartments =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllDepartments());

        model.add(linkToDepartments.withRel("all-departments"));
        return model;
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable("id") Long departmentId){
        log.info("Inside deleteDepartmentById method of Department Controller");
        Long deleteDepartmentId = departmentService.deleteDepartmentById(departmentId);

        /*Returning void will be like sending 200 or you can return No Content*/
        //return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //return ResponseEntity.noContent();
    }
}
