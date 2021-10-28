# Bean Scopes

a scope defines/ refers to the lifecyle of a bean, it tells you how long the bean will live, how many instances are created, and how is the bean shared?

why singleton? 
spring container creates only one instance of the bean by dfeault. 
it is cached in memory. all requests for the bean will return a shared reference to the same bean

```java
Interface object1 = context.getBean("classId",Interface.class);
Interface object2 = context.getBean("classId",Interface.class);

```

it will basically give you the reference to the same bean, they point to the same object of the memory, the same bean. 

it will only create one bean and share it for everyone who requests that bean so singleton scope is default, it is a stateless bean where you do not need to maintain any state 

you can specifically specify the scope of the bean, it is kinda preferred approach to minimize the number of beans that are created 

there are different scopes that we can make use of 

| Scope  | Description |
| ------------- | ------------- |
| singleton  |  create a single shared instance of the bean, default scope |
| prototype  |  create a new bean instance for each container request |
| request  | scoped to an http web request. only used for web apps  |
| session  |  scoped to an http web session. only used for web apps |
| global-session  |  scoped to an global http web session. only used for web apps |



```xml
<beans ...>

<bean id = "className"
class = "ClassName" 
scope = "singleton">

</bean>


<bean id = "className"
class = "ClassName" 
scope = "prototype">

</bean>

```


```java

Interface object1 = context.getBean("className",Interface.class);


```

so the prototype is good for keeping track of stateful data, whenever you see prototype just think of a new bean keyword for each request for that component of that object 



let's pick where we left off 


singleton example: 


applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

 
<bean id = "classOne" class = "ClassOne">
</bean>


<bean id  = "classThree" class = "ClassThree">

</bean>
	
 
  
 
</beans> 
 


```

Interface.java 
AnotherInterface.java
```java
public interface Interface{
    public String getInformation();
    public String getExtraInformationForAnother();
}

public interface AnotherInterface{


        public String anotherGetInformation();


}


```

ClassOne.java

ClassThree.java 
```java
public class ClassOne implements Interface{
	
	private AnotherInterface anotherInterface;

@Override
public String getInformation(){
	

	
	

return "this information is from class one";

}

@Override
public String getExtraInformationForAnother() {
	// TODO Auto-generated method stub
	return anotherInterface.anotherGetInformation();
}

}

public class ClassThree implements AnotherInterface{
        


        @Override
        public String anotherGetInformation(){

        return "this is from class three which inherits from AnotherInterface";
        }
        
        
        
        
        
}    
```



Main.java


```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainBean {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		
		Interface singleton1 = context.getBean("classOne", Interface.class);
		Interface singleton2 = context.getBean("classOne", Interface.class);
		
		if(singleton1 == singleton2) {
			System.out.println("same memory address");
			System.out.println("address for singleton1" + singleton1);
			System.out.println("address for singleton2" + singleton2);

		}
		
		context.close();
		
	}

}


```

they print out the same area of memory 

let's test scope with prototype 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

 
<bean id = "classOne" class = "ClassOne" scope= "prototype">
</bean>


<bean id  = "classThree" class = "ClassThree">

</bean>
	
 
  
 
</beans> 
 


```

Main.java

```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainBean {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		
		Interface prototype1= context.getBean("classOne", Interface.class);
		Interface  prototype2 = context.getBean("classOne", Interface.class);
		
	 
			System.out.println("different memory address");
			System.out.println("address for singleton1" +  prototype1);
			System.out.println("address for singleton2" +  prototype2);
 
		
		context.close();
		
	}

}


```




 
## Bean lifecyle methods/ hooks

container started -> bean instantiated -> dependency injected -> internal spring processing -> your custom init method -> bean is ready for use/ container is shutdown -> your custom destroy method -> stop 


you can add custom code during bean initialization 
1. call custom business logic methods 
2. setting up handles to resources, db, sockets, files and etc 


you can add custom code during bean destruction 
1. calling custom business logic methods 
2. clean up handlers to resources, db, socket, files and etc 



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

 
<bean id = "classOne" class = "ClassOne"
init-method="methodName"
destroy-method="methodName">
</bean>


<bean id  = "classThree" class = "ClassThree">

</bean>
	
 
  
 
</beans> 
 


```
set up bean initialization with any method names 

when using xml configuraiton, you want to provide additional detail regarding the method signature of the init-method and destroy methods 

access modifier: the method can have any access modifier public, protected, private 

return type: 
the method can have an yreturn type, however , void is most commonly uesd if you give a return type just note that you will not be able to capture the return value. as a result, void is commonly used 

method name: can have any method name 

arguments: the method can not accept any arguments, the method should be no-args 


development process 
1. define your methods for init and destroy 
2. configure the mtehod names in spring config files 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context-3.0.xsd">

 
<bean id = "classOne" class = "ClassOne" scope= "singleton"
init-method="initMethod"
destroy-method="destroyMethod"

>

<constructor-arg ref = "classThree" />
</bean>


<bean id  = "classThree" class = "ClassThree">

</bean>
	
 
  
 
</beans> 
 


```





There is a subtle point you need to be aware of with "prototype" scoped beans.

For "prototype" scoped beans, Spring does not call the destroy method.  Gasp!  
In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean: the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.

Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding. 


```java
/*

<bean id = "classOne" class = "ClassOne" scope= "prototype"
init-method="initMethod"
destroy-method="destroyMethod"
>
</bean>


*/
public class ClassOne implements Interface{
	
	private AnotherInterface anotherInterface;

@Override
public String getInformation(){
	

return "this information is from class one";

}

@Override
public String getExtraInformationForAnother() {
	// TODO Auto-generated method stub
	return anotherInterface.anotherGetInformation();
}

public void initMethod() {
	System.out.println("the init method is invoked");
}

public void destroyMethod() {
	System.out.println("the destroy method is invoked");
}

}
```