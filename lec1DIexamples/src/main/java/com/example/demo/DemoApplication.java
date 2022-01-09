package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication


public class DemoApplication {

	public static void main(String[] args) {



		ApplicationContext applicationContext =  SpringApplication.run(DemoApplication.class, args);
		// never in my code we say new here

		System.out.println("-------------same qualfiers but using profile ---------------");

		ConstructorInterfaceInjection constructorInterfaceInjection = (ConstructorInterfaceInjection) applicationContext.getBean("constructorInterfaceInjection");
		System.out.println(constructorInterfaceInjection.callMeg());







	}

}
