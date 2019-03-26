package com.eyas.parent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OkController {

    @RequestMapping("/ok")
    public String ok(){
        return "ok";
    }
}
