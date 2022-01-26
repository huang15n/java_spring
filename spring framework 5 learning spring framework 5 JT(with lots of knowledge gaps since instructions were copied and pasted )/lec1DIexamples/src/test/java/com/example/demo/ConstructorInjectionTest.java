package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstructorInjectionTest {

    ConstructorInjection constructorInjection;

    @BeforeEach
    void setUp() {
        constructorInjection = new ConstructorInjection(new ClassImplementInterface());
    }

    @Test
    void method() {
        System.out.println(constructorInjection.callMethod());
    }
}