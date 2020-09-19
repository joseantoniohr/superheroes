package com.timplesoft.springboot.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/hello-world")
    public String helloWorld() {
        return messageSource.getMessage("hello.world.message", null, LocaleContextHolder.getLocale());
    }

    @GetMapping(value = "/hello-world-bean")
    public BeanResponse helloWorldBean() {
        return new BeanResponse("Hello World Bean!");
    }

    @GetMapping(value = "/hello-world/path-variable/{name}")
    public String helloWorldVariable(@PathVariable String name) {
        return "Hello World " + name + "!";
    }

}
