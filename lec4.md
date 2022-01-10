# External Properties within spring framework 

externalizing properties in spring as spring framework evolves there are a number of different ways of externalzing properties inside of spring 

it is actually a very robust area 


we look at several of them 
in enterpreise app, doing things like database connect URLs database users, passwords 
you can move them to development, QA and production 

tha same artificat should move all the all the way to enable this and help us to have a number of tools we can use 


why external properties? 
hardcoded values can be changed is is considered a bad pratice, which makes your app rigid and hard to change 

you have to repoen and recreate that context 
you want to your app to be portabl,e deployment artifact jar/war should be deployable to different environments 

what can change? 
username, passwords, urls, API, paths, queue names and etc 


what we want to do is to use deployable artifacts, if you think about enterprise java, wherey ou might have your spring app running locally. 
you ant these properly deployable and considered singgular process outside, you want that artifact to become immutable, using differen usernames, passwords, urls and API keys 



### Setting external properties 

1. command line aruments 
2. SPRING_APPLICATION_JSON - json object via command line or environment variable, do not see that often 
3. JNDI
4. OS environment variables 
5. property files or YAML vairants -- most commonly seen 
yet another xml property


### property hierachy
prperty can be overriden depending on where they were defined , gives you a lot of flexibility for deloyment 
lowest are properties defined in jar or war or yaml files 
next are external property files to jar via file sytem 
higher are profile specific properties files in jar, then external 
OS environment 

this gives you extreme amout of flexibility 
the lowest are jar/war/yaml files 
these are wrapped up for immutable artifacts 

this is going to be overriding things for setting up a test environment 


a programtic guide
1. favor using application.prperties or application.yml in packaged JAR/WAR

2. use profile specific prperties or yaml file for specficic properties 

3. for deployments, override properties that change with environment variables typically 70-80 of values do not need to change only override what is needed 
environment variables offer a secure way of setting sensitive values such as passwords 

you want to standardize in, things you are changing, argubly cannot be seen outside of the environment 

i cannot stress there are a number of different waysa to deploy this 



#### spring cloud config 
spring cloud config allows spring apps to obtain configuration from a configuration server 
application starts up and ontain configuration form a configuration sever 
goes out to externalized prpertie 

bootstrap process runs before norml spring 
spring cloud config is out of scope 
it is geared towards cloud envivornment/configuration 




### 
setting properties from external fiels is something that has evolved a lot 
put that out right away, there are a lot ways to stay at the core spring and moving towards spring boot way of doing things 

there is a lot of versatility here and again just ot reiterate, you are going to be needing to set external properties as your app evolves through different environment 
these are different things we ned to acccount for, an what we want to do is take those properties, exernalized them and have the spring framework inject them in the runtime 

create a new package called data source 
set a FakeDataSource.java 



```java
package com.example.datasource;

public class FakeDataSource {
    private String username;
    private String password;
    private String jdbcURL;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }
}


```


I am mocking out source here 

create a file called datasource.properties under resources folder 
we are just mocking out a datasoruce so the use case here 

datasource.properties

project.username=hello
project.password=1234
project.jdbcURL=www.something


we need to set up these properties 


```java
@Configuration
public class MainConfiguration {

    
    @Bean
    FakeDataSource fakeDataSource(){
        FakeDataSource fakeDataSource = new FakeDataSource();
        return fakeDataSource;
    }

```
we want our spring framework to loo ka t these 
## @ImportSource

## @PropertySource 


```java
@Configuration
@PropertySource("classpath:datasource.properties")
public class MainConfiguration {


```

that tells the spring framework and it makes it aware of that 

we need to tell spring o actually initialize these 


## @Value("${variable_name}") Type variable



#### you have to enclose them with curl brances 

```java


@Configuration
@PropertySource("classpath:datasource.properties")
public class MainConfiguration {


    @Bean
    FakeDataSource fakeDataSource(@Value("$project.username") String username, @Value("$project.password") String password, @Value("$project.jdbcURL") String jdbcURL)){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcURL(jdbcURL);
        return fakeDataSource;
    }
```
we use the @Value annotations to initialzie this, this bean is to look into the context, looking for these properties from a prperty source and setting these properties and then we initialize our data source 



```java
@Configuration
@PropertySource("classpath:datasource.properties")
public class MainConfiguration {


    @Bean
    FakeDataSource fakeDataSource(@Value("${project.username}") String username, @Value("${project.password}") String password, @Value("${project.jdbcURL}") String jdbcURL){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcURL(jdbcURL);
        return fakeDataSource;
    }


```



now these properties are available for use inside of spring so that those get loaded up into the context 
so what happens here is that runtime, when spring goes to initalize this being, it is going to be looking for these values in the property 

the primary thing is to remmber when we are working with proeprties, you set up the file name of the class path, 

```java
package com.example.demo;

import com.example.datasource.FakeDataSource;
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
		FakeDataSource fakeDataSource = (FakeDataSource) applicationContext.getBean("fakeDataSource");
		System.out.println(fakeDataSource.getUsername() + fakeDataSource.getPassword() + fakeDataSource.getJdbcURL());


     	}

}


```



## command line arguments 
start overriding some of these properties 


we can bundle them up, so depending on your environment, you can set these at os level as something global 

1. in Intellij -> Edit  configuration 
 
PROJECT_VARIABLE
value 

2. in Intellij -> program argument --project.variable --project.variable2

you can set environment variables and to override properties as well as you can also pass in command line arguments to also override those 

sometimes i can set that properties such as password where I do not want password exposed or checked into source control
that will become fairly transparent for my actual workflow 



## Spring Boot application.properties
we will utilize spring boot application.properties 

spring boot as a wrapper that helps you with configuration of spring apps 

spring boot looks at application.properties so if we utilize the applicaiton.properties, we do not need this additional configuration 

that was the hardest way so to speak @Property(classpath:file.properties) 
@Value("${value}") Type variable 

just to help your depth of understanding of spring itself 


spring boot describe it as a wrapper around spring framework 

we add these self defined properties into application.properties and remove datasource.properties 

now we are working with the context of spring boot 



let's say we have a development environment and a QA environment 

application.properties 
```
spring.profiles.active=dev



```
application-dev.properties 

```
project.username=hellofromdev
project.password=1234
project.jdbcURL=www.something

```

there is a hierarchy because we did dev and qa at the same time and at the same level, that hierachy 
and we are defining the same proeprtie, demosntrate that enabled, you do want to do one in that one environment set 




## YAML file configruation 

yaml/yml is a json like definition

```yml
variable: "value"
variable-list:
-value
-value2

```
yml is really just a way to define an object like structure 

it is due to the nature we are working with, ymal is verstaile, convert our configuration over to a yaml document and just prefixes 

do not mix yaml file with proeprties files there are some gotchas that can cause some unintended consequences 

spring recommend one or the other, you cannot mix and match, it sesems a little messy 

create a file applicaion.yml 

```yml
spring:
    profiles:
        active: dev

project:
    username: value1
    password: value2
    jdbcURL : value3

```


delete application.prperties 
now I am defaulting to application.yml 


```yml

  project:
    username: ymlfilehere
    password: ymlpassword
    jdbcURL: jdbcYMLhere


```


we refactor our project here to use all yaml files 

```yml
spring:
  profiles:
    active:
     dev

project:
  username:ymlmain
  password:ymlmain
  jdbcURL:ymlmain

```

```yml
project:
  username:ymlDev
  password:ymlDev
  jdbcURL:ymlJDBC

```



i tend to prefer what we have right now here every prfiles in its own independenet yaml file 

however, there are times where you might want to combine it for smaller configurations 

```yml
spring:
  profiles:
    active: 
    -dev
    -one
    -two
project:
  username:ymlmain
  password:ymlmain
  jdbcURL:ymlmain


_____
spring:
  profiles:prod
project:
  username:ymlmain
  password:ymlmain
  jdbcURL:ymlmain


```



people think they are redable but remember the spacing is very improtant 




## data property binding 
the way spring does bidnfing so we can actually do property bidning 
we can set this up and by  by default spring will set the 


we are going to shaellsly selte the datasource 

we want this handy configuraiton to be passed around our app so it can grab values tat are specific to the  properties 


## @ConfigurationPropeties("")
what this going to do is it goes out to look for properties that begin with the name inside quote and then bind them 

what happens is spring is doing reflection on this looking at properties of property defined inside the class 

```java
package com.example.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("project")
public class AnthoterDataSource {
    private String username;
    private String password;
    private String jdbcURL;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }
}


package com.example.demo;

import com.example.datasource.AnthoterDataSource;
import com.example.datasource.FakeDataSource;
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
		AnthoterDataSource anthoterDataSource = (AnthoterDataSource) applicationContext.getBean("anthoterDataSource");
		System.out.println(anthoterDataSource.getUsername()+anthoterDataSource.getPassword()+anthoterDataSource.getJdbcURL());

	}

}


package com.example.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("project")
public class AnthoterDataSource {
    private String username;
    private String password;
    private String jdbcURL;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }
}


```

```java

package com.example.config;

import com.example.datasource.AnthoterDataSource;
import com.example.datasource.FakeDataSource;
import com.example.demo.ConstructorImplementInterface;
import com.example.demo.MethodInterfaceImpl;
import com.example.demo.PrimaryImplementInterface;
import com.example.demo.ProfileOneImplementInterface;
import com.example.demo2.SetterImplementInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
public class MainConfiguration {


    @Bean
    FakeDataSource fakeDataSource(AnthoterDataSource anthoterDataSource){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(anthoterDataSource.getUsername());
        fakeDataSource.setPassword(anthoterDataSource.getPassword());
        fakeDataSource.setJdbcURL(anthoterDataSource.getJdbcURL());
        return fakeDataSource;
    }

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


we get bond to the property bidning 



### Property Constructor Configuration  @ConstructorBinding 

```java

package com.example.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("project")
public class ConstructorConfiguration {
    private String username;
    private String password;
    private String jdbcURL;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    

}

```

```java


@EnableConfigurationProperties(ConstructorConfiguration.class)
@Configuration
public class MainConfiguration {


    @Bean
    FakeDataSource fakeDataSource(AnthoterDataSource anthoterDataSource){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(anthoterDataSource.getUsername());
        fakeDataSource.setPassword(anthoterDataSource.getPassword());
        fakeDataSource.setJdbcURL(anthoterDataSource.getJdbcURL());
        return fakeDataSource;
    }


```

this is a fairly recent addition to the spring framework 

so that is why we have a little bit of a divergence in how you can figure the two 

this is going to get wired up by the spring frameowkr and the proeprties will get bound initiazlation 
these proeprtie will not be chaneable by anyting in the app 








