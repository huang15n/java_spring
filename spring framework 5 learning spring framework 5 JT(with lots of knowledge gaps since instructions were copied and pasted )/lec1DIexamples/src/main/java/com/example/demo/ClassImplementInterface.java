package com.example.demo;


import org.springframework.stereotype.Controller;

@Controller
public class ClassImplementInterface implements Interface {
    @Override
    public String method() {
        return "hello this is me from Class Implements Interface that is not from the class that has been injected";
    }
}
