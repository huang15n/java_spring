# CRUD operations with spring MVC

it is a pretty meaty topic manipulating data with dtabse and stick to happy path 

create, read, update and delete and we can expand them out 


## todo crud operation 


## display image from database 


## Enumeration and dropdown 


## webjars 


```xml

<!-- https://mvnrepository.com/artifact/org.webjars/bootstrap -->
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>5.1.3</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.webjars/jquery -->
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.6.0</version>
</dependency>



```

then maven clean, validate, verify 
With these two WebJar dependencies defined, let's now set up a simple Spring MVC project to be able to use the client-side dependencies.

Before we get to that however, it's important to understand that WebJars have nothing to do with Spring, and we're only using Spring here because it's a very quick and simple way to set up an MVC project.

Here's a good place to start to set up the Spring MVC and Spring Boot project.

And, with the simple projet set up, we'll define a some mappings for our new client dependencies


## display by ID  @RequestMapping("/xxx/xxx/{id}")
##  public String showById(@PathVariable Long id, Model model)

we are going to set a detail page for individual item



```java

package com.uwindsor.receipe.Service;

import com.uwindsor.receipe.models.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public interface RecipeService {

    Set<Recipe> getRecipe();
    Recipe findById(Long l);
}

package com.uwindsor.receipe.controllers;

import com.uwindsor.receipe.Service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }
}


```


index.html

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Recipe Home</title>
</head>
<body>

<h1> Recipes Here! 23424</h1>



<div class="table-responsive">
    <table class="table table-hover ">

        <tr class = "panel-heading">
            <td>ID</td>
            <td>Description</td>

            <td> View</td>
        </tr>
        <tr th:each="recipe : ${recipes}" class = "panel-body">
            <td th:text="${recipe.id}"> </td>
            <td th:text="${recipe.description}"> </td>
            <td> <a th:href = "@{/recipe/show/{id}(id=${recipe.id})}">View </a> </td>

        </tr>
    </table>
</div>
</body>
</html>
```


display remaining recipe properties to add hint for recipe type, iterator for categories, for ingredients, and complete remaining properties 

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Recipe Home</title>
</head>
<body>

<h1> Recipes details</h1>



<div class="table-responsive">
    <table class="table table-hover ">

        <tr class = "panel-heading">
            <td> prepare time </td>
            <td>cook time</td>
            <td>source time</td>

            <td> difficulty</td>
            <td> serving </td>
            <td> url </td>
        </tr>
        <tr th:each="recipe : ${recipe}" class = "panel-body">
            <td th:text = "${recipe.preTime}"></td>
            <td th:text = "${recipe.cookTime}"></td>


            <td th:text = "${recipe.source}"></td>
            <td th:text = "${recipe.difficulty}"></td>
            <td th:text = "${recipe.servings}"></td>
            <td th:text = "${recipe.url}"></td>




        </tr>
    </table>
</div>



<div class="well">
    <div class="panel-heading">Direction</div>
    <div class="panel-body">
        <p th:each="recipe : ${recipe}" th:text = "${recipe.getDirections()}"> </p>
    </div>
</div>



<div class="well">
    <div class="panel-heading">notes</div>
    <div class="panel-body">
        <p th:each="note : ${recipe.getNotes()}" th:text = "${note.getRecipeNotes()}"> </p>
    </div>
</div>




</body>
</html>
```


## Post 
form posts are pretty important things when we are developing a traditional web app, we post data to the backend server 
we create an object and we bind that object to pass that over and utimately the data properties will get passed over to that 

this will submit via post, upon that post, that has been around for a long time, it can be called such as a command object. let's swtich over 


let's take a lok at the whole transcations under the cover 

### data binding in spring 
command objects aka backing beans are used to transfer data to and from web forms 
spring will automatically bind data of form pots 
binding done by property name less get / set 


we will persist to the database and doing a type conversion to our any objects coming back out of the data base , doing another type conversion to our command 

follow through test driven development 

get wired to context, creating a service that is going to accept a command object, convert it, save it to the database and then return an entity 



## CRUD operations 


### what is @Synchronized???





## display image -> persist image to database and display imags from database 



static-> images 

