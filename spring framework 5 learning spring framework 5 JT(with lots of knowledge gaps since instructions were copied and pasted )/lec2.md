# Spring Depdency injection DI and IOC


we are getting into the absolute core of spring cores and this is where Spring shines 



the cornerstone of spring framework is the dependency injection, it is like blocking and tackling in football, the swing in golf. you have to get these fundamentals stuff down otherwise you are going to really struggle with 

all circle of spring projects really depend on the stuff 

SOLID OOP principle really dovetails with it very nicely. a joint formed by one or more tapered projections (tenons) on one piece which interlock with corresponding notches or recesses (mortises) in another.

both of these tool sets to help you to write better code, we will get in depth, exampls of experiences and legacy code bases and why these ar bad and why you want to be utilizing them in your software development , it will lead you to produce better code further down the road using spring frameowkr 


## SOLID principles 
it is credited with coming up with the SOLID acronym 

OOP is a powerful concept but it is not the magic path to high quality software, OOP does nto always lead to quality software, the principles foucs on depedency management 

poor dependency management leads to code that is brittle, fragile, and hard to change 
proper dependency management leads to quality code that is easy to maintain, when you violate these rules, you get yourself into trouble



### S -- Single responsibility principles 
just because you can does not mean you should 

1. every class should have a single responsibiity 
there should never be more than one reason for a class to change 
2. your classes should be small, or more than a screen full of code, as rule of thumb 
3. avoid "God" classes
4. split big classes into smaller classes , it is too many variances and you are going to get proper test coverage out of it 

they feel too much overhead to create another class and trust me, obejct creation in java is very cheap,relatively speaking, your code quality and maintainability is far more important 



### O - Open Close principle
open chest surgey is not needed when putting on a coat 

1. you classes should be open for extension but closed for modification 
2. you should be able to extend a class behaviour, without modifiying it. you should not be exposing getters and getters 
3. use private variable with getters and setters -- only when you need them 
4. use abstract base classes, so you will see in that code like Entity class that was POJO, also specify using abstract methods which you expect to be implemented when you encapsulate the common code inside that abstract base class 



### LISOV Substitution principle 
if it looks like a duck, quacks like a duc. but need batteries -- you probably have the wrong abstraction 

1. objects in a program would be replaceable with instances of their subtype WITHOUT altering the correctness of the programming 
2. violkations will often fail the IS A test 
3. a square is a rectangle. however, a rectangle is not a square 


some of the logic will be change and you would get different results because you are failing that "IS A" test. when you extends your class, you are not introducing different behavior 




### Interface segregation principle 
1. make fine grained interfaces that are client speicific 
2. many client specific interfaces are better than one general purpose interface 
3. keep your component focused and minimize depedencies between them 
4. notice relationship to the single responsibility principles 
i.e. avoid god interfaces 

sometimes people will try to make the interfaces just too generic and they will start overloading methods or just really making API overly complex when ideally there should be more than one interface there, avoid those general purpose interfaces, once you start doing that , it becomes a very slippery slope 


###  Dependency Inversion Principle 
would you solder a lamp directly to the electrical wiring in a wall rather than a plug? 

it takes you a while to fully grasp here 

a dependency inversion principle 
1. abstractions should not depend upon details 
2. details should depend on abstractions 
3. important that higher level or lower level objects depend on the same abstract interaction 
4. this is not the same as dependency injection -- which is how objects obtain dependent objects 
this is trying to make a distinction between the higher level and lower level obejcts. so it is not how they are composed 


in summary, the code will become more testable and it is going to be easier to maintain, you should avoid tight coupling in your code, avoid massive classes of these overly complex god classes and interfaces and keepo things very concise 


## SOLID Pinciple in details 


# TODO 


## Interface Naming Convetion 

- interface should be a good object name e.g. List Interface
implementations - ArrayList, CheckedList, SingletonList
- DO NOT start with I e.g. No IName IList

### implementation naming 
1. when use just one implementation really accepted to usert <Interface Name>  + implmentation 
2. when more than one, name should indicate difference of implementation 


you are using that interface to generate mocks or stubs and things like thtt for your test framework then maybe you want to give those implementations a name or just happen to use a mock 

this is a lot of votality well from a standpoint of developing and what we care about htings we do not care about iplementation 
it may counter others but I do see this generally accepted in the indsury 


```java

package com.example.demo;

public interface SomeInterface {
}

package com.example.demo;

public class SomeClassImplementsInterface extends  SomeInterface{
}


package com.example.demo;

public class ExampleController {

    private SomeInterface someInterface;

    public ExampleController(SomeInterface someInterface) {
        this.someInterface = someInterface;
    }
}



```

there is a lot opinions on this 
I want to use JPA to return object from a database 
but just to get your head around how this is being worked 
now I allow spring to wire in, I am not going to get down to that nitty gritty here , using this for illustrative purposes 

I know this is related to implementing something, I hopethis clears up 
















### Spring Dependency Injection 

go through the recommended workflow, step through it 

you can think of Spring ApplicationContext is all around your app, when spring starts up it is going to be looking at your project and have annotated dependencies. you might have configuration files 


conceputally start remember that is not just your code that is executing, you have this context that spring is going to be managing components. as it finds components, spring beans, those beans are going to get constructed and become available in the context 






###### you need to click the pom.xml file and add that as the Maven project


download this: https://start.spring.io/
get rid of junit 4 test. we bring up our repository instances, spring will inject an instance of that repository into our controller at runtime 



create a new controller : 

```java
package com.example.demo.controllers;


import org.springframework.stereotype.Controller;

@Controller
public class ExampleController {

    public String message(){
        return "this is the message from controller but it is integrated into the bean now";
    }


}


```

in the main java:

```java

package com.example.demo;

import com.example.demo.controllers.ExampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {



		ApplicationContext applicationContext =  SpringApplication.run(DemoApplication.class, args);
		// never in my code we say new here

		ExampleController exampleController = (ExampleController) applicationContext.getBean("exampleController");

		String msg = exampleController.message();
		System.out.println(msg);

	}

}


```


what is under these covers is that spring is creating inside the ApplciationConext, spring effectively saying create a new instance of this class, put it into the context, you want an instance of this so from the context provide me an instance of this bean 

the Spring Framwork is managing the construction of controller, spring is creating it so we are not "new"ing an object there. 


probably butchering that, you may leave the door open, your classes are going to have dependency and you are asking the framework to provide that dependency. the framework is going to provide that needed object for you 

dependency injection is where a needed dependency is injected by another object. the class being injected has no repsonsibilities in instantiating that object being injected 

think about a database connection that is a fairly expensive and complex object to create and this is an ideal thing that you can have injected so your classes at runtime that is getting data from the database does not necessarily have to care if it is getting from mysql database, h2 database or some other database 

so this allows your classes to be abstracted so it is only asking for that data source so it does not care about the configuration fo that data source that is eing pushed off to the framework to manage that object for you 


#### types of dependencies 
1. by class properties -- least preferred, can be public or private properties/ using private properties is evil, it does take extra works, so it does create some headaches 



2. by stters -- are of much debtate 

3. by constructor -- most preferred 



#### concrete classes versus interfaces 
DI can be done with concrete classes or with intefaces 

generally DI with concrete classes should be avoid 

DI via interfaces is highly preferred, it
1. allows runtime to decide implementation
2. follows interface segration principle of SOLID
3. makes your code more testable 

that implementation of data source can be swapped out because you are using an inteface 



## Inversion of Controller -- IOC 

it is a techinque to allow dependencies to be injected at ruintime 
dependencies are not predetermined 

one important characteristic of a framwork is that the mehods defined by the user to tailor the framework will often be called from within the framework itself. rather than ffrom the user app code. the framework often plays the role in the main program in coordinating and sequencign app activity. this inversion of control gives frameworks the power to serve as extensible skeltons. the method supplied by the user tailor the generic algorithms defined in the framework for a particular app 

this is basically saying the the framework is going to control the most of the activity bringing up the structure 
you are providing code to fulfill the business logic so that is where the spring framework becoms so powerful 


### difference between IoC versus Dependency Injection 
IoC and DI are easily confused 
- DI refers much to the composition of your classes . i.e you compose your classes with DI in mind 

- IoC is the runtime environment for your code. i.e. Spring Framework's IoC container 
spring is in control of the injection of dependencies 


Dependency injection is how you write your classes, inversion of control is the runtime when the injection is actually happening, so the inversion control determines what object to actually inject 




### Best pratices with dependency injection 
1. favor using constructor injection over setter injection
2. use final properties for injected components 
3. whenever pratical, code to an interface 




### examples of dependency injection without spring framework 


create an interface 
```java

package com.example.demo;

public interface Interface {

    String method();
}

```

implemen the interface 
```java
package com.example.demo;

public class ClassImplementInterface implements Interface {
    @Override
    public String method() {
        return "hello this is me from Class Implements Interface that is not from the class that has been injected";
    }
}



```



##### property based dependency injection class:

```java

package com.example.demo;

public class RegularDependencyInjection {

    public Interface anInterface;

    public String callMethod(){
        return anInterface.method();
    }
}


```

right click on intellij and Create Test 

```java
package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularPropertyDependencyInjectionTest {

    RegularPropertyDependencyInjectionTestregularDependencyInjection;

    @BeforeEach
    void setUp(){
        regularPropertyDependencyInjection = new RegularPropertyDependencyInjectionTest();
        regularDependencyInjection.anInterface= new ClassImplementInterface();

    }

    @Test
    void callMethod() {

        System.out.println(regularDependencyInjection.callMethod());
    }
}

```

we are mimicking what the spring framework is doing 
actually creating the object 

we are doing this by properties and this is not the way we want to be doing 



add setter method
```java
package com.example.demo;

public class SetterInjection {
    public Interface anInterface;

    public void setAnInterface(Interface anInterface){
        this.anInterface = anInterface;
    }

    public String callMethod(){
        return anInterface.method();
    }
}


```



test class:

```java
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

```


#### constructor based injection 

create a class 
```java
package com.example.demo;

public class ConstructorInjection {

    private final Interface anInterface;

    public ConstructorInjection(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String method(){
        return anInterface.method();
    }


}


```


create a test:



```java

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
        System.out.println(constructorInjection.method());
    }
}

```


we are kind of acting as inversion control in the set up methods of in these setUp() methods 



## Dependency Injection using Spring Framework 

we will be setting up those classes constructing clases and doing the dependency injection ourselves via the setup inside of a test 


some common gotaches very easy to make mistakes 
```java

package com.example.demo;

import com.example.demo.controllers.ExampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {



		ApplicationContext applicationContext =  SpringApplication.run(DemoApplication.class, args);
		// never in my code we say new here

		System.out.println("--------------property based injection ---------------");

		RegularDependencyInjection regularDependencyInjection = (RegularDependencyInjection) applicationContext.getBean("regularDependencyInjection");
	    System.out.println(regularDependencyInjection.callMethod());



	}

}

/**
- [           main] com.example.demo.DemoApplication         : Starting DemoApplication using Java 17.0.1 on DESKTOP-KT5L3F9 with PID 5652 (C:\Users\Eddie\Desktop\demo\target\classes started by Eddie in C:\Users\Eddie\Desktop\demo)
2022-01-04 14:42:10.113  INFO 5652 --- [           main] com.example.demo.DemoApplication         : No active profile set, falling back to default profiles: default
2022-01-04 14:42:12.539  INFO 5652 --- [           main] com.example.demo.DemoApplication         : Started DemoApplication in 3.977 seconds (JVM running for 5.429)
--------------property based injection ---------------
Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'regularDependencyInjection' available
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)
	at com.example.demo.DemoApplication.main(DemoApplication.java:20)

*/


```


I need to create @Controller annotation 

```java
package com.example.demo;


import org.springframework.stereotype.Controller;

@Controller
public class RegularDependencyInjection {

    public Interface anInterface;

    public String callMethod(){
        return anInterface.method();
    }
}

/**


2022-01-04 14:43:44.726  INFO 6384 --- [           main] com.example.demo.DemoApplication         : Starting DemoApplication using Java 17.0.1 on DESKTOP-KT5L3F9 with PID 6384 (C:\Users\Eddie\Desktop\demo\target\classes started by Eddie in C:\Users\Eddie\Desktop\demo)
2022-01-04 14:43:44.738  INFO 6384 --- [           main] com.example.demo.DemoApplication         : No active profile set, falling back to default profiles: default
2022-01-04 14:43:46.628  INFO 6384 --- [           main] com.example.demo.DemoApplication         : Started DemoApplication in 3.566 seconds (JVM running for 5.021)
--------------property based injection ---------------
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.example.demo.Interface.method()" because "this.anInterface" is null
	at com.example.demo.RegularDependencyInjection.callMethod(RegularDependencyInjection.java:12)
	at com.example.demo.DemoApplication.main(DemoApplication.java:21)

Process finished with exit code 1
*/

```

we got null pointer exception so we need to use @Autowired annotation at he property 
```java
package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegularDependencyInjection {

    @Autowired
    public Interface anInterface;

    public String callMethod(){
        return anInterface.method();
    }
}


```




```java
package com.example.demo;


import org.springframework.stereotype.Service;

@Service
public class ClassImplementInterface implements Interface {
    @Override
    public String method() {
        return "hello this is me from Class Implements Interface that is not from the class that has been injected";
    }
}


package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class ClassImplementInterface implements Interface {
    @Override
    public String method() {
        return "hello this is me from Class Implements Interface that is not from the class that has been injected";
    }
}


package com.example.demo;


import org.springframework.stereotype.Controller;

@Controller
public class ClassImplementInterface implements Interface {
    @Override
    public String method() {
        return "hello this is me from Class Implements Interface that is not from the class that has been injected";
    }
}


```
spring will fail to start, we are trying to autowire something but there is no instance of that 
but effectively there is no effective functional difference between them @Controller, @Service or @Component 
it is just for you to show your intention of how it is going to be utilized. the framework does not really make much of a distinction between these 

by annotating it as a @Service, @Controller, @Component, spring knwos to bring an instance of this into its context 


```java
package com.example.demo;

import com.example.demo.controllers.ExampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {



		ApplicationContext applicationContext =  SpringApplication.run(DemoApplication.class, args);
		// never in my code we say new here

		System.out.println("--------------setter based injection ---------------");
		SetterInjection setterInjection = (SetterInjection) applicationContext.getBean("setterInjection");

		System.out.println(setterInjection.callMethod());





	}

}


```


```java

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SetterInjection {
    public Interface anInterface;


    @Autowired
    public void setAnInterface(Interface anInterface){
        this.anInterface = anInterface;
    }

    public String callMethod(){
        return anInterface.method();
    }
}

```




#### constructor based 

```java

package com.example.demo;

import com.example.demo.controllers.ExampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {



		ApplicationContext applicationContext =  SpringApplication.run(DemoApplication.class, args);
		// never in my code we say new here

		System.out.println("--------------constructor based injection ---------------");
		ConstructorInjection constructorInjection = (ConstructorInjection) applicationContext.getBean("constructorInjection");
		System.out.println(constructorInjection.method());





	}

}

```
make sure getBean("constructorInjection"); is not getBean("constructorInjection "); because space matters 



for constructor injection, spring does not need @AutoWired annotation at all 

```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConstructorInjection {


    private final Interface anInterface;

    public ConstructorInjection(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String method(){
        return anInterface.method();
    }


}


```



we have beans to be brought the the context 



## Using @Qualifiers 

we have one implementation for the interface, what if we have constructor, property and setter injected controllers we want to inject a specific method, what we can do is to use the @Qualifer annotation 



if you created multiple implementations 
```java
package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class SetterInjectionImplementsInterface implements Interface{

    @Override
    public String method() {
        return "this is for the setter injection but it may cause confusion -- Setter";
    }
}

package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class ConstructorInjectionImplementsInterface implements  Interface{


    @Override
    public String method() {
        return "this is for the constructor injection but it may cause confusion -- Constructor";
    }
}


/***


Description:

Parameter 0 of constructor in com.example.demo.ConstructorInjection required a single bean, but 3 were found:
	- classImplementInterface: defined in file [C:\Users\Eddie\Desktop\demo\target\classes\com\example\demo\ClassImplementInterface.class]
	- constructorInjectionImplementsInterface: defined in file [C:\Users\Eddie\Desktop\demo\target\classes\com\example\demo\ConstructorInjectionImplementsInterface.class]
	- setterInjectionImplementsInterface: defined in file [C:\Users\Eddie\Desktop\demo\target\classes\com\example\demo\SetterInjectionImplementsInterface.class]


Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed


Process finished with exit code 1

*/


```






###### so when we do annotated components like that, the bean is going to be the class name but with a leading lower case 



##### @Qualifer("className")


```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ConstructorInjection {


    private final Interface anInterface;

    public ConstructorInjection(@Qualifier("constructorInjectionImplementsInterface") Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String callMethod(){
        return anInterface.method();
    }


}


package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class SetterInjection {
    public Interface anInterface;


    @Qualifier("setterInjectionImplementsInterface")
    @Autowired
    public void setAnInterface(Interface anInterface){
        this.anInterface = anInterface;
    }

    public String callMethod(){
        return anInterface.method();
    }
}


```



I have qualifiers so spring knows which one of these beans to wire into which controller. we have given spring hint which one we want to inject to 


```java
package com.example.demo;

import com.example.demo.controllers.ExampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {



		ApplicationContext applicationContext =  SpringApplication.run(DemoApplication.class, args);
		// never in my code we say new here

		System.out.println("--------------constructor based injection ---------------");
		ConstructorInjection constructorInjection = (ConstructorInjection) applicationContext.getBean("constructorInjection");
		System.out.println(constructorInjection.callMethod());


		System.out.println("--------------setter based injection ---------------");
		SetterInjection setterInjection = (SetterInjection) applicationContext.getBean("setterInjection");
		System.out.println(setterInjection.callMethod());





	}

}


```





so it throws an error when it could make a determination which one to add, we put the qualifier hint 





## @Primary 

with @Qualifiers we can select which bean we want to use , now there is another way we can control this, with primary bean annotation, this is when we have the beans with same type. 

well when in doubt just go ahead and use this one as the primary beans. so you can have several beans of the same type as we do now and we can designate one of them as primary and then the candidates for that are having their dependency injected into them . 


```java

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


```


when there is any situation where is no qualfier, this is the situation that we want to control for 

now we see beans got wired up 


```java

package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class PrimaryWithoutQualifierInjection {
    private final Interface anInterface;

    public PrimaryWithoutQualifierInjection(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String method(){
        return anInterface.method();
    }



}

```




```java
package com.example.demo;

import com.example.demo.controllers.ExampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {



		ApplicationContext applicationContext =  SpringApplication.run(DemoApplication.class, args);
		// never in my code we say new here

		System.out.println("--------------primary without qualifier based injection ---------------");
		PrimaryWithoutQualifierInjection primaryWithoutQualifierInjection = (PrimaryWithoutQualifierInjection) applicationContext.getBean("primaryWithoutQualifierInjection");

		System.out.println(primaryWithoutQualifierInjection.method());






	}

}


```

you can experiment with how @Qualifier and @Primary can work together in controlling how classes get injected into your spring managed components 






## @Profiles 

profiles are probably one of the most powerful feature of spring framework 

if you have multiple qualifiers with same names, spring will get confused

what profiles allow you to do is to have beans in your configuration that will take on different characteristics 


by default when you have an H2 in memory database on your class, spring boot automatically configures that 
but what if you want to have MySQL capabilities as well so you might want to run a profile for MySQL. 

but you are controlling the MySQL specific beans with a profile 

so the profiles are very very powerful thing and probably one of the most commonly used features of dependency injection inside the spring framework 
because these profiles allow you to control your spring app in different runtime environments 


just get our feet wet with profiles, and profiles used a lot more as we get deeper into it 

it now has capability of a profile and you can set a profile and then using inversion of control container framework and spring is going to wire up your app differently depending on the active profiles 

you can think of a case as language and internationalization 

I18N is common for internationalization , so you can create one called that 


```java

package com.example.demo;


import org.springframework.stereotype.Component;

@Component("qualifierWithSameName")
public class QualiferOneImplementsInterface implements Interface{
    @Override
    public String method(){
        return "this is the first class that uses the same qualifier";
    }
}


 


package com.example.demo;


import org.springframework.stereotype.Component;

@Component("qualifierWithSameName")
public class QualiferTwoImplementsInterface implements Interface {

    @Override
    public String method(){
        return "this is the seocnd class that uses the same qualifier";
    }
}

package com.example.demo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QualifierDependencyInjection {

    private final Interface anInterface;


    public QualifierDependencyInjection(@Qualifier("qualifierWithSameName") Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String callMethod(){
       return anInterface.method();
    }
}

/*

Exception in thread "main" java.lang.ClassCastException: class com.example.demo.QualiferTwoImplementsInterface cannot be cast to class com.example.demo.QualifierDependencyInjection (com.example.demo.QualiferTwoImplementsInterface and com.example.demo.QualifierDependencyInjection are in unnamed module of loader 'app')
	at com.example.demo.DemoApplication.main(DemoApplication.java:20)
*/


```


```java


package com.example.demo;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("ONE")
@Component("qualifierWithSameName")
public class QualiferOneImplementsInterface implements Interface{
    @Override
    public String method(){
        return "this is the first class that uses the same qualifier";
    }
}

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

package com.example.demo;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QualifierDependencyInjection {

    private final Interface anInterface;


    public QualifierDependencyInjection(@Qualifier("qualifierWithSameName") Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String callMethod(){
       return anInterface.method();
    }
}

// in applcation.properties file set 
// spring.profiles.active=ONE


/**

***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of constructor in com.example.demo.QualifierDependencyInjection required a bean of type 'com.example.demo.Interface' that could not be found.

The injection point has the following annotations:
	- @org.springframework.beans.factory.annotation.Qualifier("qualifierWithSameName")

The following candidates were found but could not be injected:
	- User-defined bean
	- User-defined bean
	- User-defined bean
	- User-defined bean


Action:

Consider revisiting the entries above or defining a bean of type 'com.example.demo.Interface' in your configuration.


Process finished with exit code 1

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

		QualifierDependencyInjection qualifierDependencyInjection = (QualifierDependencyInjection) applicationContext.getBean("qualifierDependencyInjection");

		System.out.println(qualifierDependencyInjection.callMethod());








	}

}


*/

```
###### the above error occurred when we mistakenly put it in different folder. @Qualifer("nameOfTheQualifier") classes should be in the same package of your main class 




##  Default Profile 
previously if you do not @Profile("ONE") in the @Controller and spring.profiles.active=ONE, spring does not know which profile to wire in. now there is a profile that is available and that is called the default profile, this profile is considered active is no active profiles meant set 
this is a way that we can use to say to the beans belongs to a default profile 


so there are no active profile set, the default profiles beans that are annotated that way will be wired into the context 

so what is this going to allow us to do is to run the app without a profile set so we will be able to take one of those beans set a default profile on it and allow spring to bring that up 

so we do not always hae to specify an active profile for our app 


you can have more than one active profile for a bean 



```
# application.properties 

#spring.profiles.active=ONE
```

```java

package com.example.demo;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile({"TWO","default"})
@Component("qualifierWithSameName")
public class QualiferTwoImplementsInterface implements Interface {

    @Override
    public String method(){
        return "this is the second class that uses the same qualifier";
    }
}

package com.example.demo;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class QualifierDependencyInjection {

    private final Interface anInterface;


    public QualifierDependencyInjection(@Qualifier("qualifierWithSameName") Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String callMethod(){
       return anInterface.method();
    }
}

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

		QualifierDependencyInjection qualifierDependencyInjection = (QualifierDependencyInjection) applicationContext.getBean("qualifierDependencyInjection");

		System.out.println(qualifierDependencyInjection.callMethod());








	}

}

```




if we do not have the deafult profile, it will also show below error when it is not specified 




The injection point has the following annotations:
	- @org.springframework.beans.factory.annotation.Qualifier("qualifierWithSameName")

The following candidates were found but could not be injected:
	- User-defined bean
	- User-defined bean
	- User-defined bean
	- User-defined bean


Action:

Consider revisiting the entries above or defining a bean of type 'com.example.demo.Interface' in your configuration.


Process finished with exit code 1



that is how we use the default profile you just specify that spring component is part of the deault profile when there is no active profile set, that bean will get picked up and used by Spring 

the @Profile in dependency injection to control exactly which implementation we want to injected in in which controller class


through the dependency injection, you can actually set things up utilizing the spring framework to where that messaging layer abstracted 
you have the full control of how the spring inversion control container goes about compsoing your application 
so this is a very powerful concept because your classes are goign to have dependencies and the spring frameworks through inversion controll is going to be determining how to inject those classes so you can see now through different tool sets that we have in controllong how beans are injected and which beans are controleld in different environments 



### Spring bean lifecycle 

instantiate -> populate properties -> call setBeanNameOfBeanNameAware -> call setBeanFactory of BeanFactoryAware -> setApplicationContext of ApplicationContextAware -> Preinitializtaion Bean PostProcessor -> AfterPropertySet of intilazing beans -> Custom Init method -> Post Initialzataion / beanPostProcessors -> Bean Ready use to 



the spring framework creating the beans and gets everythign wired up and then can run for an indefinite amount of time, and then utilmately we are going to have some type of termiantion about ideally unless we just pull the plug on server 


spring bean life cycle 
Container shutdown -> disposable bean's destroy() -> call Custom destroy method -> terminated 

and this is a handy way if you have something like opening a socket and you need to do a clean and grace disconnect 

this is where you'd put that there and at that poitner after the  bean is destroyed or terminated 


#### CallBack Interfaces 
1. spring has two interfaces you can implement for call back event 
2. intializaing.afterPropertiesSet() called after properties are set 
3. DisposableBean.destroy() caleld during bean destruction in shutdown 


#### lifecycle annotation 
spring has two annotations you can us to hook into the bean lifecycle 
@PostConstruct annotation method will be called after the bean has been constructed, but before its returned to the requesting object
@PreDestroy is called just before the bean is destroyed by the cotnainer 



#### Bean Post Processors 
it gives you a means to tap into the spring context life cycle and interact with beans as they are processed 
implement interface BeanPostProcessor 
1. postProcesBeforeInitialzation - called before bean intialziation method 
2. postProcessAfterInitialzation - caleed after bean initialization 

to be honest, we have not actually found a use for these, so it is a cool feature knowing that you can hook into it but in reality it just never had a need to utilize this 


#### Aware itnerface 
spring has over 14 aware interfaces .these are used to access the spring framework infrascture. these are largely used within the framework, rarely used by spring developers, reality wise, just not much of a need for them 


you have a big use case of hooking the beans methods  into lifecyle events 


we are really only scratching the surface here, it is so powerful and gives you so much versatility in the enterprese 



we are just gonna kick the tires 


















