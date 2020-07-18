package com.candy.mz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: candy
 * @date: 2020/7/18
 * @description :
 **/

@RequestMapping("user")
@RestController
public class HelloWorldController {

    @GetMapping(value = "helloWorld")
    public String helloWorld() {
        return "success";
    }


}
