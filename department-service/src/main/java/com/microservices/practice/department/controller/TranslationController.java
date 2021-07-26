package com.microservices.practice.department.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/translations")
@Slf4j
public class TranslationController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/fetch")
    public String getHelloWorldInternationalized(){

        /*This method will read message properties file for given local messages.properties
                and return the text from the property file
                second argument null is passed as property defined in file is not parameterized
          It works on based on Accept-Language received from request header*/
        return messageSource.getMessage("com.hello.world",null,
                LocaleContextHolder.getLocale());
    }
}
