package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularDependencyInjectionTest {

    RegularDependencyInjection regularDependencyInjection;

    @BeforeEach
    void setUp(){
        regularDependencyInjection = new RegularDependencyInjection();
        regularDependencyInjection.anInterface= new ClassImplementInterface();

    }

    @Test
    void callMethod() {

        System.out.println(regularDependencyInjection.callMethod());
    }
}