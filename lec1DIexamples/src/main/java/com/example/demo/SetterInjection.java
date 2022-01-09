package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class SetterInjection {
    public Interface anInterface;


    @Qualifier("setterInjectionImplementsInterface")
    @Autowired
    public void setAnInterface(Interface anInterface){
        this.anInterface = anInterface;
    }

    public String callMethod(){
        return anInterface.method();
    }
}
