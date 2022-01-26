package com.uwindsor.joke.service;


import org.springframework.stereotype.Service;

@Service
public class NumberServiceImpl implements NumberService {


    private RandomNumber randomNumber;

    public NumberServiceImpl(RandomNumber randomNumber) {
        this.randomNumber = randomNumber;
    }

    @Override
    public String getNumber() {
        return randomNumber.getRandomNumber();
    }
}
