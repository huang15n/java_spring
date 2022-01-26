package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class RegularDependencyInjection {

    @Qualifier("classImplementInterface")
    @Autowired
    public Interface anInterface;

    public String callMethod(){
        return anInterface.method();
    }
}
