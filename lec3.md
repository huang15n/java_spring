# Spring Configuration 


so far we have defined spring 
there are actually a number of different ways to define beans and we take a closer look at configuration options for us to use 

we only touched the surfaces of them and so far 
we are going to configure beans in our tool belt to define spring beans 




## Spring configuration options 

1. XML based configuration : ntroudced in spring framework 2.0 common in legacy spring app, nwer spring app uses java configruation, still suported in spring framework 5.x 
it is around for a long time, it is  readable, you get quite a bit of flexibility with the java options over the xml 

there is no near-term plans to decprecate and remove the xml configuration 

it is rock solid, and mature 


2. annotation based configuration 
introduce in spring 3
spring beans found via Component 
a scan of packages for annotated compnent 
spring beans are found via class level annotations 
@Controlelr, @Service, @Componnt, @Repository 

the app start scanning apps when start up and work appropriately 


3. java based configuration option 
introduce in spring 3
uses java classes to define spring beans 
configruation classes are defined with configuration annotation 
methods returning spring beans declared with @Ben annotation 

if you have soure code that 
you can define as a spring class marked as a configruation class, with configuration option 
and then you can set up that third party component using the bean annotation 

so you do have a java class with methods where you are declaring the beans and you can do anything with it programtically 
you have the power of contrlling it 


4. groovy bean defintion DSL 
in spring 4.0 allwos you to declaer beans via groovy, borrowed from grails 
nice to use but not widely used by the communty 


you can use one or a combiantion of methods 
all will work seamless together to define applciation context 
while xml is still widely used with the legacy code, but the industry trend is to java based 

ideally legacy app should be mirgate based configruation 
newer apps should use java based configruation 


pick one and roll with it 

if nobody touched the xml long in the legacy project, you just use it , but if you are doing new stuff, you can also integerate in some of the java options 


in the back of your head, just think you are loading up the spring context 


that is just different context for spring to get loaded with the component 
so you can use a combination 
if you have a java configruation i would not go and introduce an xml compoent 
that would be a poor use case to do so, it does give you that flexibility to use multiple methods 


favor the java based configruation which we will be fully exploring 



## Spring stereotypes 

stereoptye is a fixed general iamge or set of characteristics which represent a partiuclar type of person or thing
spring stereotypes are classes level annotion define spring beans 
which classes annotated with spring stereotype are detected via the component scan, an instance of the class will be added to the spring context 

available spring stereotypes  @Component, @Controller, @Restcontroller, @Repository , @Service 


there are some negative conotations or associations with that word 


but this is the term that the spring has chosen to describe these stereotype annotaions 

we are scanning over a package of classes and an isntance of that class that has that annotaiton  is brought to that context 
it will be brought up as a spring bean 


@Component 
@Controller   @Repository @Service 
@RestController 

Functionally, there is really not a lot of functional difference because these are going to be brough in as spring beans into context 


there is difference between @Service and @Controller 
that is a very simple way to say that they are really functionally the same 


the @Service is really just helping others so it is not just a component 

@RestController is we want to do @ResponseBody  for convenience 
this is when we are doing JSON payload response  and this is making a distinction 


@Component indicates that an annotated calss is a component and it will be created as a bean 
@Controller indictes an controller class has the role a spring mvc controller 
@RestController convenicen annotaiton extends @Controller and adds @RepnseBody 

@Repository indicates class as a repsoitory as a mechanism for encapsulating storage, retriveal, and search behaviour which emualtes a collection of obejcts 

@Service indicates that an annotation class is a Serice as an operation offered as an interface that stands alone in the modle, with no encapsulated state . Services should not have any state 


they are hornoed in the domain of design, 


## Spring component scan 
spring beans defiend with spring stereotypes are detected with spring component scan 
on start up spring is told to scan packages and for classes with spring stereotype anntoations 
this configruation is spring fraemwork  specifc not spring boot 
spring boot auto configuration will tell spring to form a component scan of the package of the main class, this incldues all subcpackages of the main class package 

#### when using spring boot, if class is outside of the main class package tree, you should and must declare the package scan!!!
those components are picked up by spring component, we look for those current pakage and save us some configuration and out side of our package 


alert you with the component scan 


we will be working off DI 


how this is working is that this class is located  in this class structure in the package 
and by default, it does the component scan  spring does the component scan and all the default packages were picked by the Component Scan 

anything lives outside of this package structure, a very common problem that people run into is when they are begining iwth spring component , spring boot looking in the structure 


### ERROR

1. JDK is not found, probably you spring initializer requires jdk17 and your local machine is 11, reset it 

2. Consider defining a bean of type 'com.example.demo.Interface' in your configuration.
add @Component to your class which implements this Interface


3.  Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'constructorDI' available
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)
	at com.example.demo.DemoApplication.main(DemoApplication.java:12)

Process finished with exit code 1


make sure names are the same 


```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		ConstrutorDI construtorDI = (ConstrutorDI) applicationContext.getBean("construtorDI");

		System.out.println( construtorDI.callMsg());

	}

}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class ConstrutorDI {

    Interface anInterface;

    public ConstrutorDI(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String callMsg(){
        return anInterface.getMsg();
    }


}

package com.example.demo;

public interface Interface {
    String getMsg();

}

package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class ConstructorImplementInterface implements Interface{

    @Override
    public String getMsg() {
        return "this is constructor injection";
    }
}





```

we are getting the proper package name because it is now living in a different package 



### @ComponentScan(basePackage = "")
### @ComponentScan(basePackage = {"package1","package2"})

there is a couple of different configuration options for it, the most common use is going to say : basePackage = "xx"
you specify a package and in that package and everything underneath that package is going to be looked at 



from java standpoint, you have done everything that we need to do, that is working properly is working properly 
we are specifying two base packages, to look in this com.xx.xx and spring will this into another context 




put them in different packages called demo2


### Error 
Exception in thread "main" java.lang.NullPointerException
	at com.example.demo2.SetterDI.callMethod(SetterDI.java:15)
	at com.example.demo.DemoApplication.main(DemoApplication.java:20)

Process finished with exit code 1


if you do not use @Autowired in setter methods 
 


```java
package com.example.demo2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetterDI {
    Interface anInterface;

    @Autowired
    public void setAnInterface(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public String callMethod(){
        return anInterface.getMesg();
    }
}


package com.example.demo2;

public interface Interface {

    String getMesg();
}


package com.example.demo2;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class SetterImplementInterface implements Interface{

    @Override
    public String getMesg() {
        return "this is a setter injection";
    }
}


package com.example.demo;

import com.example.demo2.SetterDI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.demo2","com.example.demo"})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		ConstrutorDI construtorDI = (ConstrutorDI) applicationContext.getBean("construtorDI");

		System.out.println( construtorDI.callMsg());

		SetterDI setterDI = (SetterDI) applicationContext.getBean("setterDI");
		System.out.println(setterDI.callMethod());

	}

}


```

you need to use this when working with other teams because they might bring in another jar into the app this is the time when you specify and look at your app




just to recap with the ComponentScan, looking for classes annotated with this 
one critisim is that when doing component scan, spring startup is doing the reflection and goinng through the classes utilizing java reflection utils to inspect the classes 
traditionally reflection get the name for being a little bit slow, here we only have a few classes not a bad performance impact at all 


that performance impact only happens on app startup 
if you have hundreds of classes that app ripe for refactoring to me 


large app you do get a lot of sprin components in there and that component scan can become a more length process 

that is a huge criticism we need to be pointing out 


we are refactoring the anntoations, which depends on the component scan to the java reflection 



## spring configuration 

we create a folder in the package called config ation, to me it makes a lot of sense to do it that way 
because integration is a more centralized process in your code 



we need to add an annotation called @Configuration and what this tells spring framework is ,  we are going to be doing component scan to define a different beans 

### @Configuration @Bean 
that gives us the method that we can execute during the build up 
to make that a spring component , annotate it with the bean annotation 

if we work with classes we own, typically we would not do this refactoring away from using the sterotype annotation on this, but this is more of a traditional use case for this so would be if we were working with a third party component 


to set up our own obejct map or so we could run a configuration class 

a lot of times if you own the code, you use the sterotyoes 
if you do not own the code, you will be using configuration classe here or some type of special configuration 



@Bean with the java programming language gives us a tremeodous amount of flexibility in our configuration because we can do anything that java can do 

when we return new Class() with bean, we talk about bean names, by default I can actually put a quialifer on here, set up a bean name, but by deafult. the spring framework generated the name for the context you use 



these bean can still run even we took away the annotations 
so we can actually show it to prove it 


when we took out @Component @Controller @Service they are no longer spring managed components, we are not declaring that via annotaitons 

rather we introduce a new configruation class annoted top level class annotion @Configuration 
each bean, we are defiing different sprin beans here with the bean annotation 


just in the @Bean to reitearte, get their name from the method name 


```java

package com.example.demo;

import com.example.demo2.SetterDI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.config","com.example.demo2","com.example.demo"})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		ConstrutorDI construtorDI = (ConstrutorDI) applicationContext.getBean("construtorDI");

		System.out.println( construtorDI.callMsg());

		SetterDI setterDI = (SetterDI) applicationContext.getBean("setterDI");
		System.out.println(setterDI.callMethod());

	}

}



package com.example.config;

import com.example.demo.ConstructorImplementInterface;
import com.example.demo2.SetterImplementInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {

    @Bean
    public ConstructorImplementInterface constructorImplementInterface() {
        return new ConstructorImplementInterface();
    }
    
    @Bean
    public SetterImplementInterface setterImplementInterface(){
        return new SetterImplementInterface();
    }



}
/*
    """
package com.example.demo2;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

 
public class SetterImplementInterface implements Interface{

    @Override
    public String getMesg() {
        return "this is a setter injection";
    }
}

    package com.example.demo;

import org.springframework.stereotype.Component;

public class ConstructorImplementInterface implements Interface{

    @Override
    public String getMsg() {
        return "this is constructor injection";
    }
}

packa


    """

*/





```



## @Primary Beans and @Profiles 


```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("sameName")
public class PrimaryImplementInterface implements  Interface{

    @Override
    public String getMsg() {
        return "this is a @Primary class implements interface";
    }
}


package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PrimaryDI {

    Interface anInterface;

    // how do you know which one is mine? using @Qualifier

    public PrimaryDI(@Qualifier("sameName") Interface anInterface) {
        this.anInterface = anInterface;
    }


    public String callMsg(){
        return anInterface.getMsg();
    }
}
package com.example.demo;


import org.springframework.stereotype.Component;

public class SecondaryImplementInterface implements Interface{

    @Override
    public String getMsg() {
        return "this is the secondary interface that implements class";
    }
}


```





```java

package com.example.demo;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("sameName")
@Profile("TWO")
public class SecondaryImplementInterface implements Interface{

    @Override
    public String getMsg() {
        return "this is the secondary interface that implements class";
    }
}
package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Primary
@Component("sameName")
public class PrimaryImplementInterface implements  Interface{

    @Override
    public String getMsg() {
        return "this is a @Primary class implements interface";
    }
}

```

actually pretty straigthforward the annotations here used are oon the classes, the individual classes with component scan also work inside the inside the @Configuration 


```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PrimaryDI {

    Interface anInterface;

    // how do you know which one is mine? using @Qualifier

    public PrimaryDI( Interface anInterface) {
        this.anInterface = anInterface;
    }


    public String callMsg(){
        return anInterface.getMsg();
    }
}
package com.example.config;

import com.example.demo.ConstructorImplementInterface;
import com.example.demo.PrimaryImplementInterface;
import com.example.demo2.SetterImplementInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MainConfiguration {

    @Bean
    public ConstructorImplementInterface constructorImplementInterface() {
        return new ConstructorImplementInterface();
    }

    @Bean
    public SetterImplementInterface setterImplementInterface(){
        return new SetterImplementInterface();
    }

    @Bean
    @Primary
    public PrimaryImplementInterface primaryImplementInterface(){
        return new PrimaryImplementInterface();
    }



}
/*

package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


public class PrimaryImplementInterface implements  Interface{

    @Override
    public String getMsg() {
        return "this is a @Primary class implements interface";
    }
}

    """
package com.example.demo2;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


public class SetterImplementInterface implements Interface{

    @Override
    public String getMesg() {
        return "this is a setter injection";
    }
}

    package com.example.demo;

import org.springframework.stereotype.Component;

public class ConstructorImplementInterface implements Interface{

    @Override
    public String getMsg() {
        return "this is constructor injection";
    }
}

packa


    """

*/





```



```java
package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("sameNameHere")
@Profile({"TWO","default"})
public class ProfileTwoImplementInterface implements Interface {

    @Override
    public String getMsg() {
        return "profile one uses the same name";
    }

}
package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("sameNameHere")
@Profile({"TWO","default"})
public class ProfileTwoImplementInterface implements Interface {

    @Override
    public String getMsg() {
        return "profile two uses the same name";
    }

}

package com.example.demo;

public class ProfileDI {
    private final Interface anInterface;

    public ProfileDI(Interface anInterface) {
        this.anInterface = anInterface;
    }
    
    public String callMsg(){
        return anInterface.getMsg();
    }
}




```


```java

package com.example.config;

import com.example.demo.ConstructorImplementInterface;
import com.example.demo.PrimaryImplementInterface;
import com.example.demo.ProfileOneImplementInterface;
import com.example.demo2.SetterImplementInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class MainConfiguration {
    
    @Bean
    @Profile("ONE")
    ProfileOneImplementInterface profileOneImplementInterface(){
        return new ProfileOneImplementInterface();
    }

    @Bean
    public ConstructorImplementInterface constructorImplementInterface() {
        return new ConstructorImplementInterface();
    }

    @Bean
    public SetterImplementInterface setterImplementInterface(){
        return new SetterImplementInterface();
    }

    @Bean
    @Primary
    public PrimaryImplementInterface primaryImplementInterface(){
        return new PrimaryImplementInterface();
    }



}
/*
    """
package com.example.demo2;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


public class SetterImplementInterface implements Interface{

    @Override
    public String getMesg() {
        return "this is a setter injection";
    }
}

    package com.example.demo;

import org.springframework.stereotype.Component;

public class ConstructorImplementInterface implements Interface{

    @Override
    public String getMsg() {
        return "this is constructor injection";
    }
}

packa


    """

*/



// spring.profiles.active=ONE

```


to reiterate, by default, spring is going to use the method name unless you override it 

## DI in java configuration in method parameters 


using a hypothetical situation here, purely 

spring will wire up our method 

spring is smart enough to go out and create that bean to the context 



```java
package com.example.config;

import com.example.demo.*;
import com.example.demo2.SetterImplementInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class MainConfiguration {


    @Bean
    MethodInterfaceImpl methodInterface(){
        return new MethodInterfaceImpl();
    }


    @Bean
    MethodInjection methodInjection(MethodInterfaceImpl methodInterface){
        return new MethodInjection(methodInterface);
    }



    @Bean
    @Profile("ONE")
    ProfileOneImplementInterface profileOneImplementInterface(){
        return new ProfileOneImplementInterface();
    }

    @Bean
    public ConstructorImplementInterface constructorImplementInterface() {
        return new ConstructorImplementInterface();
    }

    @Bean
    public SetterImplementInterface setterImplementInterface(){
        return new SetterImplementInterface();
    }

    @Bean
    @Primary
    public PrimaryImplementInterface primaryImplementInterface(){
        return new PrimaryImplementInterface();
    }



}
/*
    """
package com.example.demo2;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


public class SetterImplementInterface implements Interface{

    @Override
    public String getMesg() {
        return "this is a setter injection";
    }
}

    package com.example.demo;

import org.springframework.stereotype.Component;

public class ConstructorImplementInterface implements Interface{

    @Override
    public String getMsg() {
        return "this is constructor injection";
    }
}

packa


    """

*/



package com.example.demo;

public class MethodInjection {
    MethodInterfaceImpl methodInterface;

    public MethodInjection(MethodInterfaceImpl methodInterface) {
        this.methodInterface = methodInterface;

    }


    public String callMsg(){
        return methodInterface.callMethod();
    }
}
package com.example.demo;

public interface MethodInterface {

    String callMethod();
}
package com.example.demo;

public class MethodInterfaceImpl implements MethodInterface {
    @Override
    public String callMethod() {
        return "this is a method that will be injected into another injection";
    }
}


```



## Factory beans 

common programming pattern in spring is the factory pattern 


it has been adopted to any object oriented programming languages 

you are not on speed providing complex object 


let's provide a factory pattern 
get all wired up 

```java

package com.example.demo;


import org.springframework.stereotype.Service;

@Service
public class Factory {
    
    public Factory(String types){
        switch (types){
            case "Un":
                return new ClassOne();
            case "Deux":
                return new ClassTwo(); 
                default:
                    return new ClassThree();
        }
    }
}


package com.example.config;

import com.example.demo.*;
import com.example.demo2.SetterImplementInterface;
import org.springframework.boot.logging.java.JavaLoggingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class MainConfiguration {

    @Bean
    Factory factory() {
        return new Factory();
    }
    
    @Profile("Un")
    @Bean 
    Un un(Factory factory){
        return factory.getTypes("Un");
    }

}

```


## XML configurations 


xml is very mature very versatile 

the trend is java although, everything you can do in xml you can do in java, you are kinda limited to the limitation and constraints of xml. it is the nature of these two technologies so to speak 


under resources: 
new -> XML -> spring config 

this is a standard xml file, the secret sauce so to speak is the top here where it says beans 

we cannot go to particulars of html it is kinda of no longer in favor in the industry very structured 


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- bean definitions here -->

</beans>

```
this gives us the constraints for us to leverage 


we are giving the method name normally by default, this is how this is going to be set up, 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- bean definitions here -->

    <bean name="constructorImplementInterface" class="com.example.demo.ConstructorImplementInterface" />
    

    

</beans>
```
we do a close tag on that this satisfies the xml configuration 


comment this out 

```java
//    //@Bean
//    public ConstructorImplementInterface constructorImplementInterface() {
//        return new ConstructorImplementInterface();
//    }

```
we need to tell spring to look at this xml configuratiton file 


wing profiles are active: ONE
2022-01-08 23:10:38.028  INFO 15944 --- [           main] com.example.demo.DemoApplication         : Started DemoApplication in 1.173 seconds (JVM running for 1.628)
Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'constructorImplementInterface' available
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:872)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1344)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:309)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154)
	at com.example.demo.DemoApplication.main(DemoApplication.java:15)

Process finished with exit code 1

we can do in the Configuration class 
add @ImportResource("classpath")


```java

package com.example.config;

import com.example.demo.ConstructorImplementInterface;
import com.example.demo.MethodInterfaceImpl;
import com.example.demo.PrimaryImplementInterface;
import com.example.demo.ProfileOneImplementInterface;
import com.example.demo2.SetterImplementInterface;
import org.springframework.context.annotation.*;

@Configuration
@ImportResource("classpath:config.xml")
public class MainConfiguration {


    @Bean
    MethodInterfaceImpl methodInterface(){
        return new MethodInterfaceImpl();
    }



    @Bean
    @Profile("ONE")
    ProfileOneImplementInterface profileOneImplementInterface(){
        return new ProfileOneImplementInterface();
    }

//    //@Bean
//    public ConstructorImplementInterface constructorImplementInterface() {
//        return new ConstructorImplementInterface();
//    }

    @Bean
    public SetterImplementInterface setterImplementInterface(){
        return new SetterImplementInterface();
    }

    @Bean
    @Primary
    public PrimaryImplementInterface primaryImplementInterface(){
        return new PrimaryImplementInterface();
    }



}



```

you can also put in the main class 

the configuratio also get picked off from main class 


```java

package com.example.demo;

import com.example.demo2.SetterDI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan({"com.example.config","com.example.demo2","com.example.demo"})
@SpringBootApplication
@ImportResource("classpath:config.xml")

public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
    ConstructorImplementInterface constructorImplementInterface = (ConstructorImplementInterface) applicationContext.getBean("constructorImplementInterface");
	System.out.println(constructorImplementInterface.getMsg());
	}

}

```

we have our xml, configuration class, and finally controlelrs like our regular controlelrs, applicaitonContext will bring them into the context 

you can have a mixture like this 
it is important that you can use multiple ways to have this mixture 


to recap you put bean inside xml and declare the bean to notify the spring the xml file 
or configuration class 
controller class 



## Bean Socpes 

bean scope tells spring when beans are created, how long the container, the spring IOC container keeps the bean around 


- Singleton -- default only one instance of the bean is created in the IoC container 
- Protypte -- a new instance is created each time the bean is request 

- request - a single instance per http request only valid in the context of a web aware spring applciaiton context 

- session: a single instance per http session. only valid in the context of a web aware spring applicaiton context 

singleton is only one gets one instance 
the other will be more than one or as many are requested 

so very distinct different there as to how spring IoC context is be treated these 


### spring bean scope 
- gloabl-session a single instance per global session , typically only used in portlet context, only valid in the context of a web aware applcation context 

- application bean is scoped to the lifecyle of the context, only valid in the context of a web awre 

- webscoket -- scopes a single eban defintion of the lifecyle of a websocket. only valid in the context of a web aware spring application context 

- custom scope -- spring scopes are extensible, so you can define our onwn scope, implementing springs scope interface 
you cannot override the in in singleton and prototype scopes 




there is what we might want to call a code smell to be doing things like that

just want to make your awrre, but in reality and more modern spring development, you really do not have that much of a use case for it 


```xml

<bean id-"methodName" class="class name full path" 
>
<property name ="" ref = "one">

<bean id-"methodName" class="class name full path" 
>
<property name ="" ref = "one">

<bean id-"methodName" class="class name full path" 
>
<property name ="" ref = "one">

</bean>

<bean id = "yourId" class = "..." scope ="singleton" />  

```

one instance will be passed to each one of them 
very important distinction to understand that one instance is being used across multiple implementaitons 




```xml

<bean id-"methodName" class="class name full path" 
>
<property name ="" ref = "one">

<bean id-"methodName" class="class name full path" 
>
<property name ="" ref = "one">

<bean id-"methodName" class="class name full path" 
>
<property name ="" ref = "one">

</bean>

<bean id = "yourId" class = "..." scope ="prototype" />  

```


there will be brand new object for that and inject that 


every time when you need that a new object will be instantiated, so just that, it is really important to understand the distinction there 

### declaring bean scopes 
no declaration need for sinleton scope 
in java configuration uses @Scope annotaiton 
in xml configuration is an xml  attribute of bean tag 
99% of the time singleton scope is fine 


a lot of times it is a good time to step back and make sure that there is really a proper use case for it 
so often when you are getting into some of these edgge cases, you are making a poor decision  so to speak 



#### @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)

```java
package com.example.demo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonBean {
    public SingletonBean(){
        System.out.println("this is the singleton bean" + this);
    }

    public String printOut(){
        return "memory address" + this;
    }
}

package com.example.demo;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class PrototypeBean {

    public PrototypeBean(){
        System.out.println("this is the prototype scope:" + this);
    }

    public String getMyScope(){
        return "I am the prototype, memory: " + this;
    }
}
package com.example.demo;

import com.example.demo2.SetterDI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan({"com.example.config","com.example.demo2","com.example.demo"})
@SpringBootApplication
@ImportResource("classpath:config.xml")

public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

     PrototypeBean prototypeBean1 = (PrototypeBean) applicationContext.getBean(PrototypeBean.class);
	 System.out.println(prototypeBean1.getMyScope());

		PrototypeBean prototypeBean2 = (PrototypeBean) applicationContext.getBean(PrototypeBean.class);
		System.out.println(prototypeBean2.getMyScope());

		SingletonBean singletonBean1 = (SingletonBean) applicationContext.getBean(SingletonBean.class);
		System.out.println(singletonBean1.printOut());
		SingletonBean singletonBean2 = (SingletonBean) applicationContext.getBean(SingletonBean.class);
		System.out.println(singletonBean2.printOut());
	}

}




```



the singleton bean gets upon the start of spring context 









 







































































