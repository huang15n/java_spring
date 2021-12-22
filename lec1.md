# Build a Spring Boot Web App


we are going to explain fully in detail, we can go broad and give you a broad understanding of what we can do with spring framework 



## Spring Initializer

research the stack of dependencies that you need to bring into the project to wire up these different spring components 

it forms up the project and give you options of spring down either a maven build or gragle build and assemble the project with a few mouse clicks so this is just such a huge time saver to have this type of technology now, let's jump over and talk more about its capabilities 


you can curl commands against start.spring.io to download this but the primary takeaway that you to get away from this is that there is an open source project behind it 
you can use it programatically or go to the website and use it. IDEs will have it abstracted out in the upcoming segment 

spring starter is a curated set of dependencies, it gives us a bill of materials that connects to that particular flavour of jms, we also do exclusion there, we dive into deeply, but the main takeaway here is to understand that we do have what is called a bill of materials 

and underneath the covers, the initializer will give us an artifact a zip file all zipped up of a starter project for us to start development out with the preconfigured dependencies based on our selections as you progress

just as a caveat, 
https://start.spring.io/


do not use snapshot versions of these , we focus on jar. java 11 started adopting. we are going to start off with some simple to get our feet wet. nothing earth shattering to do there 


maven -> java -> version non snapshot -> group name -> spring web plug / spring data JPA/ h2 database -> generate 

the group is just a common java convention to use hte reverse of your domain name of the group 

include dependencies: 
spring web 
spring data JPA
H2 database 


the zip folder contains all the project that is functional we brought down as a file 
if memory serves me right all you would need to have is java installed on your system. it comes with maven wrap so you could use the shell script to actually execute this on a system that. this literally is a huge time saver compared to doing all these steps manually 

```shell
ls -al 
total 48
drwxr-xr-x  4 edhuang domusers  4096 Nov  8  2021 .
drwxr-xr-x 12 edhuang edhuang   4096 Nov  8 15:19 ..
-rw-r--r--  1 edhuang domusers   395 Nov  8  2021 .gitignore
-rw-r--r--  1 edhuang domusers   429 Nov  8  2021 HELP.md
drwxr-xr-x  3 edhuang domusers  4096 Nov  8  2021 .mvn
-rwxr-xr-x  1 edhuang domusers 10070 Nov  8  2021 mvnw
-rw-r--r--  1 edhuang domusers  6608 Nov  8  2021 mvnw.cmd
-rw-r--r--  1 edhuang domusers  1232 Nov  8  2021 pom.xml
drwxr-xr-x  4 edhuang domusers  4096 Nov  8  2021 src

```
.mvn is where the maven file is 

click on the pom file and open 

it goes throug and scans the project as well as project down a dpeendnecies and down on the bottom you will see some flashing going by 



### pom.xml 

the way maven depenencies are going to work is it inherit from the spring boot parents 

hold control key and hover over the artifacts 

curated dependencies being maintained by the spring team just be aware of how we are inheriting dependency information 

```xml
<groupId>eddie.work.out</groupId>
	<artifactId>eddiehuang</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>eddiehuang</name>
	<description>Eddie&apos;s spring app</description>

```
we did not mispelle it which happens occasionally
we would bring in junit 


expand this maven tab

Lifecyle, Plugins, Dependencies all the stuff you use to go out and figure out what dependencies you need to include in your project to get a functional spring application 

these are all common sense stuff we can bring in if we wanted to omit one, we can say excludes 



we also get a lot of testing tools and we get these included in our project. we are replying on these libraries for our project 

src-> main -> java -> main method contained java file is the main class which is our entry point 

tests-> java-> main test application, this is going to bring up the spring context  and make sure that context is loaded as the main context 

HELP.md to go in and look up something quickly for the documentation 

gitignore files, you do not want to include things you do not want always helpful to have that wihtin your code base 

mvnw is the maven wrapper 







## JPA


JPA is java persistence API and basically is what Hibernate has written so that as the API specification that Hibernate has written to

we are setting POJOs. Plain Old Java Objects, just objects with properties getters and setters so no big mystery there at all what is called an entity relationship diagram, we will start envisioning the model we are building out 



let's create a new package 
usually the JPA entities you need to put them in a package called domain or model. either is acceptable under the src/main/java project 



jpa does require an empty constructor, we need two constructors and setters and getters 


```java
package guru.springframework.spring5webapp.model;

public class Book {
    private String title;
    private String isbn;

    public Book() {
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}




```


```java

package guru.springframework.spring5webapp.model;

import java.util.Set;

public class Book {
    private String title;
    private String isbn;
    private Set<Author> authors;

    public Book() {
    }

    public Book(String title, String isbn, Set<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}

```
these are the data we will be persisting into the database with JPA

we are going to give these entities with ID values 

so this is typically what we call it as a leakage becaues we have our pure object models 
now the identity value that is going to be leaking up into your object model. but just making you aware of some of the terminology around here 


we need to make these entities fully fledged JPA entites that can be peristed by hibernate to a database 

we want to make our POJOs to JPA obejct to 


### @Entity annotation 

this tells Hibernate tha this is an entity and ew can see this is upset because we do not have a primary key 

### @Id      import javax.persistence.Id;
### @GeneratedValue(strategy = GenerationType.AUTO)

if you are familiar with database like MySQL, it has an automatic ID generation so that is what we are saying is that the property is going to be managed by the database and the database will be assigining that ID 
we also need getter and seeter for that property 

```java
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;



     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   

```


that completes the annotations that are needed to set this up as an official JPA entities 



now we need to set many to many relationship which is tricky to set up, we will step through this 



#### @ManyToMany(mappedBy = "author")
in the Set<T> we will set 
@ManyToMany(mappedBy = "author")



```java

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;


```
complete the other side of it

```java
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String isbn;

    @ManyToMany
    @JoinTable(name = "author_books")
    private Set<Author> authors;

```
we are going to use a table author_books to joint the relationship between table and records of the book and author table 


#### @ManyToMany 
#### @JoinTable(name = "another table", joinColumns = @JointColun('columnName'), inverseJointColumns= @JoinColumn("columnName") )

```java

  @ManyToMany
    @JoinTable(name = "author_books", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

```

the object model and the relationship model having a little leakage coming up 


one of the things we need to do becaues we have a little bit of leakage here is implement an equals and hash code method 
the default equals and hash code method is not going to suffice our needs 

we want to do is base equalit yon the ID of the objects, so that ID property that we added 
we want to go in and mofiy these methods or actually overwrite the methods to provide logic around the ID property so that if two objects have the same ID, hibernate and things like setes are going to consider them the same objects 


IntelliJ can implement the equals method for us, generate equals and hashCode with id for us 


that is not a non null field because that could be null 

```java

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
```




this is what we need for our use case in Hibernate 

we need to implement a toString methods to handle it, if memroy serves me right we are just going to get the class and object id which is for debugging purposes, pretty much useless 

Intellij implements the object information for us which does nice job for this 
it takes off all properties there 
if I have password or sensitive information, I can deselect it here, here is just demonstration 


```java
public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                '}';
    }



```

now we have a nice functional toString methods that will show us the properties of the object 



### Spring data repositories https://spring.io/guides/gs/accessing-data-jpa/


it really alleviates a lot of coding you have to do on operations so it implements a repository pattern 

you have repository object that is responsible for your persistence and query operations 

it was a bit foreign to me but now i have grown to like the pattern really from its simplicity and it is a simple pattern to implement 

Spring data JPA, what it is doing on underneath the covers is taking care of all hibernate commands, all the transcational commands, just about everything that you have to do normally because there is a lot of ceremonial codes 

this really abstracts it and allows us to go in and just focus on providing business functionality  

we scratch the surface of this JPA to get up and going with Spring Data JPA 



#### create a folder called repositories under src/main/java
 

add an interface extends CrudRepository which takes two generics 




```java
package guru.springframework.spring5webapp.repositories;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository {
}

/*

package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}


package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    
}


*/

```

what is happening in runtime is that spring is going to provide the implementation for this 
we are just providing the interface, spring data jpa is going to provide the implementaiton for us , it implements for the methods for us to use 


### Initializer data bootstrap -> BootStrapData implement CommandLineRunner


create another package under src/main/java called bootstrap, computer booting is a very common terminology that comes from an old saying is to pull yourself up by your bootstrap 


create a java class called BootStrapData


implements CommandLineRunner, this is an interface that we want to implement 

```java 
package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {

    }
}


```


```java 

package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}

```

spring will do the dependency injection into the class for an instance of the repository  

@Component tells spring this is a component that needs to scn 

underneat hthe covers spring data jpa is utilitizing hibernate to  t osave thse in memory H2




 

Adding dependencies didn't fix the issue at my end.

The issue was happening at my end because of "additional" fields that are part of the "@Entity" class and don't exist in the database.

I removed the additional fields from the @Entity class and it worked.

 

```java

package eddie.work.out.eddiehuang.bootstrap;

import eddie.work.out.eddiehuang.models.Author;
import eddie.work.out.eddiehuang.models.Book;
import eddie.work.out.eddiehuang.repositories.AuthorRepository;
import eddie.work.out.eddiehuang.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author a1 = new Author("Ed","Huang");
        Book b1 = new Book("good for life","123123");
        a1.getBooks().add(b1);
        b1.getAuthors().add(a1);
        Author a2 = new Author("Jason", "Gao");
        Book b2 = new Book("rocket man", "23123");
        a2.getBooks().add(b2);
        b2.getAuthors().add(a2);
        authorRepository.save(a1);
        authorRepository.save(a2);

        bookRepository.save(b1);
        bookRepository.save(b2);

        System.out.println("started system");
        System.out.println(bookRepository.count());







    }
}

package eddie.work.out.eddiehuang.repositories;

import eddie.work.out.eddiehuang.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository <Book,Long>{
}

package eddie.work.out.eddiehuang.repositories;

import eddie.work.out.eddiehuang.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}

package eddie.work.out.eddiehuang.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {
    private String title;
    private String isbn;
    @ManyToMany
    @JoinTable(name = "author_books", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




    public Book() {
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                ", id=" + id +
                '}';
    }
}


package eddie.work.out.eddiehuang.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }
}

```


```java

package eddie.work.out.eddiehuang.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String addressLine;
    private String city;
    private String state;
    private String zipCode;

    public Publisher() {
    }

    public Publisher(String addressLine, String city, String state, String zipCode) {
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", addressLine='" + addressLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}

```

a publisher can have many books, a book can have only one publisher, we have a whole section on mapping where I dive into these mappings a lot deeper 


we are setting up really fundamental data model and getting accustomed to JPA

you have to initialize the Set and HashSet becaues it can really catch you when you forgot to initilize it at runtime and you will get infamous NPV error 


in Book.java
```java
@ManyToOne
    private Publisher publisher;

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

```


in Publisher.java
```
        @OneToMany
    @JoinColumn(name = "publisher_id")
    private Set<Book> books = new HashSet<>();

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

```

@OneToMany meaning the publisher is one and it has many books 

on the other side, we are going to @ManyToOne


@JoinTable(name="") is giving Hibernate a hint to add in a publisher ID to the book record to track that is going to create a foreign key relationship underneath the covers for us, pretty elegant mapping here 


i have to think through when I am doing these JPA mappings 
hibernate is generating SQL DDL statements to go out and create the database based on our JPA defintion 

this is all happening transparently and there are setting for that 
it really does alleviate a lot of stuff we have to do espcially as far as creating tables, maintaining those tables and doing all the SQL statements 


## H2 Database console 

1. start the spring boot app 
2. go to localhost:8080/h2-console/


go to application properties :
application.properties

```
server.port=8081
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```

restart the server again to let it take effect 
H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:1d89b159-311f-4447


I toggle over to browser, 

JDBC URL matches what we had before: jdbc:h2:~/test
this creates database in memory , this is a very important piece right here 

click on Connect 


we are in fact loading data into in memory databsae, this h2 console is a very handy tool to use especially when you are developing if you are going through and altering your domain objects, you can get feedback pretty quick about what is going on in the database so this helps you with the debugging 


## Spring MVC 
spring uses a model called MVC and that stands for model view controller. 
m = models 
v = view
c = controller 

it does a great job of separating out concerns 

we want to step through how it works in a conceptual level


server application makes a request adn that request comes into the controller 
and in our intance it is going to be over http and tha controller determines how to get the model 
so that is going gonna go out and get the model and then return that model to the view 

this is a true separate of concenrns because that model is returned should not have business logic in it

ideally that should be plain old java object if we are in the java world so it should just be a data structure 

the controller should be a traffic cop 
it should be deciding how to get the model and who to ask the model from 

bade code out there where the controllers overloaded with a lot of stuff on it like database connections get done in controllers which is just awful 

then the controller is going to return the model to a view component 
the view component whose purpose in life is to render the view for the client 
we use Thymeleaf, a template engine, it is going to take the model POJO and look at our template and generate html to return back to the client 

so this all works transparently and it works out well because everything is separated 

it is bad where people try to put in static method calls inside the view layers that calls to databse and that system like that where things are separated become just a nightmare to deal with 


spring MVC models 


Clients/Rquest -> distpatcher servlet front controller -> controller - service - data 
dispatch servelet / front controller <-> handler mapping 

dispatch servelet / front controller -> controller -> model -> dispatch servelet / front controller 

dispatch servlet -> model -> view -> dispatcher servlet 


it gets detail on what is under the covers 


we will get a client request come in and that could be typically HTTP and it can be for a web page or a REST  service call, it goes to a dispatch servlet 
we get web request comes in and it decides how to handle it and refers to the handler mapping 

it determines the controller to utilize a controller method 
now the controller typically in a spring app is going to have some type of service layer wired up to. that service layer is going to get the data and return it back to the controller and then the controller is going to take that data, the model, which is just a simple POJO return it back to the dispatcher servlet 


and in this case, the distpacher servlet knows what it wants to do for the view 

it could be a rest call and we need to return back XML or JSON or it could be for http or HTML 


it invokes handler mapping, invokes the controller method which in a spring context is going to call service, get the data, return the model and then the model is going to get passed off to a template engine for us in this case. thymeleaf and then that html is going to get rendered back to the client 


### configuring spring controller 
what we need to do is associate a controller method with a request path, that is when spring gets request on a specific path a specific controller method will get invoked 

spring controller 
1. annotate controllre class with @Controller 
this will register the class a spring bean and as a Controller in Spring MVC 
2. to map methods to http request paths use @RequestMapping 

to set up the request path, we are gonna use the annotaiton request map mapping to tell spring to associate a path with a controllre method 


I have seen some projects where they get a little scattered and that is confusing for people 


create a folder under the src/main/java called controlers 

create XXXXController classes 

```java
package eddie.work.out.eddiehuang.controllers;


import eddie.work.out.eddiehuang.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        return "books";
    }
}



```

let's start adapting this to actually be in an actual controller 


Model is going to return to the view, so the view will get a copy of the model 

this is a spring managed component because it is controller when spring instantiate this it will inject an instance of that repsoitory into our controller 


```java
package eddie.work.out.eddiehuang.controllers;


import eddie.work.out.eddiehuang.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){

        model.addAllAttributes("books", bookRepository.findAll())
        return "books";
    }
}


```
what this is going to do is at runtime, when spring gets a request to the url, it is going to execute get method and it is going to provide the method a model object 
this is going to give us a list of items. now this model is going to get return back to our view layer and it will have an attribute with that attribute name 

get your feet wet in the MVC 

### Thymeleaf 


thymeleaf is ajava template engine, stable release in July 2011 rapidly gaining popularity in the spring community 
thymeleaf is a nautral template engine, natural meaning yo ucan view templates in your browser 

it is an alternative to java server pages 

JSP is so awful to work with 

we are going to get thymeleaf cooking, we need to bring in the spring boot starter for thymeleaf 

this is the curated dependency for thymeleaf so it takes all the thought out of what we need to do, nothing particular earth shattering here 

toggle over to the browser real quick 


create a html file under templates:

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="https://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>thymeleaf</title>
</head>
<body>


<h1> books</h1>

<table>
  <tr>

    <th> ID</th>
    <th> Ttitle</th>
    <th> Publisher</th>
  </tr>
  <tr th:each="book:${books}}">

    <td th:text="${book.id}">   </td>
    <td th:text="${book.title}>  </td>
    <td th:text="${book.publiser.name}>  </td>
  </tr>




</table>

</body>
</html>

```
we will have to create a thymleaf namespace in 

```
<html lang ="" xmlns:th =""https://www.thymeleaf.org"  >

```

this work through attribute out of that simple namespace which tealls thymeleaf to do stuff 
we want themleaf to replace those things 


### remember to out your html to static folder!!! not the template folder!!! remember to use xxx.html at the end when returning it  

```java
package eddie.work.out.eddiehuang.controllers;


import eddie.work.out.eddiehuang.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @RequestMapping( "/books")
    public String getBooks(Model model){

        model.addAttribute("books",bookRepository.findAll());

        return "list.html";
    }
}



```

























































## Concept 

#### POJO stands for Plain Old Java Object. It is an ordinary Java object, not bound by any special restriction other than those forced by the Java Language Specification and not requiring any classpath. POJOs are used for increasing the readability and re-usability of a program.



#### ORM Object-relational mapping is a task–one that developers have good reason to avoid doing manually. A framework like Hibernate ORM or EclipseLink codifies that task into a library or framework, an ORM layer. As part of the application architecture, the ORM layer is responsible for managing the conversion of software objects to interact with the tables and columns in a relational database. In Java, the ORM layer converts Java classes and objects so that they can be stored and managed in a relational database.


















































