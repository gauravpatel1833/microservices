package com.microservices.practice.department.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.microservices.practice.department.vo.FilteringBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/filtering")
public class DynamicFilteringController {

    /*This is an example to showcase how dynamic filtering of fields can be implemented*/
    @GetMapping("/")
    public MappingJacksonValue retrieveSomeBean(){
        /*Initialize Object of sample bean*/
        FilteringBean filteringBean = new FilteringBean("value1", "value2", "value3");

        /*Implement a Bean filter to denote the fields names to be kept rest will be filtered out*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        /*Implement SimpleFilterProvider which take bean with filter name "FilteringBeanFilter"
        on bean level as @jsonFilter and filter with fields to be kept*/
        FilterProvider filters = new SimpleFilterProvider().addFilter("FilteringBeanFilter",filter);

        /*Define the object which need to be mapped or fields to be filtered and filters*/
        MappingJacksonValue mapping = new MappingJacksonValue(filteringBean);
        mapping.setFilters(filters);

        /*Return type of these should have to be MappingJacksonValue to have these filters working
                if you directly return bean then the filters are not going to work on actual object*/
        return mapping;
    }

    @GetMapping("/list")
    public MappingJacksonValue retrieveSomeBeanList(){
        List<FilteringBean> filteringBeanList = Arrays.asList(new FilteringBean("value1", "value2", "value3"),
                new FilteringBean("value4", "value5", "value6"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("FilteringBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(filteringBeanList);
        mapping.setFilters(filters);

        return mapping;
    }

}
