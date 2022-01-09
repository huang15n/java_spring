package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class ConstructorInjectionImplementsInterface implements  Interface{


    @Override
    public String method() {
        return "this is for the constructor injection but it may cause confusion -- Constructor";
    }
}
