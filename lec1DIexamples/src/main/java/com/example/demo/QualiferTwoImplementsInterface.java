package com.example.demo;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("TWO")
@Component("qualifierWithSameName")
public class QualiferTwoImplementsInterface implements Interface {

    @Override
    public String method(){
        return "this is the second class that uses the same qualifier";
    }
}
