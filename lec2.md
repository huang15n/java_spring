

# Inversion of Control and Dependency Injection 

## Inversion of Control 
inversion control is the type of buzzwords we hear from spring work, what exactly is inversion control? the approach of outsourcing the construction and management of objects 

basically it says your object is outsource to an object factory and that process is handled by object factory 







The Inversion-of-Control (IoC) pattern, is about providing any kind of callback (which controls reaction), instead of acting ourself directly (in other words, inversion and/or redirecting control to external handler/controller). The Dependency-Injection (DI) pattern is a more specific version of IoC pattern, and is all about removing dependencies from your code.

Every DI implementation can be considered IoC, but one should not call it IoC, because implementing Dependency-Injection is harder than callback (Don't lower your product's worth by using general term "IoC" instead).

we start with rough prototype and refine it 



app <-> method () <-> concrete class 

app should be configurable 
easily change the concrete for another type, let's build out rough prototype 

every class has the method getInformation(); let's implement an interface. this can worh with any Interface implementation!

Interface.java

```java
public interface Interface{
    public String getInformation();
}

```


Main.java
```java
public class Main{

public static void main(String [] args){

Interface classOne = new ClassOne();

Interface classTwo = new ClassTwo();
System.out.println(classOne.getInformation());

System.out.println(classTwo.getInformation());



}

}



```

ClassOne.java

```java
public class ClassOne implements Interface{

@Override
public String getInformation(){

return "this information is from class one";

}

}


```

ClassTwo
```java
public class ClassTwo implements Interface{



@Override
public String getInformation(){

return "this information is from class two";

}


}


```

the app should be configurable which easily change the class for another class 


```java
Interface classXXX= new ClassXXX();

```

this is hardcoded 

ideally we can make it from a config file and then make use of it, spring addresses this problem 



## overview

my app ---- give me an object----> object factory
|
class1, class2, class3 

it talks to the object factory based on a configuraiton file or annotation, spring will give you appropriate implementation and it will have full support for it 

the primary functions for spring container is 
1. create and manage object --- Inversion of Control IOC
2. inject object's dependencies --- dependency injection 


three ways t configure spring container 
1. xml configuration file , legacy but most legacy apps still use this high liklihood 
2. java annotation -- modern
3. java source code -- modern 

spring development process 
1. configure your spring beans 





```xml
<beans ....>

<bean id = "className"

class = "package_path.ClassName">


</beans>
```
the id is like an alias, fully qualified class name of implementation class 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="
    http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd">
	  

	
  <bean id="" class="" />
  <bean id="" class="" />
  <bean id="" class="" />
</beans>

```



2. create a spring container 
spring container is generically known as ApplicationContext, specialized implementaiton 
ClassPathXmlApplication Context 
AnnotaitonConfigurationApplicationContext 
GenericWebApplicationContext



```java
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // name of config file 

```
it wil lgive you the implementaiton of that given interface 


3. retrive beans from spring container 


```java

Interface classOne = context.getBean('id',ClassName.class);


/*

<beans ....>

<bean id = "className"

class = "package_path.ClassName">


</beans>

*/

```





4. finally we must close the context as we clean up!!!!



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="
    http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd">
	  

	
  <bean id="classOne" class="ClassOne" />
  
  <bean id = "classTwo" class = "ClassTwo" />
 
</beans>


```

```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{

public static void main(String [] args){

 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
 
 
 
 Interface classOfOne = context.getBean("classOne", Interface.class);
 
 
 Interface classOfTwo = context.getBean("classTwo", Interface.class);
 
 
 System.out.println(classOfOne.getInformation());

 System.out.println(classOfTwo.getInformation());


}

}

```

what is a spring bean? a spring bean is simply a blurb as a java object, when java objects are created by the spring container, then spring refers to them as spring beans, spring beans are created from normal java classes just like java objects. 

why do we specify the Interface class? we pass the interface to the method behind the scenes, Spring will cast the object for you, Behaves the same as getBean(String), but provides a measure of type safety by throwing a BeanNotOfRequiredTypeException if the bean is not of the required type. This means that ClassCastException can't be thrown on casting the result correctly, as can happen with getBean(String).


## Dependency Injection 

Dependency Injectionis about separating concerns. The dependency inversion principle, the client delegates to calls to another object and responsibility of the providing its depenencies 


Car -> Car factory -> assemble car for you, they inject dependencies for you same thing as helper objects 




Without DI: You have a laptop computer and you accidentally break the screen. And darn, you find the same model laptop screen is nowhere in the market. So you're stuck.

With DI: You have a desktop computer and you accidentally break the screen. You find you can just grab almost any desktop monitor from the market, and it works well with your desktop.

Your desktop successfully implements DI in this case. It accepts a variety type of monitors, while the laptop does not, it needs a specific screen to get fixed.



for example, ClassThree may have a different method and ClassOne and ClassTwo uses it 


dependency = helper 



there are many types of injection with spring: 
1. constructor injector
2. setter injection 

later on, autowiring in the annotation 


development process - constructor injection 
1. define the dependency interface and class 

second interface 
```java
public interface Interface{
    public String getInformation();
    public String getExtraInformationForAnother();
}

```

second class inherits from that interface 

```java
public interface AnotherInterface{


        public String anotherGetInformation();


}


```


```java
public class ClassThree implements AnotherInterface{
        


        @Override
        public String anotherGetInformation(){

        return "this is from class three which inherits from AnotherInterface";
        }
        
        
        
        
        
}    

```



2. create a constructor in your class for injections 


in ClassFour.java

```java
public class ClassFour implements Interface{

private AnotherInterface anotherInterface;


public ClassFour(AnotherInterface anotherInterface){

 this.anotherInterface = anotherInterface;
}

@Override
public String getInformation() {
	// TODO Auto-generated method stub
	return "This is from class four";
}

@Override
public String getExtraInformationForAnother() {
	return anotherInterface.anotherGetInformation();
}

}



```

3. configure the dependency injection in spring config file 



you nee dto inject the dependency/helper using constructor injection 
<constructor-arg ref = "className">


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="
    http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd">
	  

	
  <bean id="classOne" class="ClassOne"> </bean>
  
  <bean id = "classTwo" class = "ClassTwo" ></bean>
  
  <bean id = "classThree" class = "ClassThree" >
  </bean>

   <bean id = "classFour" class = "ClassFour"> 
<constructor-arg ref="classThree" />
  </bean>
</beans>

```

this is responsible for injecting the dependencies 


the spring framework will perform operations behind the scenes for your, this helps out a little bit, let's get our hands dirty 

```java 

AnotherInterface classThree = new ClassThree();

ClassFour classFour = new ClassFour(classThree);

```



Main.java 

```java

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{

public static void main(String [] args){

 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
 
 
Interface classFour = context.getBean("classFour", Interface.class);

System.out.println(classFour.getInformation());
System.out.println(classFour.getExtraInformationForAnother());

 context.close();


}

}

```



eclipse has a function when right click on the code Source -> Generate Constructor using fields 





let's add another class 

class five

```java

public class ClassFive implements Interface {
	
	private ClassThree classThree;
	
	public ClassFive(ClassThree classThree) {
		this.classThree = classThree;
	}

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return "this is from class five";
	}

	@Override
	public String getExtraInformationForAnother() {
		// TODO Auto-generated method stub
		return this.classThree.anotherGetInformation();
	}

	
	
}


```


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="
    http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd">
	  

	

  <bean id="classOne" class="ClassOne"> </bean>
  
  <bean id = "classTwo" class = "ClassTwo" ></bean>
  
  <bean id = "classThree" class = "ClassThree" >
  </bean>

  <bean id = "classFour" class = "ClassFour"> 
<constructor-arg ref="classThree" />



  </bean>
  
  
  <bean id = "classFive" class = "ClassFive">
<constructor-arg ref = "classThree" />

</bean>
  
 
</beans> 
 


```

Main.java


```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{

public static void main(String [] args){

 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
 
 
Interface classFour = context.getBean("classFour", Interface.class);

System.out.println(classFour.getInformation());
System.out.println(classFour.getExtraInformationForAnother());

Interface classFive = context.getBean("classFive", Interface.class);
System.out.println(classFive.getInformation());
System.out.println(classFive.getExtraInformationForAnother());



 context.close();


}

}


```


### Setter Injection 

1. create setter methods in your class for injection 

```java

public class ClassSix implements Interface{
	
	private AnotherInterface anotherInterface;

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return "this is from class six";
	}
	
	
	public void setAnotherInterface(AnotherInterface anotherInterface) {
		this.anotherInterface = anotherInterface;
	}
	
	

	@Override
	public String getExtraInformationForAnother() {
		// TODO Auto-generated method stub
		return this.anotherInterface.anotherGetInformation();
	}
	
	

}


```

2. configure the dependency injection in spring config files 


3. call setter method on java class 

##### spring will ltake that method setXXX(), captilize first letter of property name 


<property name = "methodName" ref = "">

public void setMethodName()


```xml
<bean id = "classSix" class = "ClassSix">

<property name = "anotherInterface" ref = "classThree"/>
</bean>
  

```


```java

	
	public void setAnotherInterface(AnotherInterface anotherInterface) {
		this.anotherInterface = anotherInterface;
	}
	
```

Main.java
```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{

public static void main(String [] args){

 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
 
 
Interface classSix = context.getBean("classSix", Interface.class);

System.out.println(classSix.getInformation());
System.out.println(classSix.getExtraInformationForAnother());
 
 context.close();


}

}


```



### Injecting literal values 


development process 
1. create setter methods in your class for injection 

use eclipes to generate getters and setters 



```java
 

public class ClassSeven implements Interface {
	
	private String variable1;
	private String variable2;
	
	
 

	public String getVariable1() {
		return variable1;
	}

	public void setVariable1(String variable1) {
		this.variable1 = variable1;
	}

	public String getVariable2() {
		return variable2;
	}

	public void setVariable2(String variable2) {
		this.variable2 = variable2;
	}

	@Override
	public String getInformation() {
		// TODO Auto-generated method stub
		return this.variable1;
	}

	@Override
	public String getExtraInformationForAnother() {
		// TODO Auto-generated method stub
		return this.variable2;
	}

}


```

2. configure the injection in spring config file 
```xml

<bean id = "classSeven" class = "ClassSeven">

<property name = "variable1" value = "here for variable1" />
<property name = "variable2" value = "here fore variable2" />


</bean>
  

```

note: you cannot set the setter and get methods to private,lol :

```java
private void setVariable1(String variable1) {
		this.variable1 = variable1;
	}
	
	private void setVariable2(String variable2) {
		this.variable2 = variable2;
	}
/*

2021 10:57:29 AM org.springframework.context.support.AbstractApplicationContext refresh
WARNING: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'classSeven' defined in class path resource [applicationContext.xml]: Error setting property values; nested exception is org.springframework.beans.NotWritablePropertyException: Invalid property 'anotherInterface' of bean class [ClassSeven]: Bean property 'anotherInterface' is not writable or has an invalid setter method. Does the parameter type of the setter match the return type of the getter?
Exception in thread "main" org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'classSeven' defined in class path resource [applicationContext.xml]: Error setting property values; nested exception is org.springframework.beans.NotWritablePropertyException: Invalid property 'anotherInterface' of bean class [ClassSeven]: Bean property 'anotherInterface' is not writable or has an invalid setter method. Does the parameter type of the setter match the return type of the getter?
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues(AbstractAutowireCapableBeanFactory.java:1726)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1434)
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
	at Main.main(Main.java:7)
Caused by: org.springframework.beans.NotWritablePropertyException: Invalid property 'anotherInterface' of bean class [ClassSeven]: Bean property 'anotherInterface' is not writable or has an invalid setter method. Does the parameter type of the setter match the return type of the getter?
	at org.springframework.beans.BeanWrapperImpl.createNotWritablePropertyException(BeanWrapperImpl.java:243)
	at org.springframework.beans.AbstractNestablePropertyAccessor.processLocalProperty(AbstractNestablePropertyAccessor.java:432)
	at org.springframework.beans.AbstractNestablePropertyAccessor.setPropertyValue(AbstractNestablePropertyAccessor.java:278)
	at org.springframework.beans.AbstractNestablePropertyAccessor.setPropertyValue(AbstractNestablePropertyAccessor.java:266)
	at org.springframework.beans.AbstractPropertyAccessor.setPropertyValues(AbstractPropertyAccessor.java:104)
	at org.springframework.beans.AbstractPropertyAccessor.setPropertyValues(AbstractPropertyAccessor.java:79)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues(AbstractAutowireCapableBeanFactory.java:1722)
	... 13 more
*/

```

main.java

```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{

public static void main(String [] args){

ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


ClassSeven classSeven = context.getBean("classSeven",ClassSeven.class);

System.out.println(classSeven.getInformation());
System.out.println(classSeven.getVariable1());

System.out.println(classSeven.getExtraInformationForAnother());
System.out.println(classSeven.getVariable2());

context.close();

}

}

```


why use concrete class instead of Interface.class 
when you retrieve a bean from the spring container using the interface you only have methods defined in that interface, even though the actually implementation has additional methods, you only have visibility that are defined at the interface level 
when you retrive a bean from the spring container using the concrete class, you have access to the methods defined in the class interface. also you have access to additional methods defined in the class. the bottom line is it depends on how you retrieve the object and assign it.. that determines the visibility you have to the methods 


## injecting values from a property files

previously the values were hard coded and  injected from the config file 

we would like to read this information from our property file


development process 
1. create properties files 

file: xxx.properties 
xxxxxxx.name1=value1
xxxxxxx.name2=value2

xxxx.properties 
```
something.name1=this is a value from variable1 and name1
something.name2=this is a value from variable2 and name2

```



2. load properties file in spring config file 
3. reference values from properties file 


in applicationContext.xml 
<context: property-placeholder location="classpath:xxx.properties">


referencing the properties, ${prop name}

<property name = "variable1" value = "${xxxxxxx.name1}">


```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

	  
	  <context:property-placeholder location="classpath:name.properties" />

	

  <bean id="classOne" class="ClassOne"> </bean>
  
  <bean id = "classTwo" class = "ClassTwo" ></bean>
  
  <bean id = "classThree" class = "ClassThree" >
  </bean>

  <bean id = "classFour" class = "ClassFour"> 
<constructor-arg ref="classThree" />



  </bean>
  
  
  <bean id = "classFive" class = "ClassFive">
<constructor-arg ref = "classThree" />

</bean>

<bean id = "classSix" class = "ClassSix">

<property name = "anotherInterface" ref = "classThree"/>
</bean>

<bean id = "classSeven" class = "ClassSeven">
<property name = "variable1" value = "${something.name1}" />
<property name = "variable2" value = "${something.name2}" />


</bean>
  
 
</beans> 
 


```




Change your beans configuration in applicationContext.xml, The prefix "context" for element "context:property-placeholder" is not   bound.
'''
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">


</beans>



'''


