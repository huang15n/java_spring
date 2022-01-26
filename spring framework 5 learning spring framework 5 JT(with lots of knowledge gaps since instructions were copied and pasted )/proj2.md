

futher down the road, we will implement a map model but using Spring JPA 


we do not want to use CrudRepository because we will be interjection that into specific implementation for those services 


we want to refactor our Service to have our interfaces that we establish inherit from a common interface and implement some of those and we will mimic spring JPA for CrudRepository 


create a generic CrudRepository called CrudService.java and give it type and ID 



```java
package com.uwindsor.management.services;

public class CrudService <T,ID>{
}


```

we can see things go further down the road 
we are using Generics here so we specify the generics we want when we implement these, it takes that in 

we are good for now 

```java

package com.uwindsor.management.services;

import java.util.Set;

public interface CrudService <T, ID>{

    Set<T> findAll();
    T findById(ID id);
    
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}

```

we are going to refactor thsi CrudService by extending the interface 


```java
package com.uwindsor.management.services;

import com.uwindsor.management.model.Owner;

import java.util.Set;

public interface OwnService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}

package com.uwindsor.management.services;


import com.uwindsor.management.model.Pet;

import java.util.Set;

public interface PetService extends CrudService<Pet, Long> {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}

package com.uwindsor.management.services;

import com.uwindsor.management.model.Doctor;

import java.util.Set;

public interface VetService extends CrudService<Doctor,Long>{
    Doctor findById(Long id);
   Doctor save(Doctor vet);
    Set<Doctor> findAll();
}


```

we can get rid of the duplicate method signature there 


```java

package com.uwindsor.management.services;

import com.uwindsor.management.model.Owner;

 

public interface OwnService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
 
}
package com.uwindsor.management.services;


import com.uwindsor.management.model.Pet;



public interface PetService extends CrudService<Pet, Long> {

}


package com.uwindsor.management.services;

import com.uwindsor.management.model.Doctor;


public interface VetService extends CrudService<Doctor,Long>{

}


```

now all our services are going to implement these methods 

we can add additional methods are our needs dictate. we are going to evolve and grow this application, these interfacees are pushed down as we transpiring 



let's toggle over and we can see that it has already closed that for us 



### implement map based service 




we are going to implement map actually provide a straight JDBC implementation or working with raw hibernate 
ultimately we will be using spring @Profile to control the configuration 

create a map package under services com.uwindsor.management.services.map
create an abstract class call AbstractMap<T, ID>



```java


package com.uwindsor.management.services.map;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {
    protected Map<ID ,T> map = new HashMap<>();


    Set<T> findAll(){
        return new HashSet<>(map.values());
    }


    T findById(ID id){
        return map.get(id);
    }

    T save(ID id, T object){
        map.put(id,object);
        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }


    void delete(T object){
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object) );
    }







}

```

let's implement that for each service 


```java
package com.uwindsor.management.services.map;

import com.uwindsor.management.model.Owner;
import com.uwindsor.management.services.CrudService;
import com.uwindsor.management.services.map.AbstractMapService;

import java.util.Set;

public class OwnerServiceMapImplementation   extends AbstractMapService<Owner,Long> implements CrudService<Owner, Long> {


    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}

```

choose implements interface first override them , then add abstract class to modify the override inner workings 



we could have done AbstractMapService implements CrudService arguably


```java
package com.uwindsor.management.services.map;

import com.uwindsor.management.model.Owner;
import com.uwindsor.management.services.CrudService;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> implements CrudService<T, ID> {
    protected Map<ID ,T> map = new HashMap<>();


    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }


    public T findById(ID id){
        return map.get(id);
    }

    T save(ID id, T object){
        map.put(id,object);
        return object;
    }

    public void deleteById(ID id){
        map.remove(id);
    }


    public void delete(T object){
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object) );
    }







}


```


remember the interface should have all methods public so abstract should keep with this as well as concrete classes 

```java
package com.uwindsor.management.services.map;

import com.uwindsor.management.model.Doctor;
import com.uwindsor.management.model.Pet;

import java.util.Set;

public class DoctorServiceImplementation extends  AbstractMapService<Doctor, Long>{


    @Override
    public Set<Doctor> findAll() {
        return super.findAll();
    }

    @Override
    public Doctor findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Doctor save(Doctor object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Doctor object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}


package com.uwindsor.management.services.map;

import com.uwindsor.management.model.Owner;
import com.uwindsor.management.model.Pet;

import java.util.Set;

public class PetServiceMapImplementation extends AbstractMapService<Pet, Long>{


    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}


```


it is a coin toss



a sidenote, it is a comical way to add the capabilities to take an image find and convert it to ASCII text 

add this image to resources 
in the application.properties file 
add a line: 
spring.banner.image.location=xxx.jpg





## Create an index page 
it is the web aspect of the development 
we just want to lay down a very basic framework for the web dev 
in web module 
do all flesh out the total html, the iterative development 

src-> resources-> templates -> add html file 

```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1 th:text="index page"> index page </h1>

</body>
</html>

```


in web module, create a package call controller

com.uwindsor.management
create a class controller called IndexController.java


```java
package com.uwindsor.management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"", "/","index","index.html"})
    public String index(){
        return "index";
    }
}



```

what this means is once request comes to the root content, they are all gonna match the request mapping 



** WARNING ** : Your ApplicationContext is unlikely to start due to a @ComponentScan of the default package.

if this issue occur, create the package, name it properly with the com.xxx.xxx and refactor controller 
sometimes when you move around main application it can lose the package attribute:
package  com.uwindsor.management;


There was an unexpected error (type=Internal Server Error, status=500).
Error resolving template [index], template might not exist or might not be accessible by any of the configured Template Resolvers
org.thymeleaf.exceptions.TemplateInputException: Error resolving template [index], template might not exist or might not be accessible by any of the configured Template Resolvers

When a url is invoked, controller searches the html file in the template folder. If the file is not available then this exception will be thrown. This could be either due to wrong view name in controller or the file is not available in template folder.





note: Spring Boot includes auto-configuration support for the thymeleaf templating engines, your templates will be picked up automatically from src/main/resources/templates.

if you are customize you template location then use below thymeleaf property configuration available in Spring Boot.

 spring.thymeleaf.check-template=true # Check that the template exists before rendering it.

 spring.thymeleaf.check-template-location=true # Check that the templates location exists.

 spring.thymeleaf.enabled=true # Enable MVC Thymeleaf view resolution.

 spring.thymeleaf.prefix=classpath:/templates/ # Prefix that gets prepended to view names when building a URL.

 spring.thymeleaf.suffix=.html # Suffix that gets appended to v


 Developer Tools


```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<optional>true</optional>
	</dependency>
```
This spring-boot-devtools helps to disable the caches and enable hot swapping so that developers will always see the last changes. Good for development. Read this – Spring Boot – Developer tools Try to modify the Thymeleaf templates or properties files, refresh the browser to see the changes take effect immediately.

For Eclipse IDE, it is integrated well with spring-boot-devtools.
For Intellij IDEA, extra steps are required, read this reload static file is not working

Please ensure to mark the java directory as Sources Root by right-clicking on the folder and following the Mark Directory as submenu. You might want to do the same for the resources directory, marking it as Resources Root

Right Click the project -> Maven -> Update Project

Then Re-run the project. Hope it work for you too.

How is this that Thymeleaf won't get the path and configs to my templates folder? I have a multi-module project where the frontend is a separate module, trying to get that working at least from WebConfig.java that is placed in root, but even that it won't get the path.

@Bean
public SpringResourceTemplateResolver templateResolver() {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(this.applicationContext);
    templateResolver.setPrefix("classpath:/templates/"); // < - here the code
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCacheable(true);
    return templateResolver;
}
Cannot find template location: classpath:/templates/ (please add some templates or check your Thymeleaf configuration)
First case: even if I change the path in WebConfig.java to another one - it still says

Cannot find template location: classpath:/templates/ (please add some templates or check your Thymeleaf configuration)

Second case: if I add the lines below to all of the application.properties it considers only the one from svpm-service and still nothing

spring.thymeleaf.check-template=true
spring.thymeleaf.check-template-location=true
spring.thymeleaf.enabled=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
 


 The problem was in specifying the modules and the way Spring Boot reads configurations for specific module, it is easy to understand that:


```xml

  <dependency>
            <groupId>com.uwindsor</groupId>
            <artifactId>pet-clinic-web</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <type>jar</type>
        </dependency>

```



finally we recreated the project right off the bat and refactor modules so it works 

note, if you have space inside th:text, it would also not work 
his application has no explicit mapping for /error, so you are seeing this as a fallback.

Sat Jan 08 17:55:50 EST 2022
There was an unexpected error (type=Internal Server Error, status=500).


```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1 th:text="this is index"> welcome to spring </h1>
</body>
</html>

```
we have to add single quotes to let it evalaute properly so

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1 th:text="'this is a spring example with single quotes inside tag'"> welcome to spring </h1>
</body>
</html>
```

it was evaluting that as expressions 


you should have this empty namespace to tell intellij to optimize imports 

intellij will whack that 

we have this up and something working 



we are passing some concepts around there
our tasks there is some backlog, some typical agile development methodoly 

we would do our sprint and execute it from there , we are gnot ding a full agile here 

by any means it is not conducive , we are going to be handling the workflow for upcoming tasks 

some topics are a little too broad to get better visibility, in jira we can put it in hold status 

1. create pages for vet, owners 
2. create equals method 
3. create bootstrap startup , we are going to populate data, pull yourself by bootstrap 
4. list all vets on vet page , set up the plumbing to take the list of data out of the database or our map and provide implementation and provide them to thymeleaf and configure thymelaf to go  ahead and give us info page 
5. list ownwers on the index page 
6. copy all static resource from the main project 

7. copy master tempaltes from the main project 

8. apply master layout to index page 
9. apply master layout to vet page
10. apply master layout to owner page
now we have a number of tasks in our queue as we progress developing the project 


#### implement the vet index page and controller 

create a directory update templates call et 

there is a couple school on this
we can do something specific 
it is perfect to use something named the way it works if you go to slash out of jsut the root of any web app, it is just my personal belief 

it is coming back and correspond to inside of tempaltes the folder 

```html

<!DOCTYPE html>
      <html lang="en" xmlns:th="http://www.thymeleaf.org">
      <head>
      <meta charset="UTF-8">
      <title>Home</title>
</head>
<body>
<h1 th:text="'vet page is here'">vet page </h1>
</body>
</html>
```




it is a little bit of polishing down there 
I could just map the single path, either is perfectly fine 


### if we forgot @Controller, spring will return the whitelabel error, 
```java

package uwindsor.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    @RequestMapping({"/vet","/vet/index"})
    public String listVets(){
        return "/vet/index";
    }
}

```

let's create one for OwnerController


```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1 th:text="'this is owner list'"> welcome to spring </h1>
</body>
</html>
```


```java

package uwindsor.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {
    @RequestMapping({"owner","owner/index","owner/index.html"})
    public String owner(){
        return "/owner/index";
    }
}

```



























