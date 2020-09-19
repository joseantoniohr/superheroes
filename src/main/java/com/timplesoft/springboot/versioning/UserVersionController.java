package com.timplesoft.springboot.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVersionController {

    @GetMapping("/userVersioning")
    public UserVersionV1 getUserVersion1() {
        return new UserVersionV1("v1");
    }

}
