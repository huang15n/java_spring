package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class ConstructorInterfaceImpl implements ConstructorInterface{

    @Override
    public String printMsg() {

        return "this will be injected";
    }
}
