package com.microservices.practice.department.vo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("FilteringBeanFilter")
public class FilteringBean {

    private String field1;
    private String field2;

    /*This is static filtering using @JsonIgnore it will remove this field while returning response
            in all methods
      For Dynamic Filtering we need to implement MappingJacksonValue filtering in methods and annotate the class with filter name*/
    /*@JsonIgnore*/
    private String field3;
}
