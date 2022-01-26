package com.example.demo.controllers;


import com.example.demo.Interface;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("ONE")
@Component("qualifierWithSameName")
public class QualiferOneImplementsInterface implements Interface {
    @Override
    public String method(){
        return "this is the first class that uses the same qualifier";
    }
}
