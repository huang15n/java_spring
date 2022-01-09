package com.example.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Primary
@Component
public class PrimaryClassImplementsInterface implements  Interface{
    @Override
    public String method(){

        return "this is the primary bean that implements the interface - Primary";

    }



}
