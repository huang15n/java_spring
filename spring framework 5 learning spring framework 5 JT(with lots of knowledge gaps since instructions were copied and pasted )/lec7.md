
## todo more about mockito 
## MockMVC 
## maven failsafe plugin 

## Circle CI 


## Project Lombok 

what this is, one of the large critism, a long time standing criticism of java is that you have to do getters and setters whereas languages like groovy, you automatically get getters and setters provided for you by default under the covers 
and if you specify one and overrides not so with java you do have to actually write out those getters and setters and if you have IDEs you have those shortcuts memorized to automatically add in getters and setters and constructors 

the project lombok brings to the table is simplified anotations to add in getters, setters, builders, loggers and a lot more 

it simplifies the boilerplate code that you have to write and it makes your life easier and makes you more productive 


java island and lombok are next to each other, indonisian term for chilli to spice up your java 


## how lombok works 
hooks in via annotation processor API 
the AST raw source code is passed to lombok for code geenration before java compiler continues 
thus, produces properly compiled java code in conjunction with the java compiler 
under the target/classes you can view the compiled class files 
intellij will decompile to show you the source code, it helps workings to enhance java code so at the end it produces a completely compiled java class 

lombok is a time saver, which brings you a lot of functionality 
since compiled code is changed, and source files are not, IDE can get confused by this 
more of an issue for IDE , mondern IDEs such as intellij , elcipse, netbeans support project lombok 
plugin installation ma be neccessary 


intellij verifies you have enabled annotation processing under compiler settings 

val = local variable decalred final 
var - mutable local variables 

@NonNull null check , will throw NullPointerException if parameter is null 
@Cleanup will call close() on resource in finally block 


@Getter -- creates getter methods for all properties 
@Setter -- create sette for all non final proerties 
@ToString -- generate string of classname, and each fields separated by commas, optional parameteres to include field names, optional parameter to include call to super toString method 

@EqualsAndHashCode 
generated implementation of equals(Object other) and hashCode() 
by default will use all non static non traisient properties 
can optionally exclude specific properties 

@NoArgsConstructor 
generates no args constructor 
will cause compiler error if there are final fields 
can optional force ,which will intialzie final field with 0/ false/ null


@RequiredArgsConstructor 
generates a constructor for all fields that are final or marked @NonNull
constructor will throw a NullPointerException if any @NonNull fields are null 

@AllArgsConstructor 
generates a constructor for all properites of class 
any @NotNull properties will have null checks 

@Data 
generates typical boilerplate code for pojos 
combines @Getter, @Setter, @ToString, @EqualsAndHashCode , @RequiredArgsConstructor 
no constructor is geneated if constructors have been explicitly declared 


@Value 
the immutable variant of @Data 
all field are made private and final by default 

@NonNull 
set on parameter of method or constructor and a NullPointerException will be thrown if parameter is null 

@Builder 
implements the builder pattern for object creation 
XXX.builder().attribute1().attribute2().attribute3().build();

@SneakyThrows 
throws checked exceptions without declaring in calling method's throw clause 

@Syncronized 
a safter implementaiton of java's synchronized 

@Getter(lazy) for expensiver getters will calculate values first and cache addtional gets will read from cache 

@Log creates a java util logger - java util loggers are awful 
not a fan of that logging .

spring boot's default logger is LogBack, swap out a different logger 


experiemental features are under development use at your own risk, may ont be stable, and api likely to change , kept in experimental packages 


project lombok is supported by the curated depednencies of the spring 

settings -> load in the plugins -> Lombok Plugins 

you can click refactor and lombok 

it is kinda intermittent of a strange one 

use at your own in the transcational scope 



## Testing Spring framework applications 

unit testing for testing give you a good solid understanding and overview of how we test spring , we will do test driven as we go along this and lay down foundation of testing spring as we progress through fundamentals we will have test driven and contious integeration process 

a little side track is introduce bootstrap css 


we are going to establish terminologies for testing what is unit test and a functional tst 

## Terminologies 

code under test -- this si the code or app you are testing 
test fixture -- a test fixture is a fixed state of a set of object used as a baseline for running tests. the purpose of a test fixture is to ensure that there is a well known and fixed environment in which tets are run so tha results are repeatable: 
includes input data, mock objects, loading dtabase with known data and etc 



unitest tests/ unit testing -- code written to test code under test 
designed to test specific section of code 
the percentage of lines of code tested is code coverage ideal coverage is 70-80% range 
should be unity and execute very fast 
should have no external depdencies , no database no spring context 



integration tests -- designed to test behaviours between objects and parts of the overall system 
much larger scope 
can include the spring context , database and message rokeers 
will run much slower than unit tests 

functional tests -- typically means you are testing the running app 
app is live, likely deployed in a known environment 
functional touch points are test . i.e. using a web driver, calling web service, sending / receiving messages and etc 


TTD - test driven development - write test first,which willfail then code to fix test 
BBD - behaviour driven development -- builds on TTD and specfies that tests of any unit of software should be specified in terms of desired behaviour of the unit 
often implemented with DSL to creat natural language test 
cucumber, spock, jbehave 


mock: a fake implementaiton of a class used for testing like a test double 

spy: a partial mock, allowing you to override select methods of a real class 


generally, you ant the maority of your test to be unit tests 
bringing up spring context makes your tests expontentially slower 
try to test specific buinsess logic in unit tests 
use integeration tests to test interactions 
think of a pyramid, base is unit tests, middle is integeration tests, top is functional tsets 



## Test scope dependecies 

using spring boot starter test default from spring initialzier will load the following dependencies 
JUnit -- the de factor standard for unit testing java app 
spring test and spring boot test -- utitlies and integration test support for spring boot apps 
AssertJ -- a fluent assertion library 
Hamcrest -- a library of matcher objects 
Mockito -- a java mock framework 
JSONassert - an assertion lirbaries for json 
JSONPath -- Xpath for json 


## JUnit 4 

@Test identifies a method as a test method 
@Before executed before ach test, it is used to prepare the test environment read input, intilize the class 
@After executed after each test it is used to celanup the test environment. it can also save memory by cleanin up expensive memory structures 
@BeforeClass executed once, before the start of all tests. methods marked with this annotaiton need to be defined as static to work with junit 
@AfterClass executed once, after all tests have been finished. methods annotated with this annotation need to be defined as static to work with junit 
@Ignore marks that the test should be disabled 
@Test(expected=Exception.class) fails if the method does nto throw the named exceptions 


@RunWith(SpringRunnerclass ) run test with spring context 
@SpringBootTest search for spring boot app for configruation 
@TestConfiguration specify a spring configuration for your tset 
@MockBean inject mockito mock 
@SpyBean injects mockito spy 
@JsonTest create a joson or gson object mapper via sprign boot 
@WebMvcTest used to test web context without a full http server 
@DatJpya test used to test data layour with embedded database 


@JdbcTett like @DataJpa TEST 


@Sql specifies sql scripts to run before 




### create a junit 
in test-java-projectname-springframework - SpringXXXTests 


right click on the actual class name, Create test


```java

package com.uwindsor.receipe.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getId() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}
```



```java

package com.uwindsor.receipe.models;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private Category category;

    @Before("")
    public void setUp(){
        category = new Category();

    }

    @Test
    void getId() {
        
        Long expected = 1L;
        category.setId(expected);
        assertEquals(expected, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}
```

right click on test folder's package and run all test 


```java

package com.uwindsor.receipe.models;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private Category category = new Category();

    @Before("")
    public void setUp(){
        category = new Category();

    }

    @Test
    void getId() {

        Long expected = 1L;
        category.setId(expected);
        assertEquals(expected, category.getId());
    }

    @Test
    void getDescription() {
        category.setDescription("hello");
        assertEquals("hello", category.getDescription());
    }

    @Test
    void getRecipes() {

    }
}
```

## Mockito mocks 

let's create one Mockito test for the Service implemetnation 

create test 


```java

package com.uwindsor.receipe.Service;

import com.uwindsor.receipe.models.Recipe;
import com.uwindsor.receipe.repositories.RecipeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;
 

    @Before(value="")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);

    }

    @Test
    void getRecipe() {

        Set<Recipe> recipes = recipeService.getRecipe();
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);
        when(recipeService,getRecipe()).thenReturn(recipeData);
        recipes.add(recipe);
        assertEquals(recipes.size(),0);
    }
}
```


when you have a service layer you want to inject that service and you want to test that busines logic inside the service layer so you can write unit test 



## MockMVC 
in old days we have to bring up the entire web servie to get this tested and now we have the capability to do a mock web server and actually we are not even using a mock 



## Integration test 
click create test -> setup @Before


```java

package com.uwindsor.receipe.repositories;

import com.uwindsor.receipe.models.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringRunner.class)
@DataJpaTest
class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("cup");
        assertEquals("cup", unitOfMeasureOptional.get());
    }
}
```

## CircleCi 

it is the best practice to have a continous inegeration build server 
cirlcleCI is completely free and you can utilize it 

you can also use traviCI




### Junit 5
crowdfunding campaign and raised money include pivotal 
it leverage new features in java 8 and lambda expressions, streams desiged for integeration and extensibility 

it provides a test runner for junit 3 and junit 4 tests 

junit 4 
@Test(expected= xxx.class)
@Test(timeout=1)
@RunWith(SpringJUnit4ClassRunner.class)

Assertions.assertThrows(xx.class)




from now we do not use JUnit4 we all use JUnit 5
 @BeforeEach
 @AfterEach
 @BeforeAll
 @AfterAll
 @Disabled
 @Tag

 however, tons of legacy code using junit4 , for the foreseable future, you can expect both to be used 

 currently considering a deep dive on testing spring cover in dpeth junit4, 5, mickito , test containers, spock 

 


