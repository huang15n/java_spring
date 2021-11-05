# Annotations 


what are java annotations? 
1. special labels/markers added to java classes 
2. provide metadata about hte class 

3. processed at compile time or runtime for special processing 

@Override annotation tells the compiler we are gonna implement a given interface and override mtehods, at compilation time, compiler will check/verify the override. again, that is actually processed at compilation time 



why do you use spring configuration with annotations? 
1. xml configuraiton can be verbose 
2. configure your spring beans with annotations 
3. annotations minimizes the xml configurations 


scanning for component classes 
1. spring will scan you java classes for special annotations 
2. automatically register the beans in the spring container 



development process 
1. enable component scanning in spring config file, spring will scan this package recursively 


```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

 
 
  
<context:component-scan base-package="com.eddie.annotation" />

 
 
  
 
</beans> 
 

```


2. add the @Component annotaiton to your java classes 



```java
package com.eddie.annotation;
public interface Interface{
    public String getInformation();
 }


 package com.eddie.annotation;
import org.springframework.stereotype.Component;


@Component("randomClassNameHere")
public class ClassOne implements Interface{
	
 

@Override
public String getInformation(){
	

return "this information is from class one";

}

}
 

```

3. retrive bean from spring container, same code as before, nothing changed 


```java

package com.eddie.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	     
		
		Interface classOneFromInterface = context.getBean("randomClassNameHere",Interface.class);
		
		System.out.println(classOneFromInterface.getInformation());
		
		
		
		context.close();

	}

}

```





### default component names 

spring also supports default bean ids instead of @Component("nameYouGave")

default bean id: the class name , make first letter lower-case 
ClassName -> className(default bean id)
you do not have to explicity specify @Component("nameOrSomething")


```java
package com.eddie.annotation;
import org.springframework.stereotype.Component;


@Component
public class ClassOne implements Interface{
	
 

@Override
public String getInformation(){
	

return "this information is from class one";

}

}
 


 package com.eddie.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	     
		
		Interface classOneFromInterface = context.getBean("classOne",Interface.class);
		
		System.out.println(classOneFromInterface.getInformation());
		
		
		
		context.close();

	}

}


```


## Spring depenecy injection with annotations and autowiring 


now will also provide helper class this is a dependency 


what is spring autowiring? 

for dependency injection, spring can use auto wiring
spring will look for a class that matches the property, match by type: class or interface 
spring will inject it automatically ... hence it is called autowired 


injecting the helper class into the class implementation 
spring will scan @Component 
any one implements the helper Interface? if so , let's inject them 


autowiring injection types
1. constructor injection
2. setter injection
3. field injections 

development process - constructor injection 
1. define the dependency interface and classes 
2. create a constructor in your class for injections 
3. configure the dependency injection with @Autowired annotation 


instead of doing long hand using xml configs

```xml

 <bean id="classOne" class="ClassOne"> </bean>
  
  <bean id = "classTwo" class = "ClassTwo" ></bean>
  
  <bean id = "classThree" class = "ClassThree" >
  </bean>

  <bean id = "classFour" class = "ClassFour"> 
<constructor-arg ref="classThree" />

```
this uses autowired implementaiton. what is done in the background is that spring will find a bean that implements the helper's interface 



what if there are multiple Interface implementaitons
when using autowiring, spring has special support to handle this case,use the @Qualifier annotation and address all scenarios 

interface:
```java
package com.eddie.annotation;
public interface Interface{
    public String getInformation();
    public String getExtraInformationFromAnotherInterface();
 }

 package com.eddie.annotation;

public interface Interface2 {
	
	public String getExtraInfo();

}


```

classes 
```java
package com.eddie.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClassOne implements Interface{
	
 private Interface2 interface2;
 
 @Autowired
 public  ClassOne(Interface2 interface2) {
	 this.interface2 = interface2;
 }

@Override
public String getInformation(){
	

return "this information is from class one";

}
@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 
 

 package com.eddie.annotation;

import org.springframework.stereotype.Component;

@Component
public class ClassTwo implements Interface2 {
	
	
	

	@Override
	public String getExtraInfo() {
		// TODO Auto-generated method stub
		return "this is inherited from interface2 to create a class2";
	}

}


```


```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

 
 
  
<context:component-scan base-package="com.eddie.annotation" />

 
 
  
 
</beans> 
 


```

scan will scan for a component that implements the Interface2 



```java
package com.eddie.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	     
		
		Interface classOneFromInterface = context.getBean("classOne",Interface.class);
		
		System.out.println(classOneFromInterface.getInformation());
		
		System.out.println(classOneFromInterface.getExtraInformationFromAnotherInterface());
		
		context.close();

	}

}


```





eclipse tips:    Windows -> Preferences -> Java -> Editor ->Content Assist : auto activation trigger: ._abc



how did @AutoWired work?
an @Autowired annotation on such a constructor is no longer neccesary if the target bean only defines one constructor to begin with. however, if several constructors are available, at least one must be annotated to teach the constructor which one to you. personally prefer to use @Autowired annotation because it makes the code more readable 



### setter injection 

inject dependencies by calling setter methods on your class 



autowiring example : 
inject AnotherInterface into the Interface implementaiton 
spring will scan @Component 
any one implements AnotherInterfaces if so let's inject it 


development process 
1. create setter methods in your class for injection
2. configure the dependency injection with @Autowired annotation 




```java

package com.eddie.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClassOne implements Interface{
	
 private Interface2 interface2;
 
 
 @Autowired
 public void setInterface2ImplementationInjection(Interface2 interface2) {
	 this.interface2 = interface2;
 }


@Override
public String getInformation(){
	

return "this information is from class one";

}
@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 

 package com.eddie.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	     
		
		Interface classOneFromInterface = context.getBean("classOne",Interface.class);
		
		System.out.println(classOneFromInterface.getInformation());
		
		System.out.println(classOneFromInterface.getExtraInformationFromAnotherInterface());
		
		context.close();
		

	}

}

package com.eddie.annotation;

import org.springframework.stereotype.Component;

@Component
public class ClassThree implements Interface2 {

	@Override
	public String getExtraInfo() {
		// TODO Auto-generated method stub
		return "this is from class 3 which inherits from Interface2";
	}

}

package com.eddie.annotation;

public interface Interface2 {
	
	public String getExtraInfo();

}
package com.eddie.annotation;
public interface Interface{
    public String getInformation();
    public String getExtraInformationFromAnotherInterface();
 }
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

 
 
  
<context:component-scan base-package="com.eddie.annotation" />

 
 
  
 
</beans> 
 


```

remember if you put two @Component on classes that implements the same interface, it will output errors:

```java


/*

Oct 29, 2021 9:48:00 AM org.springframework.context.support.AbstractApplicationContext refresh
WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'classOne': Unsatisfied dependency expressed through method 'setInterface2ImplementationInjection' parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.eddie.annotation.Interface2' available: expected single matching bean but found 2: classThree,classTwo
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'classOne': Unsatisfied dependency expressed through method 'setInterface2ImplementationInjection' parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.eddie.annotation.Interface2' available: expected single matching bean but found 2: classThree,classTwo
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredMethodElement.resolveMethodArguments(AutowiredAnnotationBeanPostProcessor.java:768)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredMethodElement.inject(AutowiredAnnotationBeanPostProcessor.java:720)
	at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:119)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:399)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1413)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:601)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:524)
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:944)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:918)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:144)
	at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:85)
	at com.eddie.annotation.Main.main(Main.java:10)
Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.eddie.annotation.Interface2' available: expected single matching bean but found 2: classThree,classTwo
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveNotUnique(DependencyDescriptor.java:220)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1358)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1300)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredMethodElement.resolveMethodArguments(AutowiredAnnotationBeanPostProcessor.java:760)
	... 16 more

*/
package com.eddie.annotation;

import org.springframework.stereotype.Component;

@Component
public class ClassTwo implements Interface2 {
	
	
	

	@Override
	public String getExtraInfo() {
		// TODO Auto-generated method stub
		return "this is inherited from interface2 to create a class2";
	}

}


```




### inject dependencies by calling any methods on your class

you can use any name

```java
package com.eddie.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClassOne implements Interface{
	
 private Interface2 interface2;
 
 
 @Autowired
 public void anyName(Interface2 interface2) {
	 this.interface2 = interface2;
 }


@Override
public String getInformation(){
	

return "this information is from class one";

}


@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 

```


### field injection 


inject dependencies by setting field values on your class directly even private fields accomplished by java reflection, without setter methods, we place it directly on the filed then they will set this field behind the scenes 






1. configure the dependency injection with @Autowired annotation applied directly to the field, no need for setter methods 

```java

package com.eddie.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClassOne implements Interface{
	
@Autowired
 private Interface2 interface2;
 
 



@Override
public String getInformation(){
	

return "this information is from class one";

}


@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 

```

it automatically uses this field injection with @Autowired annotation 



why injection type should you use? 1. constructor injection 2. setter injection 3 field injection? choose a style and stay consitent in your project for full discloures 




## annotation @Qualifier for  @Autowired 

what happens if there is mulitple implementaitons out there? 

there will be an error saying injection of autowired dependency failed, no qualifying bean of type is defined expected single matching bean not found 


we want to specify the bean id for @Qualifier, Be SPECIFIC same as class:
@Qualifier("className")


in genreal, when using Annotations, for the default bean name, spring uses the following rules, if the annotation's value does not indicate a bean name, an appropriate name will be built based on the short name of the class with the first letter lower cased 
however, for the special case when BOTH the first and second characters for the class name uppercase, then the name is not converted 

behind the, spring uses java beans introspector to generate the default bean name, string to be decapitalized 




we can apply @Qualifier annotation to 
1. constructor injection
@Qualifier is a nice feature, but it is tricky when used with Constructors, the syntax is much different from others and not exactly initutive . 
below will not work 

```java
	@Autowired
	@Qualifier("classTwo")
public ClassOne(Interface2 interface2) {
		this.interface2 = interface2;
	}

```

consdier this the deep end of the pool when it comes to spring configuration, we have to place the @Qualifier annotation insisde the constructor arguments 


correct version:

```java
 
	@Autowired
	
public ClassOne(@Qualifier("classTwo") Interface2 interface2) {
		this.interface2 = interface2;
	}


 

```



2. setter injection methods 
```java
package com.eddie.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ClassOne implements Interface{
	

 private Interface2 interface2;
 
 
 
	@Autowired
	@Qualifier("classTwo")
 public void setMethod(Interface2 interface2) {
	 this.interface2 = interface2;
 }


 

@Override
public String getInformation(){
	

return "this information is from class one";

}


@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 

```


3. field injection 


```java
package com.eddie.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ClassOne implements Interface{
	
	@Autowired
	@Qualifier("classTwo")
 private Interface2 interface2;
 
 


 

@Override
public String getInformation(){
	

return "this information is from class one";

}


@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 

```


you can also use a property file with values to inject 

1. create a properties file that holds your properties 
src/xxx.properties 

```
xxx.key=value

```
2. load the properties in the xml config 
```

<context-property-placeholder="classpath:xxx.properties">

```

3. inject the property vlaues into your class

```
@Autowired
@Value("${xxx.key}")


```


### @Scope annotation 

scope refers to the lifecycle of the beans, singleton means spring container creates only one instance of the bean, by default. it is cached in memory. all requests for the bean will return a shared reference to the same bean 

you should place the scope of your class which inherits the interface after @Component 


```java
package com.eddie.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
 
public class ClassOne implements Interface{
	

 private Interface2 interface2;
 
 
 
	@Autowired
public ClassOne(@Qualifier("classTwo") Interface2 interface2) {
		this.interface2 = interface2;
	}


 

@Override
public String getInformation(){
	

return "this information is from class one";

}


@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 

```

prototype creates a different bean each time:

```java
package com.eddie.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class ClassTwo implements Interface2 {
	
	
	

	@Override
	public String getExtraInfo() {
		// TODO Auto-generated method stub
		return "this is inherited from interface2 to create a class2";
	}

}
package com.eddie.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class ClassOne implements Interface{
	

 private Interface2 interface2;
 
 
 
	@Autowired
public ClassOne(@Qualifier("classTwo") Interface2 interface2) {
		this.interface2 = interface2;
	}


 

@Override
public String getInformation(){
	

return "this information is from class one";

}


@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 

```


### Bean lifecyle methods/ hooks @PostConstruct and @PreDestroy

you can add custom code during bean initialization
1. calling custom business logic methods 
2. setting up handles to resources -- db, sockets, files etc 
@PostConstruct , code will execute after constructor and after injection of dependencies 



you can add custom code during bean destruction
1. calling custom business logic methods 
2. clean up handles to resources -- db, sockets, files etc 
@PreDestroy, code will execute before bean is destroyed 


the method can have any modifiers -- public, protected, private

return type can be any, void is most commony use, you just note you will not be able to capture the return value 

method name can be anything 
the method cannot accept any argument, the method should be no arg!!!!

when you use java9 or higher, javax.annotation has removed @PostConstruct or @PreDestroy from its default classpath 
add this to your class path
https://search.maven.org/remotecontent?filepath=javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar

```java
package com.eddie.annotation;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class ClassOne implements Interface{
	

 private Interface2 interface2;
 
 
 
	@Autowired
public ClassOne(@Qualifier("classTwo") Interface2 interface2) {
		this.interface2 = interface2;
	}

	
@PostConstruct
public void preConstructorMethod() {
	System.out.println("setup method for spring");
}


@PreDestroy
public void PostDestroyMethod() {
	System.out.println("cleanup method for spring");
}


@Override
public String getInformation(){
	

return "this information is from class one";

}


@Override
public String getExtraInformationFromAnotherInterface() {
	return interface2.getExtraInfo();
}

}
 

 package com.eddie.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	     
		
		Interface classOneFromInterface1 = context.getBean("classOne",Interface.class);
		Interface classOneFromInterface2 = context.getBean("classOne",Interface.class);
		
 
		
	 
		
		context.close();
		

	}

}


```

for prototype scope in class, spring will not call @PreDestroy methods on @Scope("prototype")   gasp!! untill you dug into spring menu, n contrast to the other scopes, Spring does not manage the complete lifecycle of a
prototype bean: the container instantiates, configures, and otherwise assembles a
prototype object, and hands it to the client, with no further record of that prototype
instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding. 


You can destroy prototype beans but custom coding is required. This examples shows how to destroy prototype scoped beans.

1. Create a custom bean processor. This bean processor will keep track of prototype scoped beans. During shutdown it will call the destroy() method on the prototype scoped beans.

2. The prototype scoped beans MUST implement the DisposableBean interface. This interface defines a "destroy()" method. This method should be used instead of the @PreDestroy annotation.


