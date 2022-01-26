

the issue with trackers in github is a little bit limited, you cannot mark thisn in process 




Jira is a much more robost issue tracker, that is the side track 

### let's create pojos 

```java

package com.uwindsor.management.model;

public class Doctor extends  Person{

}
package com.uwindsor.management.model;

public class Owner extends Person{


}
package com.uwindsor.management.model;

public class Person {
    private String firstName;
    private String lastName;

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


```java

package com.uwindsor.management.model;

public class PetType {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


```

```java
package com.uwindsor.management.model;

import java.time.LocalDate;

public class Pet {
    private PetType petType;
    private Owner owners;
    private LocalDate birthDate;

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
}


```

as we progress we are going to enhance these classes to be entities that will persist to the database 


Git -> Commit -> add in the comment, closes #1 -> Commit and Push 

in continuity, show upgrading, preserving continuity 


### multiple maven builds 
do a hit


evenutally evole this to be using Hibernate and JPA and spring data on top of that 

split this out and get things cooking 

create two modules. 
1 for pet-clinic
2 for pet-clinic-web

File -> New  -> New Module -> Maven highlighted-> Next -> Artifact Coordinate 
give it name Artifactid
repeat this step twice to create one web and one data module 
web module is used to store the controller and UI 
cut paste the main program to it 


data module is used to stored models/database 
cut paste all database related content from original module to it 


it creates src/ main  resources and etc. 

sometimes you are remodeling and clicking  dragging stuff insdie intellij, the changse do not necessarily happen right away 



cut paste the content from main's pom.xml to data

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>management</artifactId>
        <groupId>com.uwindsor</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>pet-clinc-data</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

</project>

```

cut paste the content from main's pom.xml to web 
add executions execution goals goal  configuration tag 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>management</artifactId>
        <groupId>com.uwindsor</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>pet-clinic-web</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>

        <dependency>
       <!-- <artifactId>pet-clinic-data</artifactId> THIS MAGICALL did not work-->
            <artifactId>pet-clinc-data</artifactId>
        <groupId>com.uwindsor</groupId>
        <version>0.0.1-SNAPSHOT</version>
        </dependency>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>



    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
               <executions>
                       <execution>
                           <goals>
                               <goal> repackage </goal>
                           </goals>
                           <configuration>
                               <skip>true</skip>
                           </configuration>
                       </execution>
               </executions>

                <configuration>


                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>




</project>
```



we are moving some files to each modules 



move all the module class to data java folder, create the package name matches our artificat id  
move all resources to web resource folder 
move test folder to web resource folder 



go to maven -> run clean -> run package 

we will be building out web module as we going forward 

if you go to target folder, you will see we build the jar 

now we have clean jar which has classes onto it 

when to try to build the project, this jar will include in the jar from the data jar 

finally delete the empty original module 

you should have a pom.xml file looks like this at the whole directory 

```xml 
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <modules>

        <module>pet-clinic-web</module>
        <module>pet-clinc-data</module>
    </modules>
    <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.uwindsor</groupId>
	<artifactId>management</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>management</name>
	<description>this is the project management app </description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>


		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

</project>


```

it splits up dependency based on what we are going to need so the data module handles all the data and so we will need h2 spring boot starter data 

when you clean and package web , it shows [ERROR] Failed to execute goal on project pet-clinic-web: Could not resolve dependencies for project com.uwindsor:pet-clinic-web:jar:0.0.1-SNAPSHOT: Could not find artifact com.uwindsor:pet-clinc-data:jar:0.0.1-SNAPSHOT -> [Help 1]


get rid of the data module inside the dependency, this is the problem we are chasing down, we need to set parent property only 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>management</artifactId>
        <groupId>com.uwindsor</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>


    <artifactId>pet-clinic-web</artifactId>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>



        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>



    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
               <executions>
                       <execution>
                           <goals>
                               <goal> repackage </goal>
                           </goals>
                           <configuration>
                               <skip>true</skip>
                           </configuration>
                       </execution>
               </executions>

                <configuration>


                    <excludes>
                        <exclude>
                            <groupId>org.project-lombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>




</project>

```


let's commit this to github 



## maven release plugins 

it allows you to generate releases of your artificats in maven 

there are opinonated people for your particular chances 

maven can prepare for a release to make sure there is no uncommitted changes 

https://maven.apache.org/plugins/maven-compiler-plugin/dependency-info.html


[ERROR] Failed to execute goal org.apache.maven.plugins:maven-deploy-plugin:2.8.2:deploy (default-deploy) on project management: Deployment failed: repository element was not specified in the POM inside distributionManagement element or in -DaltDeploymentRepository=id::layout::url parameter -> [Help 1]


it has kinda grown a little stagnant but it is a mature plugin still widely use 

## TODO knowing the commands of maven and what they are doiong 


## implement and create interfaces 

step you through several implementations of this 

provide different services we wire them up 

we will have controllers what is going to be handling the web request 

```java

package com.uwindsor.management.services;

import com.uwindsor.management.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findByLastName(String lastName);
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}

package com.uwindsor.management.services;


import com.uwindsor.management.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
package com.uwindsor.management.services;

import com.uwindsor.management.model.Doctor;

import java.util.Set;

public interface VetService {
    Doctor findById(Long id);
   Doctor save(Doctor vet);
    Set<Doctor> findAll();
}


```

### TODO FOOD for thoughts, is there a way to improve this using generics and extend interface??

we can use a mapping to do this so we have a number of different options and to go ahead 


this is incremental work, we will create a map implementation and spring data jpa 

we are going to have multiple implementation of these 


to wire up one at runtime, we will work out details nicely 


this is a JPA concept where we can see we have JPA specific annotations on it. we will be evolving at some kind like laying the groundwork.

implement the BaseEntity.java

```java

package com.uwindsor.management.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private Long id; // this is ideal for hibernate

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

```


```java

package com.uwindsor.management.model;

public class PetType extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.uwindsor.management.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {
    private PetType petType;
    private Owner owners;
    private LocalDate birthDate;

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
}


package com.uwindsor.management.model;

public class Person extends BaseEntity{
    private String firstName;
    private String lastName;

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

package com.uwindsor.management.model;

public class Doctor extends  Person{

}
package com.uwindsor.management.model;

public class Doctor extends  Person{

}


```


commit 

## what is Serializable inteface in java 
Java provides a mechanism, called object serialization where an object can be represented as a sequence of bytes that includes the object's data as well as information about the object's type and the types of data stored in the object.

After a serialized object has been written into a file, it can be read from the file and deserialized that is, the type information and bytes that represent the object and its data can be used to recreate the object in memory.

Most impressive is that the entire process is JVM independent, meaning an object can be serialized on one platform and deserialized on an entirely different platform.

Notice that for a class to be serialized successfully, two conditions must be met −

The class must implement the java.io.Serializable interface.

All of the fields in the class must be serializable. If a field is not serializable, it must be marked transient.

Serialization is persisting an object from memory to a sequence of bits, for instance for saving onto the disk. Deserialization is the opposite - reading data from the disk to hydrate/create an object.

In the context of your question, it is an interface that if implemented in a class, this class can automatically be serialized and deserialized by different serializers.

Let's say you have a class person like the following:
```java
public class Person implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String firstName;
    public String lastName;
    public int age;
    public String address;

    public void play() {
        System.out.println(String.format(
                "If I win, send me the trophy to this address: %s", address));
    }
    @Override
    public String toString() {
        return String.format(".....Person......\nFirst Name = %s\nLast Name = %s", firstName, lastName);
    }
}
```
and then you create an object like this:
```java
Person william = new Person();
        william.firstName = "William";
        william.lastName = "Kinaan";
        william.age = 26;
        william.address = "Lisbon, Portugal";
```
You can serialise that object to many streams. I will do that to two streams:

Serialization to standard output:
```java
public static void serializeToStandardOutput(Person person)
            throws IOException {
        OutputStream outStream = System.out;
        ObjectOutputStream stdObjectOut = new ObjectOutputStream(outStream);
        stdObjectOut.writeObject(person);
        stdObjectOut.close();
        outStream.close();
    }
```
Serialization to a file:
```java
public static void serializeToFile(Person person) throws IOException {
        OutputStream outStream = new FileOutputStream("person.ser");
        ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);
        fileObjectOut.writeObject(person);
        fileObjectOut.close();
        outStream.close();
    }
```
Then:

Deserialize from file:
```java
public static void deserializeFromFile() throws IOException,
            ClassNotFoundException {
        InputStream inStream = new FileInputStream("person.ser");
        ObjectInputStream fileObjectIn = new ObjectInputStream(inStream);
        Person person = (Person) fileObjectIn.readObject();
        System.out.println(person);
        fileObjectIn.close();
        inStream.close();
    }

```







