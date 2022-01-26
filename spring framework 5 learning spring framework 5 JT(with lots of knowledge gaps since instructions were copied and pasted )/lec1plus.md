# Let's create a Joke app using Spring 


spring viit actuator 


we are still laying the found work. we have some basic tasks we need to do that helps a lot to reinfornce on hands-on coding 


create a spring boot app to display quotes, inspired by plugins for jenkins, do one for spring boot actuator
open source, available in maven central
app will be a spring MVC app themeleaf 
create service to obtain quotes, use controlelrs to pass quotes to thymeleaf 


create a service a little bit challenge, 

set up, 
1. use spring initializer to create spring boot project 
uses java 11, maven 
slect only spring web and themeleaf dependencies 
generated files to workspace 

add dpendency actuator 
2 create a service to return joke string 
3 create joke controller with one method mapped to context root 
5. to the method being return, add a promot with value of quotes from service 
6. return view name of index 
7. create thymealf tempalte for view index 
8. updateview to display joke string from model 



to reiterate we use spring dependency injection to inject in and go ahead in service into the controller that way 


download from spring intializer 
inject this dependency 
```xml
 <dependency>
            <groupId>guru.springframework</groupId>
            <artifactId>chuck-norris-for-actuator</artifactId>
            <version>2.4.0</version>
        </dependency>
```

not a rocket science just pretty simple 

create a package named service under java.packagename 

create a service interface 

```java

package com.uwindsor.joke.service;

public interface JokeService {
    String getJoke();
}


```

implement interface 

```java
package com.uwindsor.joke.service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;


@Service
public class JokeServiceImpl implements JokeService {
    private final ChuckNorrisQuotes chuckNorrisQuotes;

    public JokeServiceImpl() {
        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    @Override
    public String getJoke() {
        return chuckNorrisQuotes.getRandomQuote();
    }
}


```
wait a second , this is not friendly for DI, if you were little further along as far as utilizing spring configuration, this gets wired up using java constructor  




to recap here, we add an interface, a class implements this interface which has dependency injected 

if we have a single contrustor in the class, spring will automatically do dependency injection for us 

the model's efffectively a map implementation, so we are adding in a property with the string 

```html
<html xmlns:th="http://www.thymeleaf.org">
```

```java

package com.uwindsor.joke.controller;

import com.uwindsor.joke.service.JokeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @RequestMapping({"/", ""})
    public String sayAjoke(Model model){
        model.addAttribute("joke",jokeService.getJoke());

        return "index";
    }



}

```



org.thymeleaf.exceptions.TemplateProcessingException: Could not parse as expression: "${joke}}" (template: "index" - line 20, col 4)
	at org.thymeleaf.standard.expression.StandardExpressionParser.parseExpression(StandardExpressionParser.java:131) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.standard.expression.StandardExpressionParser.parseExpression(StandardExpressionParser.java:62) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.standard.expression.StandardExpressionParser.parseExpression(StandardExpressionParser.java:44) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.engine.EngineEventUtils.parseAttributeExpression(EngineEventUtils.java:220) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.engine.EngineEventUtils.computeAttributeExpression(EngineEventUtils.java:207) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]

    if you see this error , you must be donig things wrong with thymeleaf 
trace this in java error log 
in my example 
```html
<p th:text="${joke}}"> </p>


```

    if you enter the wrong attribute, your html file will show nothing 

```html

<p th:name="${number}"> this is the number </p>

```

it must be th:text 




```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>


<h1> number </h1>

<p th:name="${number}"> this is the number </p>

</body>
</html>



```


```java

package com.uwindsor.joke.controller;

import com.uwindsor.joke.service.NumberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RandomNumberController {
    private final NumberService numberService;

    public RandomNumberController(NumberService numberService) {
        this.numberService = numberService;
    }


    @RequestMapping("/number")
    public String showNumber(Model model){

        System.out.println("hey, the number is here " + numberService.getNumber());

        model.addAttribute("number",numberService.getNumber());
        return "number";
    }



}

```


```java
package com.uwindsor.joke.service;

import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class RandomNumber {

  // you do not need a constructor here

    public String getRandomNumber(){
        StringBuilder bd = new StringBuilder();
        Random rand = new Random();
        return  bd.append(rand.nextInt(1000000)).toString();
    }
}

package com.uwindsor.joke.service;

public interface NumberService {
    String getNumber();
}



package com.uwindsor.joke.service;


import org.springframework.stereotype.Service;

@Service
public class NumberServiceImpl implements NumberService {


    private RandomNumber randomNumber;

    public NumberServiceImpl(RandomNumber randomNumber) {
        this.randomNumber = randomNumber;
    }

    @Override
    public String getNumber() {
        return randomNumber.getRandomNumber();
    }
}


```


let's create a custom banner for spring boot 
```


  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
```

#### just a little nice customization that we can do for our spring : patorjk.com



create a banner.txt under the resource folder 

grab a text from here and jump back to intellij and other resources 

