
# Spring configuraiton without xml and using pure java code!


instead of configuring spring container using xml, we configure the spring container with java source code


there are three ways of configguring spring container
1. full xml config 

```xml
<bean id = "className" class = "xxx.xxx.xxx.ClassName">
<constructor-args ref = "anotherClassName">

<property name = "anotherClass" ref = "xxx.xxx.xxx.AnotherClass">

</bean>
```

2. xml component scan 


```xml
<context:component base-package="xxx.xxx.packagename">

```


```java

@Component

@Autowired 

@Scope 

@PostConstruct

@PreDestroy

```


3. pure java configuration class 

let's get our hands dirty 

in a nut shell:


```java
// config file class java 


package com.eddie.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.eddie.annotation")
public class Anything{
	
	

	

}


```

```java
// main class 

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Anything.class);


```

development process 
1. create java class and annotate as @Configuration 
2. add component scanning support: @ComponentScan(optional)
```java
package com.eddie.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.eddie.annotation")
public class ConfigurationClass {
	
	

	

}


```


3. read spring java configuration class 
4. retrive bean from spring container 

```java
package com.eddie.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationClass.class);
		
   
		Interface classOneFromInterface1 = context.getBean("classOne",Interface.class);
		Interface classOneFromInterface2 = context.getBean("classOne",Interface.class);
		
 
		
	 
		
		context.close();
		

	}

}


```


### Add beans to the configuraiton with no xml @Bean

no @ComponentScan("xx.xx.xx") required 

```java

@Configuration


public class Anything{

    @Bean 
    public Interface className(){
   ClassName className = new className();
        return new ClassName;
    }
}

```
the bean id is the actual method name in main class 



```java

ClassName className = context.getBean("className",Interface.class);

```

development process 
1. define method to expos beans 

we are in good shape here and here are some prep work we have taken care of, we will get down and dirty and start doing bean defintions 

remeber the method name will be the bean id!



2. inject bean dependencies 
it is really important that we are actually calling that bean method on top of line 




3. read spring java 


```java

package com.eddie.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
 
public class ClassTwo implements Interface2 {
	
	
	

	@Override
	public String getExtraInfo() {
		// TODO Auto-generated method stub
		return "this is inherited from interface2 to create a class2";
	}

    

}
package com.eddie.annotation;

import java.security.PublicKey;

public class ClassFour implements Interface {
	
	private Interface2 interface2;
	
	 public ClassFour(Interface2 interface2) {
		// TODO Auto-generated constructor stub
	   	this.interface2 = interface2;
	}

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return "no xml required for this bean, class four";
	}

	@Override
	public String getExtraInformationFromAnotherInterface() {
		// TODO Auto-generated method stub
		return this.interface2.getExtraInfo();
	}

}


package com.eddie.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationClass.class);
		
   
		Interface classOneFromInterface1 = context.getBean("returnClassFour",Interface.class);
		
 
		System.out.println(classOneFromInterface1.getInformation());
		System.out.println(classOneFromInterface1.getExtraInformationFromAnotherInterface());

		
		context.close();
		

	}

}


```

@Component scan has to be placed in the helper class!!!


how does bean annotation work in the background? 
this is an advanced concept, walk thorugh the code line by line 


at a high level, spring creates a bean component manullay. by default the scope is singleton. so any request for a bean, will get the same instance of the bean since singleton is default scope 


the @Bean annotation tells spring that we are creating a bean component manually. 
this specifies that the bean will bean id of the className, the method name dfeines the bean id, the return type is the interface. this is seful for dependency injection. this can hep spring find any dependencies that implements the interface 

let's step back and look at the method in its entirety 

It is important to note that this method has the @Bean annotation. The annotation will intercept ALL calls to the method 

previously we were using @Autowired If this is the first time the bean has been created then it will execute the method as normal. It will also register the bean in the application context. So that is knows that the bean has already been created before. Effectively setting a flag.

The next time this method is called, the @Bean annotation will check in memory of the Spring container (applicationContext) and see if this given bean has already been created. Since the bean has already been created (previous paragraph) then it will immediately return the instance from memory. It will not execute the code inside of the method. Hence this is a singleton bean.

This is effectively dependency injection. It is accomplished using all Java configuration (no xml).

 
This concludes the line-by-line discussion of the source code. All of the behind the scenes work. I hope this clears your doubt 




what is the real time use case for @Bean? 
you can use @Bean to make an exsting third party class avaiable to your spring framework app context 

we simply cannot modify the source code for third party library, we cannot simply add the @Component annotation to the source code, as a result, we need an alternative solution 

we can wrap up third arty class by @Bean, as a spring bean. and then once it is wrapped using @Bean, it is as a singleton object and available in our spring framework app context, we can easily share this bean in our app using dependency inject and @Autowired. 

so think of the @Bean annotation as a wrapper/adapter for third-party classes, available to your spring framework app context 

if class is not annotated with @Component, it is not aware of spring, but we could manually wrap it using @Bean, by doing this, we can now share/reuse in our spring app context, we can now share/reuse this bean in other areas of our spring app by using depedency inject and @Autwired 

Note: You can use both two approaches of @Bean AND component scanning depending on your application requirements. You can use @Bean for third-party beans. For your existing beans in your class you can use component scanning with @Component (also @Controller, @Service, @Repository ...).




## inject values from properties files 

1. create properties files 
2. load propeties file in spring config 
3. reference values from properties files 

```java
package com.eddie.annotation;

public interface Interface2 {
	
	public String getExtraInfo();

}



package com.eddie.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationClass.class);
		
   
		ClassFour classFour = context.getBean("returnClassFour",ClassFour.class);
		
 
		System.out.println(classFour.getName());
		System.out.println(classFour.getName2());

		
		context.close();
		

	}

}

package com.eddie.annotation;

import org.springframework.beans.factory.annotation.Value;

public class ClassFour implements Interface {
	
	private Interface2 interface2;

	@Value("${something.name}")
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Value("${something.name2}")
	private String name2;
	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	

	
	 public ClassFour(Interface2 interface2) {
		// TODO Auto-generated constructor stub
	   	this.interface2 = interface2;
	}

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return "no xml required for this bean, class four";
	}

	@Override
	public String getExtraInformationFromAnotherInterface() {
		// TODO Auto-generated method stub
		return this.interface2.getExtraInfo();
	}

}


package com.eddie.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan("com.eddie.annotation")
@PropertySource("classpath:anything.properties")
public class ConfigurationClass {
	
	
	@Bean 
	public Interface2 returnClassTwoHelper() {
		return new ClassTwo();
	}
	
	@Bean
	public Interface returnClassFour() {
		return new ClassFour(returnClassTwoHelper());
	}

	

}


```


remember the file path for property file should be under src not the package !!!!

