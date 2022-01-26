package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class ConstructorInterfaceInjection {


    private final ConstructorInterface constructorInterface;

    @Autowired
    public ConstructorInterfaceInjection(ConstructorInterface constructorInterface) {
        this.constructorInterface = constructorInterface;
    }

    public String callMeg(){
        return constructorInterface.printMsg();

    }



}

