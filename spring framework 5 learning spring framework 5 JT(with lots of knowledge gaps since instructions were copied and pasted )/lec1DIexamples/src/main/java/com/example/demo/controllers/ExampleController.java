package com.example.demo.controllers;


import org.springframework.stereotype.Controller;

@Controller
public class ExampleController {

    public String message(){
        return "this is the message from controller but it is integrated into the bean now";
    }


}
