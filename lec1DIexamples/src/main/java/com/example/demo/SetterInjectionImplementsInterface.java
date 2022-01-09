package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class SetterInjectionImplementsInterface implements Interface{

    @Override
    public String method() {
        return "this is for the setter injection but it may cause confusion -- Setter";
    }
}
