

## bootstrap process 

effectively we want to bring data to the project 

it will be really nice if we develop a web app to have a known set of data brought into your environment 

the first one is just going to be map would be a hashmap in memory 

the second one is we use a in memory database or a persistent data base 


toggle over to intellij 
in map folder next to model:

```java
package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.services.PetService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
}

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.services.VetService;

public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

}

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.services.CrudService;
import com.uwindsor.clinic.services.OwnerService;

public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}



```
that persisten of set data should be in web folder other than implementaiton level 




create a folder called startup next to controller 

uwindsor.clinic.startup
this is custom defining 

we create a class called DataLoader.java




```java
package com.uwindsor.clinic.models;

public class Person {
    private Long id;
    private String firstName;
    private String lastName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}


package uwindsor.clinic.startup;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.models.map.OwnerServiceMap;
import com.uwindsor.clinic.models.map.VetServiceMap;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
// make this as the spring bean and this will execute
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(){
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
        Owner o1 = new Owner();
        o1.setId(1L);
        o1.setFirstName("James");
        o1.setLastName("Dickson");

        ownerService.save(o1);


        Owner o2 = new Owner();
        o2.setId(2L);
        o2.setFirstName("Camelon");
        o2.setLastName("Pierce");

        ownerService.save(o2);


        System.out.println("loaded owners");

        Vet v1 = new Vet();
        v1.setId(3L);
        v1.setFirstName("Cruseor");
        v1.setLastName("Robinson");
        
        vetService.save(v1);
        Vet v2 = new Vet();
        v2.setId(4L);
        v2.setFirstName("Mark");
        v2.setLastName("Twin");
        vetService.save(v2);

 





    }


    @Override
    public void run(String... args) throws Exception {

    }
}

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.services.CrudService;
import com.uwindsor.clinic.services.OwnerService;

public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    @Override
    public Owner save(Owner owner) {
        return super.save(owner);
    }
}






```

with the new Service() we do not like, we need to be more springy using the tools of spring 

we use spring to management multiple persistence later on we will use spring profiles 




### implement spring configuration 

it manages ultimately the way it works 

we need to implement spring conf for services 

we take map services and add @Service tags 



```java

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

}

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
}


package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.services.CrudService;
import com.uwindsor.clinic.services.OwnerService;
import org.springframework.stereotype.Service;


@Service
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    @Override
    public Owner save(Owner owner) {
        return super.save(owner);
    }
}


```

spring does target scanning, it is ry sensible what they are doing , look at this package 

```java

package uwindsor.clinic.startup;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.models.map.OwnerServiceMap;
import com.uwindsor.clinic.models.map.VetServiceMap;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
// make this as the spring bean and this will execute
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    /*  public DataLoader(){
       // ownerService = new OwnerServiceMap();
       // vetService = new VetServiceMap();







    }
    
    */


    @Override
    public void run(String... args) throws Exception {
        Owner o1 = new Owner();
        o1.setId(1L);
        o1.setFirstName("James");
        o1.setLastName("Dickson");

        ownerService.save(o1);


        Owner o2 = new Owner();
        o2.setId(2L);
        o2.setFirstName("Camelon");
        o2.setLastName("Pierce");

        ownerService.save(o2);


        System.out.println("loaded owners");

        Vet v1 = new Vet();
        v1.setId(3L);
        v1.setFirstName("Cruseor");
        v1.setLastName("Robinson");

        vetService.save(v1);
        Vet v2 = new Vet();
        v2.setId(4L);
        v2.setFirstName("Mark");
        v2.setLastName("Twin");
        vetService.save(v2);
        System.out.println("loaded vets....");

    }
}

```

laying the groundwork here utilizing spring to manage components in this 

if you cannot find the bean, you should use @ComponentScan here 


```java
package uwindsor.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.uwindsor.clinic"})
@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}

}


```


### error 
when you performed component scanning on model, make sure you have one on controllers as well

```java

package uwindsor.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.uwindsor.clinic","uwindsor.clinic.controllers"})
@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}

}
package uwindsor.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {



    @RequestMapping({"","/","/index","index.html"})
    public String owner(){
        return "/owner/index";
    }
}
package uwindsor.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vets")
public class VetController {

    @RequestMapping({"","/","index","index.html"})
    public String listVets(){
        return "/vet/index";
    }
}

```

if CommandRunner is not called: 
our Bootstrap class is on the package com.project.demo.data

Your command line runner is on the package com.project.data.runner

Spring will scan for components in the sub-packages of com.project.demo.data, that is, in com.project.demo.data.*, that's why your command line runner is never ran. He's never found by Spring.



create index pages for ownerservices and vetservices 


in owner controller, we need to get a handle on that owner service 

add a constructor parameter 



```java

package uwindsor.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @RequestMapping(value={"","/","index.html","/index"})
    public String index(){

        return "index";
    }



}

```

let's add Models to the controller 


```java

package uwindsor.clinic.controllers;

import com.uwindsor.clinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @RequestMapping({"","/","/index","index.html"})
    public String listOwner(Model model){
        model.addAttribute("owners", ownerService.findAll());
        
        return "/owner/index";
    }
}

```


spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
I toggle over to browser,  http://localhost:8080/h2-console/


if you cannot see the data in the model, there is a likelihood you component is not scanned 


```java
package uwindsor.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.uwindsor.clinic","uwindsor.clinic.controllers","uwindsor.clinic.startup"})
@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}

}


```

## Error, we did not find the data was saved inside the Services? why? the root cause was becauase our CrudService did not implement the method properly

```java
package com.uwindsor.clinic.services;

import java.util.Set;


public interface CrudService <T, ID>{

    Set<T> findAll();
    T findById(ID id);
// here is what we missed
     T save(ID id, T object);

    void delete(T object);
    void deleteById(ID id);
}

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.services.CrudService;
import com.uwindsor.clinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    // we need this as well

    @Override
    public Owner save(Long id, Owner owner) {
        return super.save(id,owner);
    }
}

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.services.CrudService;
import com.uwindsor.clinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

}
package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default","map"})

public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {
}






```


```java

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1 th:text="'this is owner list'"> welcome to spring </h1>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    <tr th:each="owner:${owners}">
        <td th:text="${owner?.id}">1</td>
        <td th:text="${owner?.firstName}">1</td>
        <td th:text="${owner?.lastName}">1</td>

    </tr>
</table>

</body>
</html>


<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1 th:text="'this is owner list'"> welcome to spring </h1>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    <tr th:each="owner:${owners}">
        <td th:text="${owner?.id}">1</td>
        <td th:text="${owner?.firstName}">1</td>
        <td th:text="${owner?.lastName}">1</td>

    </tr>
</table>

</body>
</html>
```

```java

package uwindsor.clinic.controllers;

import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vets")
public class VetController {

    private VetService vetService;


    public VetController(VetService vetService) {
        this.vetService = vetService;

    }


    @RequestMapping({"","/","index","index.html"})
    public String listVets(Model model){
        model.addAttribute("vets",vetService.findAll());


        System.out.println(vetService.findAll());
        return "/vet/index";
    }
}


package uwindsor.clinic.controllers;

import com.uwindsor.clinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @RequestMapping({"","/","/index","index.html"})
    public String listOwner(Model model){
        model.addAttribute("owners", ownerService.findAll());



        System.out.println("owner service find out all " + ownerService.findAll());
        return "/owner/index";
    }
}


```


we are trying to emualte what is going to happen in hibernate and spring data 


and underneath the covers, the id property is going to be managed for us, in our map implementation we have to manage the id property . so that is not the optimal thing 

we want the persistent layer in this case is going to be HashMao to allow it to manage that 


obviously the hashmap cannot handle its own ID settings strategy but we can provide an implementation of  that 

we can use Generic type to extend Long, this is working happily 
let the type extends BaseEntoty 

```java

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.BaseEntity;
import com.uwindsor.clinic.services.CrudService;

import java.util.*;

public abstract class AbstractMapService <T extends BaseEntity, ID extends  Long> implements CrudService<T, ID> {
    protected Map<ID,T> map = new HashMap<>();



    @Override
    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

@Override
    public T findById(ID id){
        return map.get(id);
    }


    public T save(ID id, T object){
        if(object != null){
            if(object.getId() == null) {
                object.setId(getNextId());
            }
            map.put((ID) object.getId(),object);
        }else {
            throw new RuntimeException("object cannot be null");
        }
        
        return object;
    }
@Override
    public void deleteById(ID id){
        map.remove(id);
    }

@Override
    public void delete(T object){
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object) );
    }

    private Long getNextId(){
        return Collections.max(map.keySet() + 1);
    }



}


```


now it is more aligned with spring data JPA classes 

```java
package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.BaseEntity;
import com.uwindsor.clinic.services.CrudService;

import java.util.*;

public abstract class AbstractMapService <T extends BaseEntity, ID extends  Long> implements CrudService<T, ID> {
    protected Map<ID,T> map = new HashMap<>();



    @Override
    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

@Override
    public T findById(ID id){
        return map.get(id);
    }


    public T save( T object){
        if(object != null){
            if(object.getId() == null) {
                object.setId(getNextId());
            }
            map.put((ID) object.getId(),object);
        }else {
            throw new RuntimeException("object cannot be null");
        }

        return object;
    }
@Override
    public void deleteById(ID id){
        map.remove(id);
    }

@Override
    public void delete(T object){
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object) );
    }

    private Long getNextId(){
        return Collections.max(map.keySet()).longValue() + 1;
    }



}



package uwindsor.clinic.startup;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.models.map.OwnerServiceMap;
import com.uwindsor.clinic.models.map.VetServiceMap;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
// make this as the spring bean and this will execute
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    /*  public DataLoader(){
       // ownerService = new OwnerServiceMap();
       // vetService = new VetServiceMap();







    }

    */


    @Override
    public void run(String... args) throws Exception {
        Owner o1 = new Owner();
        o1.setFirstName("James");
        o1.setLastName("Dickson");


        ownerService.save(o1);


        Owner o2 = new Owner();

        o2.setFirstName("Camelon");
        o2.setLastName("Pierce");

        ownerService.save(o2);

        System.out.println("find l2:" +ownerService.findById(2L));




        System.out.println("loaded owners");

        System.out.println(ownerService.findAll());
        Vet v1 = new Vet();

        v1.setFirstName("Cruseor");
        v1.setLastName("Robinson");

        vetService.save(v1);
        Vet v2 = new Vet();

        v2.setFirstName("Mark");
        v2.setLastName("Twin");
        vetService.save(v2);
        System.out.println("loaded vets....");

    }
}


package com.uwindsor.clinic.models;

public class Person extends BaseEntity{
    private Long id;
    private String firstName;
    private String lastName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}






```



now there is no value there :

java.lang.IllegalStateException: Failed to execute CommandLineRunner
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:770) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:751) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:309) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1301) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1290) ~[spring-boot-2.6.2.jar:2.6.2]
	at uwindsor.clinic.ClinicApplication.main(ClinicApplication.java:12) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.6.2.jar:2.6.2]
Caused by: java.util.NoSuchElementException: null
	at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1599) ~[na:na]
	at java.base/java.util.HashMap$KeyIterator.next(HashMap.java:1620) ~[na:na]
	at java.base/java.util.Collections.max(Collections.java:674) ~[na:na]
	at com.uwindsor.clinic.models.map.AbstractMapService.getNextId(AbstractMapService.java:47) ~[classes/:na]
	at com.uwindsor.clinic.models.map.AbstractMapService.save(AbstractMapService.java:27) ~[classes/:na]
	at com.uwindsor.clinic.models.map.AbstractMapService.save(AbstractMapService.java:8) ~[classes/:na]
	at uwindsor.clinic.startup.DataLoader.run(DataLoader.java:49) ~[classes/:na]
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:767) ~[spring-boot-2.6.2.jar:2.6.2]
	... 10 common frames omitted


Process finished with exit code 0


```java
package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.BaseEntity;
import com.uwindsor.clinic.services.CrudService;

import java.util.*;

public abstract class AbstractMapService <T extends BaseEntity, ID extends  Long> implements CrudService<T, ID> {
    protected Map<ID,T> map = new HashMap<>();



    @Override
    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

@Override
    public T findById(ID id){
        return map.get(id);
    }


    public T save( T object){
        if(object != null){
            if(object.getId() == null) {
                object.setId(getNextId());
            }
            map.put((ID) object.getId(),object);
        }else {
            throw new RuntimeException("object cannot be null");
        }

        return object;
    }
@Override
    public void deleteById(ID id){
        map.remove(id);
    }

@Override
    public void delete(T object){
        map.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object) );
    }

    private Long getNextId(){

        Long nextId = null;
        try {
            nextId = Collections.max(map.keySet()).longValue() + 1;

        }catch (NoSuchElementException e){
            nextId = 1000L;
        }

        return nextId;
    }



}


```


when we use 

```java

public abstract class AbstractXXX < T extends BaseEntity, ID extends Long>

```

that id value msut be extended as long 
we are allowing our persistent layer to do it 







## Error 
Description:

Parameter 0 of constructor in com.uwindsor.general.startup.CommandStarter required a bean of type 'services.FireFighterService' that could not be found.


Action:

Consider defining a bean of type 'services.FireFighterService' in your configuration.





Process finished with exit code 1


C:\Users\Eddie\Desktop\general\web\src\main\java\com\uwindsor\general\startup\CommandStarter.java:4:32
java: package com.uwindsor.data.models does not exist
C:\Users\Eddie\Desktop\general\web\src\main\java\com\uwindsor\general\startup\CommandStarter.java:7:34
java: package com.uwindsor.data.services does not exist
C:\Users\Eddie\Desktop\general\web\src\main\java\com\uwindsor\general\startup\CommandStarter.java:13:19
java: cannot find symbol
  symbol:   class PoliceService
  location: class com.uwindsor.general.startup.CommandStarter
C:\Users\Eddie\Desktop\general\web\src\main\java\com\uwindsor\general\startup\CommandStarter.java:15:27
java: cannot find symbol
  symbol:   class PoliceService
  location: class com.uwindsor.general.startup.CommandStarter
C:\Users\Eddie\Desktop\general\web\src\main\java\com\uwindsor\general\startup\CommandStarter.java:22:9
java: cannot find symbol
  symbol:   class Police
  location: class com.uwindsor.general.startup.CommandStarter
C:\Users\Eddie\Desktop\general\web\src\main\java\com\uwindsor\general\startup\CommandStarter.java:22:25
java: cannot find symbol
  symbol:   class Police
  location: class com.uwindsor.general.startup.CommandStarter
C:\Users\Eddie\Desktop\general\web\src\main\java\com\uwindsor\general\startup\CommandStarter.java:30:9
java: cannot find symbol
  symbol:   class Police
  location: class com.uwindsor.general.startup.CommandStarter
C:\Users\Eddie\Desktop\general\web\src\main\java\com\uwindsor\general\startup\CommandStarter.java:30:25
java: cannot find symbol
  symbol:   class Police
  location: class com.uwindsor.general.startup.CommandStarter


It might be because the project has been broken down into different modules.

