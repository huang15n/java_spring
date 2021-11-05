
# Spring MVC 

what is spring MVC? 
1. framework for building web app in java 
2. based on Model-View-Controller design pattern 
3. Leverages features of the core spring framework IoC, DI 




the big picture of Model-View-Controller 

Web Browser -> frnt controller -> model -> controller -> model -> view template -> web browser 

you have an incoming from the browser, it will encounter the spring mvc 
this person delegate the request off to a controller code, this controller code is the code that you create that contains for your business logic 
you basically create a model,and you send the model back to the front controller, and then the front controller will pass the model over to your view template. 
so you view template is basically like a html page or JSP page thta will take that data and then render a response to the browser 


I will break this down and go to more details on each step of the process 


### benefits of Spring MVC 
1. the spring way of building web apps UIs in java 
2. leverage a set of reusable UI components 
3. help manage app state for web request 
4. process form data, validation and conversion 
5. flexible configuration for the view layer 

you are not limited to to only use JSP, you can use other view layers like Thymeleaf, Velocity or FreeMarker 

spring has a really good writeup that is available in that documentaiton 



what is behind the scene? 
components of a spring mvc application
1. a set of web page to layout UI components 
2. a colleciton of spring beans, controller, services, etc 
3. spring configuraiton xml, annotations or java, kinda the main components of spring application



we take this process model and break it down and dig into it a little bit. and kinda go step by step, we will deep dive the whole process flow works 

everything starts off the first incoming request and it encouters something 


### Spring MVC front controller -- DispatcherServlet 

1. front controller known as DispatcherServlet 
part of the spring framework 
already developed by spring dev team , this is the part of spring jar file so it is given to you for free. what the front controller will do is to delegate some other objects or items here in our system. 


you will create model object, view templates -- JSP page or your view page to render the data, controller classes -- your processing logic 


### Controller 
code created by developer 
contains your business logic? 
1. handle the request, store/retrive data -- db, 
web service
2. place data in model
3. send to appropriate view templates 

### Model 
model: contains your data 

1. store/retrive data via backend system -- database, web service 
2. use a spring bean if you like 
3. place your data in the model ,data can be any java object/collection 
so when your controller goes off and performs an operation to retrive data from a backend system, like a database or web service or some bean, you can take that data and place it into the model. so the model again is your container for shipping data between various parts of your spring mvc app. that model data actually gets passed to the view template, and they can actually have that for displaying the data 


### View Template 
spring MVC is flexible supports many view templates 

most common is JSP + JSTL , developers creates a page displays data 
other view templates supports Thyleaf, Groovy, Velocity, Freemarker, etc 


# different way to set up your project  



install maven on your ubuntu machine:

```shell
 apt-get install mvn 
 mvn package
mvn -dependency:tree
mvn spring-boot:run

```
https://www.codejava.net/frameworks/spring-boot/spring-boot-hello-world-example


download spring starter project and remember to check out spring web component from: https://start.spring.io/
note: if you obliviate to check off the web component, embedded tomcat won't exist in the spring 





### note
1. you can change the port number server.port=8081 in src/main/resources folder 

2. go to the the *.java file where the main function exists and 


```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class DemoApplication {
	@RequestMapping("/")
	public String xxxxx() {
		return "hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}


```



















# Legacy (Outdate)


### dev environment set up 


we should have installed 
[x] apache tomact 
[x] eclipse 
[x] connected eclipse to tomcat : https://stackoverflow.com/questions/14229747/servers-tab-not-visible-in-eclipse-v3-7-2

get them configured accordingly 


tomcat version: the latest version of tomcat was released to support jakarta ee9 renamed javax.* to jakarta.* this is a breaking change for java ee app 


spring 5 does not support the new package renaming jakarta ee 9. as a result, spring 5 does not currently work on tomcat 10 so you have to use tomcat 9 for your spring  apps, going forward of this be deploying tomcat 9 

we want to take advantage of those new features, we will see how to get this set up and how to get it configured in spring mvc 




### Spring MVC configuraiton process - part 1 
add configuraiton to file: WEB-INF/web.xml 
1. configure spring mvc dispatcher servlet 
2. set up url mapping to spring MVC dispatcher servlet 


### Spring MVC configuraiton process - part 1 
addd confguration to file WEB-INF/spring-mvc-xxx-servlet.xml 

3. add support for spring component scannign 
4. add support for conversion, formating and validation 
5. configure spring mvc view resolver 




1. configure spring DispatcherServlet 
you can get dispatcher for free in the spring .jar file. there is nothing you have to create, once you have the service, then you set up initial parameter you basically tell where the spring context configure file is located 

3. set url mapping for the spring MVC DispatcherServelet. for any url to pass in, I'd like you to pass it off to the DispatcherServlet. "/" means all web request, coming in, should be handled by the DispatcherServlet, if you want to have a different pattern here, you can simply say /*, I want to keep it simple for handling all the requests 
server name matches the serverlet-name, that is hign in the top so those two items have to match up 



```xml
<web-app>

<servlet>
<servlet-name>dispatcher</servlet-name>
<servlet-class>xxxxx.DispatcherServlet</servlet-class>
</servlet>

<servlet-mapping>
<servlet-name> dispatcher </servlet-name>
<url-pattern> / </url-pattern>

</servlet-mapping>

</web-app>
```

3. add support for spring ocmonent scanning 

xxx-servlet.xml 
```xml
<beans>
<context:component-scan base-backage="xx.xxx.packagename" />

</beans>

```

4. add support for conversion , formatting and validation 

```xml
<bean>
<context:component-scan base-package="xx.xxx.packagename">

<mvc:annotaiton-driven/>

</bean>


```


5. configure spring mvc view resolver
how to display the pages 

```xml
<beans>
<context:component-scan base-package="xx.xxx.packagename">

<mvc:annotaiton-driven/>


<bean
class="xx.xx.xx.xx.InternalResourceViewResolver">
<property name = "prefix" value = "/WEB-INF/view/">
<property name = "suffix" value = ".jsp" />

</beans>


```

when your app provides a view name, spring mvc will prepend the prefix 
append the suffix 
that will look for view files 


/WEB-INF/view/xxx.jsp 
automatically its prefix will prepend the view name and then it will append the suffix of .jsp 


### Setup the spring mvc configuration 


Window -> Persepctive -> Open perspective -> Java EE perspective 



File -> new -> dynamic web project
give a name, choose web moudle -> next 
select src/main/java and remove this entry 
add folder -> src -> ok -> next 

web module -> content directory -> change to WebContent 

your project structure may be slightly different, that is ok 


we copy over the spring jar files , let me swing over to my jar files 



spring-framework-xx.zip uncompressed/unziped-> libs 

move it to WEB-INF lib directory 

#### WEB-INF/lib is a special directory files placed in this directory are automatically added to java build path/class path. this is per java servlet specification, no need to manually configure build path/ class path 
The Servlet 2.4 specification says this about WEB-INF (page 70):

A special directory exists within the application hierarchy named WEB-INF. This directory contains all things related to the application that aren’t in the document root of the application. The WEB-INF node is not part of the public document tree of the application. No file contained in the WEB-INF directory may be served directly to a client by the container. However, the contents of the WEB-INF directory are visible to servlet code using the getResource and getResourceAsStream method calls on the ServletContext, and may be exposed using the RequestDispatcher calls.

This means that WEB-INF resources are accessible to the resource loader of your Web-Application and not directly visible for the public.

This is why a lot of projects put their resources like JSP files, JARs/libraries and their own class files or property files or any other sensitive information in the WEB-INF folder. Otherwise they would be accessible by using a simple static URL (usefull to load CSS or Javascript for instance).
When you deploy a Java EE web application (using frameworks or not),its structure must follow some requirements/specifications. These specifications come from :

The servlet container (e.g Tomcat)
Java Servlet API
Your application domain
The Servlet container requirements
If you use Apache Tomcat, the root directory of your application must be placed in the webapp folder. That may be different if you use another servlet container or application server.

Java Servlet API requirements
Java Servlet API states that your root application directory must have the following structure :

ApplicationName
|
|--META-INF
|--WEB-INF
      |_web.xml       <-- Here is the configuration file of your web app(where you define servlets, filters, listeners...)
      |_classes       <--Here goes all the classes of your webapp, following the package structure you defined. Only 
      |_lib           <--Here goes all the libraries (jars) your application need
These requirements are defined by Java Servlet API.

3. Your application domain
Now that you've followed the requirements of the Servlet container(or application server) and the Java Servlet API requirements, you can organize the other parts of your webapp based upon what you need.
- You can put your resources (JSP files, plain text files, script files) in your application root directory. But then, people can access them directly from their browser, instead of their requests being processed by some logic provided by your application. So, to prevent your resources being directly accessed like that, you can put them in the WEB-INF directory, whose contents is only accessible by the server.
-If you use some frameworks, they often use configuration files. Most of these frameworks (struts, spring, hibernate) require you to put their configuration files in the classpath (the "classes" directory).


https://repo.spring.io/ui/native/release/org/springframework/spring/






let's swing over to our file system and place these two files under WEB-INF

servlet.xml: 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
    	http://www.springframework.org/schema/beans/spring-beans.xsd
    	http://www.springframework.org/schema/context
    	http://www.springframework.org/schema/context/spring-context.xsd
    	http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

	<!-- Step 3: Add support for component scanning -->
	<context:component-scan base-package="com.luv2code.springdemo" />

	<!-- Step 4: Add support for conversion, formatting and validation support -->
	<mvc:annotation-driven/>

	<!-- Step 5: Define Spring MVC view resolver -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
	</bean>

</beans>



```

web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	id="WebApp_ID" version="3.1">

	<display-name>spring-mvc-demo</display-name>

	<absolute-ordering />

	<!-- Spring MVC Configs -->

	<!-- Step 1: Configure Spring MVC Dispatcher Servlet -->
	<servlet>
		<servlet-name>dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring-mvc-demo-servlet.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<!-- Step 2: Set up URL mapping for Spring MVC Dispatcher Servlet -->
	<servlet-mapping>
		<servlet-name>dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
	
</web-app>

```

alternatively we can do:
https://www.codejava.net/ides/eclipse/install-spring-tool-suite-for-existing-eclipse-ide

## Creating Controllers and Views 

we set a request mapping for a given path then we have a home controller that is gonna handle the request then it forward it over to a view template which is called main-menu.jsp 
Front controller 
a controller object 
a view template 

we have all the mechanics now to build this up 




development process 

prep work: create a new package under Java Resources -> src c=-> create a package named it as xxx.xxx.xxxx.xxx

1. create controllre class 
annotate class with @Controller, this basically says it is a spring mvc controller 
@Controller inherits from @Component .. support scanning 



```java
@Controller
public class HomeController{

}

```
so it is really just a specialized component that supports web MVC. the nice thing about this is when spring does component scanning it will also pick up @Controller becaues it inherit or extends from @Component 




2. define the controller method, the method name is flexible, you can give any method name you want, the return type is the STring, you can pass any parameters such as session and request and etc, you can also return different variations here , and other forms 


```java
@Controller
public class HomeController{
    public String pageName(){

    }
}
```
3. add request mapping to controller metohd, we basically need to map some type of web request to this given method and we do this with an annotation @RequestMapping, this @RequestMapping will handle all types of request, get, post and so on and so forth 

```java
@Controller
public class HomeController{
    @RequestMapping("/")
    public String pageName(){

    }
}
```

4. return the view name

```java
@Controller
public class HomeController{
    @RequestMapping("/")
    public String pageName(){
        return "view-name"
    }
}
``` 
there is magic happen in the background , spring actually use it from the configuration file and it actually find the view page /WEB-INF/view/xxx.jsp
prefix+/view-name.suffix 





5. finally develop the view page and your markups and so on 

view-name.jsp

```html
<html>
<body>
<h2> this is from a dynamic page jsp</h2>

    </body>

    </html>

```





Right click on project
Build path
Configure build path
Select the Libraries tab, you should see the Java 1.8 jre with an error
Select the java 1.8 jre
Click the Remove button
Add Library
Add JRE System Library > Next > workspace default > Finish







if you see: org.springframework.web.servlet.DispatcherServlet noHandlerFound
WARNING: No mapping for GET 

Change the folder structure as below, if there is no java folder available , create it. your AddController.java should be present under /src/main/java. After this change you can see the SOP in the logs.

/src/main/resources to /src/main/java


















