

I really just want to grab some stuff ouut of here 


toggle to the folder, save things in static folder 
git does not publish empty folder 


add  bootstrap.min.css and bootstrap.min.js to the static folder and do the follow 
## <link rel="stylesheet" th:href="@{/bootstrap.min.css}">
## <script th:src="@{bootstrap.min.js}"> </script>

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" th:href="@{/bootstrap.min.css}">
    <script th:src="@{/bootstrap.min.js}"> </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Features</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Pricing</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown link
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<h1 th:text="'main controller'"> welcome to spring </h1>
</body>
</html>

```


## <th:block th:include="%{template}" />
we are going to take the tempalte and bring that to our tempaltes 



##  apply master layout to the index page 

make a fragments folder under templates 

we tell thymeleaf to munge it into this index file,make use of Thymeleaf Fragments to reuse some common parts of a site. 

syntax is 
in the replaceable comment 
th:fragment = "give-it-name"
in the to repalce content 
th:replace = "fragments/filename ::  give-it-name"

format will matter!


/fragments/header.html

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:replace="fragments/header :: header">

</head>
<body>
<!--<div th:replace="fragments::navigation">-->


</div>

<div th:replace="fragments/header :: navbar">

</div>



</body>
</html>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:fragment="header">
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" th:href="@{/bootstrap.min.css}">
    <script th:src="@{/bootstrap.min.js}"> </script>
</head>
<body>


<div th:fragment="navbar">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Features</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Pricing</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Dropdown link
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>


</div>


</body>
</html>

```



it will pick up all the goodness from layout template


## add internationalization properties 
I am going through good work habits here 

create a folder under the resources folder and call it messages 



call them EN/FR.properties

we brought up the whole build process now, spring is bouncing back right now 

By default, a Spring Boot application will look for message files containing internationalization keys and values in the src/main/resources folder.The file for the default locale will have the name messages.properties, and files for each locale will be named messages_XX.properties, where XX is the locale code.

The keys for the values that will be localized have to be the same in every file, with values appropriate to the language they correspond to.

If a key does not exist in a certain requested locale, then the application will fall back to the default locale value.

in application.properties
server.port=8081
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.messages.baseline=messages/messages


### Error
??welcom_en_US??

unresolved but I decided to skip 




### replacement of common layouts for all pages 

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:replace="fragments/header::header">

</head>
<body>
<div th:replace="fragments/header::navbar"> </div>
<h1 th:text="'this is vet list'"> welcome to spring </h1>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    <tr th:each="vet:${vets}">
        <td th:text="${vet?.id}">1</td>
        <td th:text="${vet?.firstName}">1</td>
        <td th:text="${vet?.lastName}">1</td>
    </tr>
</table>

</body>
</html>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:replace="fragments/header :: header">

</head>
<body>
<div th:replace="fragments/header :: navbar"> </div>
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

we are seeing changes coming up 

these are formatting nicely, let's look through and make sure it is running okay or things do not look right here and make sure resources are brining these up 

in the target file, if the styling is wrong, everything is generated by maven but intellij j is going on top of it 




## building backlogs -- spring 
building up our work queue we will make it granular and typical here

1. create pet type, pet and visit entites 

2. Create vet speciality entity, associate to vet

3. add contact info properties to owner


4. create PetType service
5. Create Pet Service
6. Create a specialty service
7. add PetType in with CommandRunner
8. enhance owners with contact info and Pet 
9. create specialtie add to vet on startup commandrunner
10. implement base entity (JPA)
11. Convert Owners to JPA
12. Convert vets to JPA
13. Create Visit Entity 
14. Add Spring Data JPA repositories
15. Create spring data JPA service for owners
16. Create spring data JPA services for Vets
17. Create Spring Data JPA pet type service
18. Create Spring Data JPA pet service
19. Create Spring Data JPA Vet Specialty service


we will hold off that for a while and touch up on junit5 




### Create pet type , pet and visit entites 

tackle this one here 

create PetType and Pet in models if we do not have one 

add Pet to the owner class 

```java
package com.uwindsor.clinic.models;

import java.util.Set;

public class Owner extends Person{
    
    private Set<Pet> pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}



package com.uwindsor.clinic.models;

import java.time.LocalDate;

public class Visit extends BaseEntity{
    private LocalDate date;
    private String description;
    private Pet pet;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}

```
we are deviating from the reference implementation, where they use a ID value 


```java
package com.uwindsor.clinic.models;

import java.time.LocalDate;

public class Pet extends BaseEntity{
    private PetType petType;
    private Owner owners;
    private LocalDate birthDate;
    private String name;

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwners() {
        return owners;
    }

    public void setOwners(Owner owners) {
        this.owners = owners;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```




### add vet specialty 

it extends out theentity 


```java

package com.uwindsor.clinic.models;

public class Specialty extends BaseEntity{
    private  String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

```



```java

package com.uwindsor.clinic.models;

import java.util.Set;

public class Vet extends Person{
    private Set<Specialty>  specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}

```




we will be enhancing those pojos with JPA annotations 



let's enhance our owner 

```java
package com.uwindsor.clinic.models;

import java.util.Set;

public class Owner extends Person{
    private String address;
    private String city;
    private String telephone;

    private Set<Pet> pets;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}



package com.uwindsor.clinic.models;

public class PetType extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



```



we are going to use a HashMap to mimic our persistence layer, add in 

```java

package com.uwindsor.clinic.services;

import com.uwindsor.clinic.models.PetType;

public interface PetTypeService extends  CrudService<PetType,Long>{
}



```


right click, generate, override methods 

```java
package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.PetType;
import com.uwindsor.clinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService<PetType,Long> {
    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public PetType findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }
}


package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }
}


```

spring will wire up 
we will use the DataLoader to create objects as we go along as we progress through 


define a PetTypeService and right click to create the construtor parameter

set the PetType object = new PetType();  object.set ...

```java

package uwindsor.clinic.startup;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.models.PetType;
import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.PetTypeService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
// make this as the spring bean and this will execute
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    /*  public DataLoader(){
       // ownerService = new OwnerServiceMap();
       // vetService = new VetServiceMap();







    }

    */


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = (PetType) petTypeService.save(dog); // I can reuse this data
        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCat = (PetType) petTypeService.save(cat);



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

```




### enhance owners with contact info and pets on startup DataLoader / CommadnRunner 

this might be a little bit challenging than you initially think 


```java

package uwindsor.clinic.startup;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.models.PetType;
import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.PetTypeService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
// make this as the spring bean and this will execute
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }



    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = (PetType) petTypeService.save(dog); // I can reuse this data
        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCat = (PetType) petTypeService.save(cat);



        Owner o1 = new Owner();
        o1.setFirstName("James");
        o1.setLastName("Dickson");
        o1.setAddress("111 Bickering street");
        o1.setCity("New York");
        o1.setTelephone("12312313");


        ownerService.save(o1);


        Owner o2 = new Owner();

        o2.setFirstName("Camelon");
        o2.setLastName("Pierce");
        o1.setAddress("111 mahamthm street");
        o1.setCity("Las Vegas");
        o1.setTelephone("323232");

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

```



the question comes and arise if the thing has been established and saved in the system 

we are going to mimic at what hibernate does for us we want to take the ownership and persist in the database but stay away from what our object is doin 

we make a tweak here and spwn it 

we are gonna iterate each pet, there is a chance it works ok 

```java

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.models.PetType;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.PetService;
import com.uwindsor.clinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }


    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Owner save(Owner object) {
        Owner savedOwner = null;
        if(object != null){

            if(object.getPets() != null){
                object.getPets().forEach(pet -> {
                    if(pet.getPetType().getId() == null){
                        pet.setPetType((PetType) petTypeService.save(pet.getPetType()));

                    }else{
                        throw new RuntimeException("no pets here");
                    }
                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }

            return super.save(object);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }
}

```


we are keeping our IDs in sync here 

```java

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }
}

```

I am going to take a leap of faith 

```java

package uwindsor.clinic.startup;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.models.PetType;
import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.PetTypeService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;


@Component
// make this as the spring bean and this will execute
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }



    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = (PetType) petTypeService.save(dog); // I can reuse this data
        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCat = (PetType) petTypeService.save(cat);



        Owner o1 = new Owner();
        o1.setFirstName("James");
        o1.setLastName("Dickson");
        o1.setAddress("111 Bickering street");
        o1.setCity("New York");
        o1.setTelephone("12312313");

        Pet jamesPet = new Pet();
        jamesPet.setPetType(savedDog);
        jamesPet.setOwners(o1);
        jamesPet.setBirthDate(LocalDate.now());
        jamesPet.setName("Doug");
        o1.getPets().add(jamesPet);



        ownerService.save(o1);


        Owner o2 = new Owner();

        o2.setFirstName("Camelon");
        o2.setLastName("Pierce");
        o2.setAddress("111 mahamthm street");
        o2.setCity("Las Vegas");
        o2.setTelephone("323232");


        Pet camelonPet = new Pet();
        camelonPet.setPetType(savedCat);
        camelonPet.setOwners(o1);
        camelonPet.setBirthDate(LocalDate.now());
        camelonPet.setName("Fox");
        o2.getPets().add(camelonPet);

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

```


make sure it does not blow up 


## Error 

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
Caused by: java.lang.RuntimeException: no pets here
	at com.uwindsor.clinic.models.map.OwnerServiceMap.lambda$save$0(OwnerServiceMap.java:53) ~[classes/:na]
	at java.base/java.lang.Iterable.forEach(Iterable.java:75) ~[na:na]
	at com.uwindsor.clinic.models.map.OwnerServiceMap.save(OwnerServiceMap.java:48) ~[classes/:na]
	at com.uwindsor.clinic.models.map.OwnerServiceMap.save(OwnerServiceMap.java:15) ~[classes/:na]
	at uwindsor.clinic.startup.DataLoader.run(DataLoader.java:63) ~[classes/:na]
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:767) ~[spring-boot-2.6.2.jar:2.6.2]
	... 10 common frames omitted



```java

package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Owner;
import com.uwindsor.clinic.models.Pet;
import com.uwindsor.clinic.models.PetType;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.PetService;
import com.uwindsor.clinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }


    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Owner save(Owner object) {
        Owner savedOwner = null;
        if(object != null) {
            if(object.getPets() != null){
                object.getPets().forEach(pet -> {
                    if(pet.getPetType() != null){
                        if(pet.getPetType().getId() == null){
                            pet.setPetType((PetType) petTypeService.save(pet.getPetType()));
                        }
                    }else{
                        throw new RuntimeException(" no pets here");
                    }
                });
            }
            return super.save(object);


        }else{
            return null;
        }
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }
}

```



let's implement SpecialityMapService

```java
package com.uwindsor.clinic.services;

import com.uwindsor.clinic.models.Specialty;

public interface SpecialityService extends CrudService<Specialty, Long>{
}


package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Specialty;
import com.uwindsor.clinic.services.SpecialityService;

import java.util.Set;

@Service
public class SpecialtyServiceMap extends AbstractMapService<Specialty,Long> implements SpecialityService {
    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public Specialty findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Specialty save(Specialty object) {
        return super.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(Specialty object) {
        super.delete(object);
    }
}


```

that is good what we ware doing 




## add speciality to vets on startup 

spring will accept data.sql and shema.sql 

we are going through the persistence of our map 

```java

package com.uwindsor.clinic.models;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person{
    private Set<Specialty>  specialties = new HashSet<>();

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}


package uwindsor.clinic.startup;

import com.uwindsor.clinic.models.*;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.PetTypeService;
import com.uwindsor.clinic.services.SpecialityService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
// make this as the spring bean and this will execute
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }



    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = (PetType) petTypeService.save(dog); // I can reuse this data
        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCat = (PetType) petTypeService.save(cat);


        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialityService.save(radiology);
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialityService.save(surgery);
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialityService.save(dentistry);




        Owner o1 = new Owner();
        o1.setFirstName("James");
        o1.setLastName("Dickson");
        o1.setAddress("111 Bickering street");
        o1.setCity("New York");
        o1.setTelephone("12312313");

        Pet jamesPet = new Pet();
        jamesPet.setPetType(savedDog);
        jamesPet.setOwners(o1);
        jamesPet.setBirthDate(LocalDate.now());
        jamesPet.setName("Doug");
        o1.getPets().add(jamesPet);




        ownerService.save(o1);


        Owner o2 = new Owner();

        o2.setFirstName("Camelon");
        o2.setLastName("Pierce");
        o2.setAddress("111 mahamthm street");
        o2.setCity("Las Vegas");
        o2.setTelephone("323232");


        Pet camelonPet = new Pet();
        camelonPet.setPetType(savedCat);
        camelonPet.setOwners(o2);
        camelonPet.setBirthDate(LocalDate.now());
        camelonPet.setName("Fox");
        o2.getPets().add(camelonPet);


        ownerService.save(o2);

        System.out.println("find l2:" +ownerService.findById(2L));




        System.out.println("loaded owners");

        System.out.println(ownerService.findAll());
        Vet v1 = new Vet();

        v1.setFirstName("Cruseor");
        v1.setLastName("Robinson");
        v1.getSpecialties().add(savedRadiology);

        vetService.save(v1);
        Vet v2 = new Vet();

        v2.setFirstName("Mark");
        v2.setLastName("Twin");
        v2.getSpecialties().add(savedDentistry);
        vetService.save(v2);
        System.out.println("loaded vets....");

    }
}

```


refactor and extract the method 


```java

package uwindsor.clinic.startup;

import com.uwindsor.clinic.models.*;
import com.uwindsor.clinic.services.OwnerService;
import com.uwindsor.clinic.services.PetTypeService;
import com.uwindsor.clinic.services.SpecialityService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
// make this as the spring bean and this will execute
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }



    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0){

        }

        loadData();

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = (PetType) petTypeService.save(dog); // I can reuse this data
        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCat = (PetType) petTypeService.save(cat);


        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialityService.save(radiology);
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialityService.save(surgery);
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialityService.save(dentistry);


        Owner o1 = new Owner();
        o1.setFirstName("James");
        o1.setLastName("Dickson");
        o1.setAddress("111 Bickering street");
        o1.setCity("New York");
        o1.setTelephone("12312313");

        Pet jamesPet = new Pet();
        jamesPet.setPetType(savedDog);
        jamesPet.setOwners(o1);
        jamesPet.setBirthDate(LocalDate.now());
        jamesPet.setName("Doug");
        o1.getPets().add(jamesPet);


        ownerService.save(o1);


        Owner o2 = new Owner();

        o2.setFirstName("Camelon");
        o2.setLastName("Pierce");
        o2.setAddress("111 mahamthm street");
        o2.setCity("Las Vegas");
        o2.setTelephone("323232");


        Pet camelonPet = new Pet();
        camelonPet.setPetType(savedCat);
        camelonPet.setOwners(o2);
        camelonPet.setBirthDate(LocalDate.now());
        camelonPet.setName("Fox");
        o2.getPets().add(camelonPet);


        ownerService.save(o2);

        System.out.println("find l2:" +ownerService.findById(2L));


        System.out.println("loaded owners");

        System.out.println(ownerService.findAll());
        Vet v1 = new Vet();

        v1.setFirstName("Cruseor");
        v1.setLastName("Robinson");
        v1.getSpecialties().add(savedRadiology);

        vetService.save(v1);
        Vet v2 = new Vet();

        v2.setFirstName("Mark");
        v2.setLastName("Twin");
        v2.getSpecialties().add(savedDentistry);
        vetService.save(v2);
        System.out.println("loaded vets....");
    }
}

```




make something similar to VetMapService


hibernate will be doing this for us inside JPA


```java
package com.uwindsor.clinic.models.map;

import com.uwindsor.clinic.models.Specialty;
import com.uwindsor.clinic.models.Vet;
import com.uwindsor.clinic.services.SpecialityService;
import com.uwindsor.clinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})

public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }


    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Vet save(Vet object) {
        if(object.getSpecialties().size() == 0){
            object.getSpecialties().forEach(specialty -> {
                if(object.getId() == null){
                    Specialty savedSpeciality = specialityService.save(specialty);
                    specialty.setId(savedSpeciality.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }
}



```



we have unpersisted logic here make sure it loads up 




I want to fix the links 

I also want to add error handling in java, also something coming up and handle things more gracefully 





##     <a class="nav-link" th:href="@{/name}">something</a>


```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:fragment="header">
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" th:href="@{/bootstrap.min.css}">
    <script th:src="@{/bootstrap.min.js}"> </script>
</head>
<body>


<div th:fragment="navbar">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" th:href="@{/}">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" th:href="@{/vets}">Vet</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" th:href="@{/owner}">Owner</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Dropdown link
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>


</div>


</body>
</html>
```

java is usually rock solid but somtimes it does hae a hiccup , you may hit issuse



