package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ConstructorInjection {


    private final Interface anInterface;

    public ConstructorInjection(@Qualifier("constructorInjectionImplementsInterface") Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String callMethod(){
        return anInterface.method();
    }


}
