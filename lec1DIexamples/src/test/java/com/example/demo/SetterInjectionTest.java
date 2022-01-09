package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetterInjectionTest {

    SetterInjection setterInjection;
    @BeforeEach
    void setUp() {
        setterInjection = new SetterInjection();
        setterInjection.setAnInterface(new ClassImplementInterface());
    }

    @Test
    void method(){
        System.out.println(setterInjection.callMethod());

    }
}