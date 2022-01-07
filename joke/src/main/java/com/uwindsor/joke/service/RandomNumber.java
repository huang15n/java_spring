package com.uwindsor.joke.service;

import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class RandomNumber {

  // you do not need a constructor here

    public String getRandomNumber(){
        StringBuilder bd = new StringBuilder();
        Random rand = new Random();
        return  bd.append(rand.nextInt(1000000)).toString();
    }
}
