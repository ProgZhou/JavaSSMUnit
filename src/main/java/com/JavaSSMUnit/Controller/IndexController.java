package com.JavaSSMUnit.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "json";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


}
