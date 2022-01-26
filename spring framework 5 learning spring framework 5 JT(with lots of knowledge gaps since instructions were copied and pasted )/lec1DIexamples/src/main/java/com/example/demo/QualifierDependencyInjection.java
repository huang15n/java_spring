package com.example.demo;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QualifierDependencyInjection {

    private final Interface anInterface;


    public QualifierDependencyInjection(@Qualifier("qualifierWithSameName") Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String callMethod(){
       return anInterface.method();
    }
}
