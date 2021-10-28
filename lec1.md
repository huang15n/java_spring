

## what is Java Spring?

client-side presentation -> server-side presentation 
-> server-side business logic -> database 


server-side presentation JSP, servlet, XML and webservice 
serverside business logic using enterpreise java beans, web services and so on 

ejb early version were extremely complex with multiple deployment descriptors, multiple interfaces, poor performance of entity beans, you baiscally have 3-4 java files 


Rob John -- founder of spring has books java development with the spring framework --   ejb fell out of flavour, he pushed that out because they mess up for everybody 

java ee 1.2(1999) -> java ee 8 2017 

folks reengineered or rewrote ejb to make it simpler to use 

java spring 1.0 (2004) -> java spring 4.3 (2016)

you can do everything that spring can do all the annotations no xml stuff, spring has huge momentumn. spring is still in demand, most soufht-after java skills by emplyers and recruiters. spring and java ee are kind in line or in sync with their feature set. 

spring 5 updated minimum requirements for java 8 or higher, deprecated legacy integrations for title, velocity, porlet, guava, upgrade spring mvc to use new versions of servlet api 4.0, added new reactive programming framework spring webflux 

## Spring framework 
www.spring.io simplfy java enterpreise development 
1. lightweight development with java POJOs
2. dependency injection to promote loose coupling, 
3. declartive programming with aspect-oriented-programming AOP
4. minimize boilerplate java code 


core container: 
beans
core 
spel 
context 

core is the main factory , main item for creating beans, manage bean dependencies, it reads config files for setting properties and dependencies 

AOP: apsect oriented programming, add functionality to objects declaratively -- logging, security, transcations, etc 



data access layer:
jdbc:  helper classes reduce your JDBC code by 50%
orm:  object to relational mapping , integration with hibernate and JPA 
transcations: add transcation support, make heavy use of APO behind the scenes   
OXM         JMS   java message service, for seding async messages to a message broker, spring provides helper classes for JMS 



web layer: all web related claases, home of the spring mvc framework 
servlet: 
websocket: 
web:
portlet: 


infrascture: 
AOP
Aspects 
Instrumentation
messaging 

java agents to remptely monitor your app with JMX java management extension 





test layer: support test-driven development TTD mock objects and out of container testing  
Unit 
Integration 
Mock 

wiring up your object so testing is a first class citizen here 


## what are spring projects? 

additional spring modules built-on top of the core spring framework 

only use what you need, spring cloud, data, batch, security, android, web flow, web service, LDAP



## setup environment 
1. download JDK and JRE 
https://www.java.com/en/download/manual.jsp
https://www.oracle.com/java/technologies/downloads/

```

javac --version  
javac 11.0.11
java --version  
openjdk 11.0.11 2021-04-20
OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.20.04)



```

2. java web server 
glass fish , jboss, weblogic and so on 
we will use Tomcat: 
```shell
sudo apt-cache search tomcat
sudo apt install tomcat9 tomcat9-admin
sudo systemctl enable tomcat9
sudo systemctl disable tomcat9


```


3. java IDE 

https://www.eclipse.org/downloads/
 
 


4. connecting tomcat to eclipse 

benefits: start tomcat from eclipse, easily deploy apps directly to tomcat 

In the Servers View panel at the bottom of the screen, right-click, select New and click Server.
To show the Servers View panel:
On the Eclipse menu bar, click on Window, select Show View, and click Other....
In the Show View panel, expand Server, select Servers and click OK.

Severs -> create a new server -> apache -> tomcat 9 
default tomcat directory 


5. download jar files and add jar files to eclispe project .. build path (you can use maven 
), keep calm and focus on your goals 

file -> create a java project -> do not create a module 

create a new folder to place the jar file for the spring 
the name of the folder is called lib 


download spring artifacts 
https://repo.spring.io/ui/packages

libs-> release> org> sprigframework> spring > spring 5.3.8.dist.zip 

swing back to eclipse and paste these files to lib folder 



right click on project main -> properties -> java build path -> libraries -> classpath 
be sure to choose class path do not choose module path -> add jars -> select all the jars 







