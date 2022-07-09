package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class TestController {

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String connectA(){
        return "hello, A";
    }

    @RequestMapping(value = "/b", method = RequestMethod.GET)
    public String connectB(){
        return "hello, B";
    }

    @RequestMapping(value = "/c", method = RequestMethod.GET)
    public String connectC(){
        return "hello, C";
    }
}
