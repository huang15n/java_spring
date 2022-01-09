package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class PrimaryWithoutQualifierInjection {
    private final Interface anInterface;

    public PrimaryWithoutQualifierInjection(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String method(){
        return anInterface.method();
    }



}
