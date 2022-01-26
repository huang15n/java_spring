

# JPA modeling with spring and hibernate 



towards the end of the module, we are gonna set up spring data JPA repository and database initialization 


we are going to look at displaying a alist of things on the index page , load up data and set this up 




## JPA Entity Relationships 



### Types of relatinoships 

1. one to one -- @OneToOne -- one entity is related to one other entity 
it is going to be like an embeded type and a type,


2. one to many -- @OneToMany -- one entity is related to many entities List, Set, Map, SortedSet, SortedMap


3. many to one -- @ManyToOne -- the inverse relationship of One to many 


3. many to many - @ManyToMany
- many entites are related to many entites 
- each has a List or Set reference to the other 
- a joint table is used to define the relationship 

so you could have a category that would be covering multiple products 



4. undirectinal is one way 
- mapping is only done one way. one side of the relationship will not know about the other 


5. bidrectinonal is two way 
- both sides know about each other 
- generally recommended to use bidirectional, since you can navigate the object graphy in either direction 

performance wise, there is no difference 




## owning side
- the owning side in the relationship will hold the foreign key in the database 

- @OneToOne is the side where the foreign key is specified 
- @OneToMany and @ManyToOne is many side 
- 'mappedBy' ised to define the field with owns the relationship 




## Fetch type 
- lazy fetch type - data is not queried until referenced 
- eager fetch type -- data is queried up front 
- hibernate 5 supports the JPA 2.1 fetch type defaults 
- JPA 2.1 fetch type defaults 
1. @OneToMany lazy 
2. @ManyToOne - eager 
3. @ManyToMany - lazy 
4. @OneToOne - eager 



## JPA cascade types 
- JPA cascade types control how state changs are cascaded from parent objects to child objects 

- JPA cascade types : 
1. persist - save operations will cascade to related entities 
2. merge - related entities are merged when the owning entity is merged 
3. refresh - related entities are refreshed when the owning entity is refreshed 

4. remove - removes all related entities when the owning entity is deleted 
5. deetach - detaches all related entiteis if a manual deach occurs 
6. all - applies all the above cascade options 

by default, no operations are cascaded 



## Embeddable types 
1. jpa / hibernate supprot embededable types 
2. these are used to define a common set of properties 
for example, an order might have a billing address, and a shipping address. 
have an address type so we do not have duplicate properties, an embeddable type could be used for the address properties 

an embeddable type culd be used for the address properties 



## Inheritance
hibernate supports inheritance 

1. MappedSuperclass - entities inherit from a super class. a dtabase table is NOT created for the super class 
2. single table -- hibernate defulat -- one table is used for all subclasses 
3. joined table - base class and subclasses have their own tables. fetching subclass entities require a join to the parent table 
4. table per class. each subclass has its own table 


important distinction there, all subclass will share the same type, the base class will have its own table. you are really repeating class for each table. upside and downsides . 


## Create and update timestamps
1. often a best pratice to use create and update timestamps on your entities for audit purposes 
2. JPA upports @PrePersist and @PreUpdate which can be used to support audit timestamps via JPA lifecyle callbacks 
3. Hibernate provides @CreationTimestamps and @UpdateTimestamp 

you can tap into to updateyour timestamps around PrePersist for the create and PreUpdate, keep in mind that is hibernate specific 




### data model - JHipster and JDL


Recipe 
prepTime :int
cookTime :int
serving : int
source : string 
url; string 
direction : String 
difficulty : Diffiulcty 
image : byte 



notes: 
notes: string 

difficulity:
easy/moderate/hard: enumeration 


ingredient: 
description: string 
amount: bigDecimal 

unitOfMeasure: 
uom: String 

category: epartementName: String 




## Fork in github 

it will get the copy of the source code over to your repo 
that builds up some history in your repo and it something you can share with potential/perspective employers 

they are not impacting the flow of your work 



## One to One JPA relationships 

toggle over to intellij now and refresh your memory 



```java
package com.uwindsor.receipe.models;

public class Recipe {
    private String description;
    private Integer preTime;
    private Integer cookTime;
    private  Integer servings;
    private String source;
    private String url;
    private String directions;


    private Byte [] images;
    private Notes notes;

}


package com.uwindsor.receipe.models;

public class Notes {
    private Recipe recipe;
    private String recipeNotes;

}



```

the byte array for image, the different part is enumeration for difficulties 

add getter and setter, @OneToOne relationship and cascade = CascadeType.all so we want to add the whole class as an @Entity
there is a little leakage here as the model 
we need the Id and @GenerateValue(stratehy = GenerationType.IDENTITY) this is going to leverage the underlying persistence framework to generate an ID value for us. this GenerationType.IDENTITY will support the automatic geenration of a sequence 
it has an autogenerated primary key property that we can use as well as several other database too. hibernate has not caught up to that  


```java
package com.uwindsor.receipe.models;

import javax.persistence.*;


@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer preTime;
    private Integer cookTime;
    private  Integer servings;
    private String source;
    private String url;
    private String directions;


    @Lob
    private Byte [] images;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreTime() {
        return preTime;
    }

    public void setPreTime(Integer preTime) {
        this.preTime = preTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImages() {
        return images;
    }

    public void setImages(Byte[] images) {
        this.images = images;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
}



```


you could use an integer but it can run out surprisingly quick on a busy databse 

in the notes, we do not have to specify cascade because this instance we are going to allow the recipe to own this. 
we do not want to go back and delete recipe object 

now there is a special use case that we want to look at and we have two of them there 

in relational database you can use clobs for character large objects or BLOBs for binary large objects . in this case by default a string in java can be crazily large over the limitation of it off the top of my head is a bit larger than Hibernate JPA 

```java

package com.uwindsor.receipe.models;

import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}

```

## @OneToMany RELATIONSHIP 

recipe object @OneToMany to ingredients 


```java

package com.uwindsor.receipe.models;

import java.math.BigDecimal;

public class Ingredient {
    private String description;
    private BigDecimal amount;
}

```


step through creating it as an entity 
the ingredient is going to be a property called recipe.
```java
package com.uwindsor.receipe.models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer preTime;
    private Integer cookTime;
    private  Integer servings;
    private String source;
    private String url;
    private String directions;


    @Lob
    private Byte [] images;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreTime() {
        return preTime;
    }

    public void setPreTime(Integer preTime) {
        this.preTime = preTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImages() {
        return images;
    }

    public void setImages(Byte[] images) {
        this.images = images;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
}


```


 jump back over here to , we do not want any cascade here to to cascade up and delete the recipe not the other way around . 


```java

package com.uwindsor.receipe.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;


    @ManyToOne
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}

```


a recipe can have many ingredients and one ingredient can have just one recipe


### @OneToOne unidirectional relationship 
create unit of measurement is a reference table to hold different properties and be independent to look up properties 


DO NOT cascde persistence events from ingredient to unit of measure, that will be undesired behaviour of our app 





```java
package com.uwindsor.receipe.models;

import javax.persistence.*;
import java.math.BigDecimal;

public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    
    
    @OneToOne(fetch =  FetchType.EAGER)
    private UnitOfMeasure uom;
    


    @ManyToOne
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }
}

```



```java

package com.uwindsor.receipe.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

```

#### fetch= FetchType.EAGER might not be as apparent as default as code coming behind yo, that could be handy for them to triger memory 


## JPA enumeration 



create a Enum called Difficulty 

in Recipe class add Difficulty and getter and setter that is just the personal style to add whatever existing and then do the same with the getters and setters as ypu evolve pojos keep the code cleanest 


```java
package com.uwindsor.receipe.models;

public enum Difficulty {
    EASY, MODERATE, HARD
}


```
now we use string values and enum persists as string and this is important because you can come in and change this 

if you change a value on the fly let's say you had an existing database. now anything you had in there, and the hard value just changed. now every existing data that we have is messed up and it is gong to need to be addressed 





###     @Enumerated(value = EnumType.ORDINAL)

when you add EnumType.ORDINAL is the default and it defines how enum will persist in the database 

### @Enumerated(value = EnumType.STRING)
now this is overriding the default behaviour of enumerations .


```java

package com.uwindsor.receipe.models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer preTime;
    private Integer cookTime;
    private  Integer servings;
    private String source;
    private String url;
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;


    @Lob
    private Byte [] images;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreTime() {
        return preTime;
    }

    public void setPreTime(Integer preTime) {
        this.preTime = preTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImages() {
        return images;
    }

    public void setImages(Byte[] images) {
        this.images = images;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}

```


## @ManyToMany relationship 

set up a bidirectional relationship betweeen recipe and categories and we move up towards the top there 


```java

package com.uwindsor.receipe.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany
    private Set<Recipe> recipeSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Recipe> getRecipeSet() {
        return recipeSet;
    }

    public void setRecipeSet(Set<Recipe> recipeSet) {
        this.recipeSet = recipeSet;
    }
}

```



```java


```



```java

package com.uwindsor.receipe.models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer preTime;
    private Integer cookTime;
    private  Integer servings;
    private String source;
    private String url;
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;


    @Lob
    private Byte [] images;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    @ManyToMany
    private Set <Category> category;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreTime() {
        return preTime;
    }

    public void setPreTime(Integer preTime) {
        this.preTime = preTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImages() {
        return images;
    }

    public void setImages(Byte[] images) {
        this.images = images;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }
}

```





### Error 
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.uwindsor.receipe.models.Recipe.category[com.uwindsor.receipe.models.Category]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1804) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:620) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:542) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154) ~[spring-context-5.3.14.jar:5.3.14]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:908) ~[spring-context-5.3.14.jar:5.3.14]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583) ~[spring-context-5.3.14.jar:5.3.14]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:145) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:730) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:412) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:302) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1301) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1290) ~[spring-boot-2.6.2.jar:2.6.2]
	at com.uwindsor.receipe.ReceipeApplication.main(ReceipeApplication.java:12) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.6.2.jar:2.6.2]
Caused by: org.hibernate.AnnotationException: Use of @OneToMany or @ManyToMany targeting an unmapped class: com.uwindsor.receipe.models.Recipe.category[com.uwindsor.receipe.models.Category]
	at org.hibernate.cfg.annotations.CollectionBinder.bindManyToManySecondPass(CollectionBinder.java:1351) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.cfg.annotations.CollectionBinder.bindStarToManySecondPass(CollectionBinder.java:874) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.cfg.annotations.CollectionBinder$1.secondPass(CollectionBinder.java:799) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.cfg.CollectionSecondPass.doSecondPass(CollectionSecondPass.java:53) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.processSecondPasses(InFlightMetadataCollectorImpl.java:1653) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.processSecondPasses(InFlightMetadataCollectorImpl.java:1629) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:295) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1460) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1494) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:58) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:341) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1863) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1800) ~[spring-beans-5.3.14.jar:5.3.14]
	... 21 common frames omitted


Process finished with exit code 0



## we forgot @Entity tags on other classes 
## never add @Entity tag on enum classes 



start the spring boot app:
## error: Database "mem:testdb" not found, either pre-create it or allow remote database creation (not recommended in secure environments) [90149-200] 90149/90149 (Help)


### add this to your application.properties file spring.datasource.url=jdbc:h2:mem:testdb


you will see a_b and b_a two tables 


## @JoinTable(name="a_b")
I want to add jpa mapping and specify join columns 

## @JoinTable(name="a_b", JoinColumns = @JoinColumn(name  = "a_id"), inverseJoinColumns = @JoinColumn(name = name = "b_id"))


the TABLENAMES are the default naming convention that hibernate use to generate tables for us 


```java

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn( name = "recipe_id"), inverseJoinColumns = @JoinColumn( name = "category_id"))
    private Set <Category> categories;
```

I did not do the other side 

## @ManyToMany(mappedBy = "name")



## error if you mappedBy = "different name"

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is org.hibernate.AnnotationException: mappedBy reference an unknown target entity property: com.uwindsor.receipe.models.Recipe.category in com.uwindsor.receipe.models.Category.recipes
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1804) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:620) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:542) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1154) ~[spring-context-5.3.14.jar:5.3.14]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:908) ~[spring-context-5.3.14.jar:5.3.14]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:583) ~[spring-context-5.3.14.jar:5.3.14]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:145) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:730) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:412) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:302) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1301) ~[spring-boot-2.6.2.jar:2.6.2]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1290) ~[spring-boot-2.6.2.jar:2.6.2]
	at com.uwindsor.receipe.ReceipeApplication.main(ReceipeApplication.java:12) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.6.2.jar:2.6.2]
Caused by: org.hibernate.AnnotationException: mappedBy reference an unknown target entity property: com.uwindsor.receipe.models.Recipe.category in com.uwindsor.receipe.models.Category.recipes
	at org.hibernate.cfg.annotations.CollectionBinder.bindStarToManySecondPass(CollectionBinder.java:848) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.cfg.annotations.CollectionBinder$1.secondPass(CollectionBinder.java:799) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.cfg.CollectionSecondPass.doSecondPass(CollectionSecondPass.java:53) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.processSecondPasses(InFlightMetadataCollectorImpl.java:1653) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.boot.internal.InFlightMetadataCollectorImpl.processSecondPasses(InFlightMetadataCollectorImpl.java:1629) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.boot.model.process.spi.MetadataBuildingProcess.complete(MetadataBuildingProcess.java:295) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.metadata(EntityManagerFactoryBuilderImpl.java:1460) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:1494) ~[hibernate-core-5.6.3.Final.jar:5.6.3.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:58) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:409) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:396) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:341) ~[spring-orm-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1863) ~[spring-beans-5.3.14.jar:5.3.14]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1800) ~[spring-beans-5.3.14.jar:5.3.14]
	... 21 common frames omitted


Process finished with exit code 0


```java

  @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn( name = "recipe_id"), inverseJoinColumns = @JoinColumn( name = "category_id"))
    private Set <Category> category;



        @ManyToMany(mappedBy = "category")
    private Set<Recipe> recipes;

```


these have to be the same property name 

these have to related to sql that is getting generated table under covers 
I need to go out to refresher because honestly you do not see that many to many relationships when you working as a string developer and get it setup properly 




## create spring data repository 


we set up ingreidents to cascade recipes so all operations between recipe so if we do any updates on recipes and anything on the child objects of notes and ingredients of recipes, those will get persisted down through the JPA mapping 

we probably won't work with ingredients independently or notes indepenently. so I do not see a use case there in our little app 

we just need to set up spring repositorires for recipe, category and unit of measure. 

create a package called repositories

let's start off with an interface called RecipeRepository

it exntes CrudRepository<T,ID> T is the entity type and ID is going to be the ID type of that entity, this way spring will use reflection and generic 


```java

package com.uwindsor.receipe.repositories;

import com.uwindsor.receipe.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}

package com.uwindsor.receipe.repositories;

import com.uwindsor.receipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}


package com.uwindsor.receipe.repositories;

import com.uwindsor.receipe.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}


```

we are dependin on java's pacakges 

that is very common use case or a convention as used it would use spring data 

spring will bring this up because we do not have anything demanding a need for these CRUD repositorie now they have been created as spring beans in the context and if we had naything that needed them, it would have got injected in there so we will set up a service layer coming up 

we will use data loading using spring boot. 



## Spring data source initialization 

we are workingwith hibernate towards traditional sql data and this works with any traditional database we covered 

we need to populate database tables with some initial data. so there is a number of different ways we can do it and there are also some pitfalls, pretty complete toolsets, we have data loaded in the database 




## Hibernate DDL auto

DDL = data defintion language 
DML = data manipulation language , this is the sql commands you do insert, update and delete 

hibernate property is set by the spring property spring.jap.hibernate.ddl-auto 
options are : none, validate, update, create, create-drop 
spring boot will use create drop for embedded database hsql, h2, derby or none 

DDL is the hiberante property and hibernate specific thing, it is not specific to JPA. what it allows for its the database graded from your JPA entity classes and we have seen that in action. we have several otpions to work with. it does nto verify the database structure that will fail the startup 
so if hiberate is going to expect a table is missing or a column in that table is missing, hibernate prefer not to get surprised if there is a database problem compared to JPA 

now update will automatically update an exsiting database schema , i say use this one with caution if your JPA model changs or if there is an error in it, hibernate will run DDL statements to update the database and if a=you are on produciton system, this is probabbly not a behaviour you want 
it is limignt sutff to dml if you are running production system 

that is a pretty basic thing to prevent malicious activity against your databsae using the crential based app. 



there is create and drop when you shut down the database will get dropped 
all the databsae schema objects will get dropped and contrasted if you just have creted and you are runnign against my sql and you go back and look and the database will still be there, where with create drop it will be gone 


spring boot by default, using the embeded h2 database and hsql h2 and berby spring will use drop option if you are not using an embedded databse it will ue none, which is the default behaviour of spring boot and this is what has been creating the database for us. 


## initialize with hibernate 
data can be loaded from import.sql 
hibernate feature not spring specific , must be on root class path!!! only executed if hierbate dll auto property is set to create or create drop and it will automatically do this and that sql script can cotnain a combination of DDL 



## spring JDBC 
spring's datasource intializer via spring boot will by default laod schema.sql and data.sql from the root of the class path 
spring boot will also load from scheme-@{platform}.sql and data-${platform},sql 
must set spring.datasource.platform 
may conflict with hibernate DDL auto property, should use setting of none or validate 



create a data.sql under resources folder 

```sql
INSERT INTO category(description) VALUES ('America');
INSERT INTO category(description) VALUES ('China');
INSERT INTO category(description) VALUES ('Amer');
INSERT INTO category(description) VALUES ('Canada');
INSERT INTO category(description) VALUES ('French');
INSERT INTO unit_of_measure(description) VALUES ('teaspoonful');
INSERT INTO unit_of_measure(description) VALUES ('spoonful');
INSERT INTO unit_of_measure(description) VALUES ('cup');
INSERT INTO unit_of_measure(description) VALUES ('pinch');
INSERT INTO unit_of_measure(description) VALUES ('tablespoon');


```

this file will be eligible for being picked up by spring boot 

it does not take much time at all to load up these data 



## error org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "UNIT_OF_MEASURE" not found; SQL statement:


server.port=8081
spring.datasource.url=jdbc:h2:mem:testdb
#### spring.jpa.defer-datasource-initialization=true



generally sql is case sentitive for column and table names 

you will go from in memory database to a permanent databse server and like in your QA system or your production system you will be locked out 



### spring data JPA query methods 


we will find by names or columns to find location and pass in those values and what is going to happen underneath the cover is that spring data jpa is going to go in and create that query based on that criteria that was found inside the method name 

it saves us a ton of work and it is very verstaile 
it will adapt a lot of different situations, now all we need to do is go to the repositories and define a method in the interface for the unit measure and category and you get hold of that record easily for other purposes 

realize we are creating method name for the interface and then spring data jpa provides us the implementaiton that we need to fetch the data. this is why spring is so power ful and set up dynamic finders 

we want to get hold of these objects inside the database 

```java
package com.uwindsor.receipe.repositories;

import com.uwindsor.receipe.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);

}

package com.uwindsor.receipe.repositories;

import com.uwindsor.receipe.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    
    Optional<UnitOfMeasure> findByDescription(String description);
}


```
Optional is java 8 feature 




```java

package com.uwindsor.receipe.controllers;

import com.uwindsor.receipe.models.Category;
import com.uwindsor.receipe.models.UnitOfMeasure;
import com.uwindsor.receipe.repositories.CategoryRepository;
import com.uwindsor.receipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String index(){

        Optional<Category> categoryOptional = categoryRepository.findByDescription("China");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("spoonful");

        System.out.println("id for category " + categoryOptional.get().getId());
        System.out.println("id for unit of measure" + unitOfMeasureOptional.get().getId());
        return "index";
    }
}

```



when I go to the index page , we will see that output to the console for us 

we have seen the query in action 




### Option means a container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.
Additional methods that depend on the presence or absence of a contained value are provided, such as orElse() (return a default value if value not present) and ifPresent() (execute a block of code if the value is present).

This is a value-based class; use of identity-sensitive operations (including reference equality (==), identity hash code, or synchronization) on instances of Optional may have unpredictable results and should be avoided.


let's display list of recipes add any needed unit of measures to data.sql 
use bootstrap class to crete recipes on startup 
create service to return recipe list to controll 
pass list to thymeleaf view to display on index page 


```java
package com.uwindsor.receipe.models;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    @OneToOne(fetch =  FetchType.EAGER)
    private UnitOfMeasure uom;



    @ManyToOne
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }
}

package com.uwindsor.receipe.models;

import javax.persistence.*;
import java.util.Set;

package com.uwindsor.receipe.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer preTime;
    private Integer cookTime;
    private  Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;


    @Lob
    private Byte [] images;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;




    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn( name = "recipe_id"), inverseJoinColumns = @JoinColumn( name = "category_id"))
    private Set <Category> category = new HashSet<>();

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPreTime() {
        return preTime;
    }

    public void setPreTime(Integer preTime) {
        this.preTime = preTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImages() {
        return images;
    }

    public void setImages(Byte[] images) {
        this.images = images;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


}


```
 

```java
package com.uwindsor.receipe.BootStrap;

import com.uwindsor.receipe.models.*;
import com.uwindsor.receipe.repositories.CategoryRepository;
import com.uwindsor.receipe.repositories.RecipeRepository;
import com.uwindsor.receipe.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ReceipeBootstrap  implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public ReceipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        recipeRepository.saveAll(getRecipe());

    }

    private List<Recipe> getRecipe(){

        List<Recipe> recipes = new ArrayList<>();


        Optional<UnitOfMeasure> tableSpoonOptional = unitOfMeasureRepository.findByDescription("tablespoon");
        if(!tableSpoonOptional.isPresent()){
            throw new RuntimeException("expected table soon not found");
        }

        Optional <UnitOfMeasure> spoonfulOptional = unitOfMeasureRepository.findByDescription("spoonful");
        if(!spoonfulOptional.isPresent()){
            throw new RuntimeException("expected spoonful not found");
        }
        Optional <UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("cup");
        if(!cupOptional.isPresent()){
            throw new RuntimeException("expected cupOptional not found");
        }

        Optional <UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("pinch");
        if(!pinchOptional.isPresent()){
            throw new RuntimeException("expected pinch not found");
        }


        Optional <UnitOfMeasure> teaspoonfulOptional = unitOfMeasureRepository.findByDescription("teaspoonful");
        if(!teaspoonfulOptional.isPresent()){
            throw new RuntimeException("expected teaspoonful not found");
        }

        UnitOfMeasure tablepoonUOM = tableSpoonOptional.get();
        UnitOfMeasure teaspoonUOM = teaspoonfulOptional.get();
        UnitOfMeasure cupUOM = cupOptional.get();
        UnitOfMeasure pinchUOM = pinchOptional.get();
        UnitOfMeasure spoonfulUOM = spoonfulOptional.get();

        Optional<Category> americacategoryOptional = categoryRepository.findByDescription("America");
        Optional<Category> germangoryOptional = categoryRepository.findByDescription("German");

        Optional<Category> chinaOptional = categoryRepository.findByDescription("China");

        Optional<Category> frenchOptional = categoryRepository.findByDescription("French");


        Category chinaCategory = chinaOptional.get();
        Category germanCategory = germangoryOptional.get();
        Category americaCategory = americacategoryOptional.get();
        Category frenchCategory = frenchOptional.get();


        Recipe oneRecipe = new Recipe();
        oneRecipe.setDescription("this is one");
        oneRecipe.setPreTime(10);
        oneRecipe.setCookTime(20);
        oneRecipe.setDifficulty(Difficulty.HARD);
        oneRecipe.setDirections("this is direction one");


        Notes oneNote = new Notes();
        oneNote.setRecipeNotes("this is note one");

        oneRecipe.setNotes(oneNote);



        oneRecipe.getIngredients().add(new Ingredient("ingredient 2", new BigDecimal(23), teaspoonUOM, oneRecipe));
        oneRecipe.getIngredients().add(new Ingredient("ingredient 3", new BigDecimal(33), cupUOM, oneRecipe));
        oneRecipe.getIngredients().add(new Ingredient("ingredient 4", new BigDecimal(53), spoonfulUOM, oneRecipe));

    oneRecipe.getCategory().add(chinaCategory);
    oneRecipe.getCategory().add(americaCategory);
        oneRecipe.getCategory().add(frenchCategory);

    recipes.add(oneRecipe);


    return recipes;







    }
}


```


that is a pretty length object graph here 


there is a lot going one 



###  implements ApplicationListener<ContextRefreshedEvent> 
that will save everything in the repository 

```java

package com.uwindsor.receipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.uwindsor.receipe.controllers","com.uwindsor.receipe.BootStrap"})
@SpringBootApplication
public class ReceipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceipeApplication.class, args);
	}

}

```

now create a interface RecipeService.java


```java

package com.uwindsor.receipe.Service;

import com.uwindsor.receipe.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipe();
}


package com.uwindsor.receipe.Service;

import com.uwindsor.receipe.models.Recipe;
import com.uwindsor.receipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipe() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}


```


controller 


```java

package com.uwindsor.receipe.controllers;

import com.uwindsor.receipe.Service.RecipeService;
import com.uwindsor.receipe.models.Category;
import com.uwindsor.receipe.models.UnitOfMeasure;
import com.uwindsor.receipe.repositories.CategoryRepository;
import com.uwindsor.receipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private  final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String index(Model model){
        model.addAttribute("recipes", recipeService.getRecipe());
       return "index";
    }
}

```

fastfoward to html 

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>Recipe Home</title>
</head>
<body>

<h1> Recipes Here! 23424</h1>


<table>

    <tr>
        <td>ID</td>
        <td>Description</td>
    </tr>
<tr th:each="recipe : ${recipes}">
    <td th:text="${recipe.id}"> </td>
    <td th:text="${recipe.description}"> </td>

</tr>

</table>

</body>
</html>
```

```java

package com.uwindsor.receipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.uwindsor.receipe.controllers","com.uwindsor.receipe.BootStrap","com.uwindsor.receipe.Service"})
@SpringBootApplication
public class ReceipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceipeApplication.class, args);
	}

}

```


## java coding refresher         recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);





## using setter for jpa bidirectional relationship 

we want to have one spot in the code that manages that relationship rahter than having it in multiple spots because when you hae in multiple spots. now furthre down to set up JPA constraints 

there is no much way around this 


add some logic to the setter here , get the code into one spot and make it a lot lighter quite lengthy and get ingredients we set and we pass in associated with and provide a helper methods to add relationship for us 

we do not have to do it out side the class 

```java


    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return  this;
    }

```

so we do not have multiple chaining of method calls 

we have these methods fleshed out a little bit on our entities 

