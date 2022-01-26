# Web Developent with Spring MVC 




first we will create a brand new MVC application - a recuoe project, we will use interesting model to work with and use that going forward and how we perist data using Spring data 


before that we are going to take a look at Http protocol, not everybody has a solid understanding of the http protocol 

developer optins 
chrome developer tools/firefore/safari
axis tcpmon 
spring boot developer tool 
sprig boot web jars 

we use that for a long time to improve productivity, really cool features 




let's step through it 

use initializer, use spring boot v2 , add devtools, web , thymealf , JPA and H2

they bring in goodness for us and continue to use thymeleaf 





## Thymeleaf 
thyme is an aromatic prerennial evergreen herb with culinary, mdeicinal and ornamental uses 
used thyme for embalming , beleived thyme was a source of courage 



thymelea is a java template engine, and it is prounced differently 

thymealeaf is a java tempalte engine producing xml, xhtml, and html5
thymeleaf is a replacement for JSP java server pages 
in a nutshell, it is a natural engine not tied to web environment can be used for producing html, thymeleaf is not a web framework 
it works very nicely in conjunction with spring mvc 
its functions are strictly to produce html and some of the differences between thymeleaf and JSP. 

themelaf tempaltes are valid html documents you can view in the brower 
jsp files are not avlaid html, and look awful in the browser 
the natural templating ability allows you to perform rapid deve, you can see in when you develop withou the need to run a container o parse template/jsp to view the product in a browser which speeds development time, you sprinkle that in 


thymealf typically does benchmark slower than other tmepalte engines such as jsp, veolicty, thymelaf did bring performance improvements, beware of old benchmark 
this completely replicate the benchmark. we declare the xml namespace,  as we progress through , explain it going forward 




### index page 

let's step through and create a template and controller 
by default spring boot will do a package scan 

```java
package com.uwindsor.receipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","index","/index.html"})
    public String index(){
        return "index";
    }
}


```


let's create the html file , thymleaf 



```html
<!DOCTYPE html xmlns="https://www.thymeleaf.com">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recipe Home</title>
</head>
<body>

<h1> Recipes Here!</h1>

</body>
</html>

```



org.thymeleaf.exceptions.TemplateInputException: An error happened during template parsing (template: "class path resource [templates/index.html]" - line 1, col 1)
	at org.thymeleaf.templateparser.markup.AbstractMarkupTemplateParser.parse(AbstractMarkupTemplateParser.java:239) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.templateparser.markup.AbstractMarkupTemplateParser.parseStandalone(AbstractMarkupTemplateParser.java:100) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.engine.TemplateManager.parseAndProcess(TemplateManager.java:666) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.TemplateEngine.process(TemplateEngine.java:1098) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.TemplateEngine.process(TemplateEngine.java:1072) ~[thymeleaf-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.spring5.view.ThymeleafView.renderFragment(ThymeleafView.java:366) ~[thymeleaf-spring5-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.thymeleaf.spring5.view.ThymeleafView.render(ThymeleafView.java:190) ~[thymeleaf-spring5-3.0.14.RELEASE.jar:3.0.14.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.render(DispatcherServlet.java:1401) ~[spring-webmvc-5.3.14.jar:5.3.14]
	at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1145) ~[spring-webmvc-5.3


this makes the error 
```html
<!DOCTYPE html  ">
<html lang="en" xmlns:th="http://www.thymeleaf.org>
<head>
    <meta charset="UTF-8">
    <title>Recipe Home</title>
</head>
<body>

<h1> Recipes Here!</h1>

</body>
</html>
```



thymleaf wants everything to be properly escape 

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>Recipe Home</title>
</head>
<body>

<h1> Recipes Here!</h1>

</body>
</html>
```

come over to the milestone 

there is a special repository for milestones and the plugin 

maven will be unhappy about those unmatched versions 



## HTTP - hypertext transfer protocol

started as a telnet friendly protocol, it is frowned upon to use. ssh has definitely shut telnet to the side. telnet sends out everything you type free text across the network wire

a new segment of standards jointly work on this, come up with detailed specs as how the web server had to behave, it is a joint option 

200 is ok and header response solved a lot of ambiguities from earlier version added supprts for keep alive conenctions, chunked encoding transfer, http1.1 is still in use today 

request and response added encoding, charset, and cookies 
http2.2 standardized in 2015 as of 2017 make it compatible with 1.0 , transport performance was a focus, lower latency and higher throughput, marginally the same are largely transparent. we have go to this point. 

we go across the wire, http request methods, go forward as we do traditional methods 


## request method  request methods, also known as verbs are used to indciate the desired action to be perform. GET- is a request for a resource -- start off html, javascript, file, image, etc 
GET - is used when you visit a website 
HEAD - is like GET but only asks for meta info without the body, you wont get HTML file 

## Post method  post is used to post data to the server  typical use case for POST is to post from data to the server , like a checkout form 
PUT - is a request for the enclosed entity to be sored at the supllied URI. if the entity exist, it is expected to be updated 
POST is a create request 
PUT is a create or update request 


##  Delete is a request to delete the specified resource 

## TRACE will echo the received request, can be used to see if request was altered by intermedaite serers make sure that server is taking and handing yoru request off to the destiona servers 



## OPTIONS == returns the HTTP methods supported by the server for the specified URL 


## CONNECT -- converts the request to a transparent TCP/IP tunne, typically for HTTPS through an unencrypted HTTP proxy 

## PATCH -- aplied partial modifications to the specfied resources 

## Safe methods are considred safe to use because they only fetch info and do not cause changse on the server.. the safe methods are GET, HEAD, OPTIONS, and TRACE 
That is the most dynamic way 

### idemptent methods 
idempotence -- a quality of an action such that repetitions of the action have no fruther effect on the outcome 
PUT and DELETE are idempotent methods 
safe mtehods GET, HEAD, TRACE, OPTIONS are also idempotent 
being truly idempotent is not enforced by the protocol 


coders can write things badly and cause unexpected actons so that is what is expected to happen 

POST is not idempotent, multiple posts are likely to create multiple resources, ever seen webites asking you to click submit only once?


GET methos has no request body, but with a response body, it is safe, and idempotent, cachable 

HEAD has no request body, no response body, it is safe, idempotent, cachable 

POST has request and response body, but not safe or idempotent, it is cachable 

PUT has request and response body, not safe, it is idemptoent but not cahcable 
DELET has no requets boyd but it has response body, it is not safe , it is idempotent , it is ont cachable 




## HTTP status code 
100 series are informatonal in nature 
200 series indicate sucessful request 
300 series are redirections 
400 series are client error 
500 series are server side errors 

## Common http status code 
200 ok, 201 created, 204 accepted 
301 moved permanetly 
400 bad request, 401 not authorized, 404 not found 
500 internal server error; 503 unavaible 

the full suite of http status code 




## developer tools 

to see what is actually rendered on the browser 


the main takeaway is I am going to leave this in and pay special attention to is the firefox goes away 

it is emitting the messages and useful things  
it is grab that traffic, passin it over to that and then taking the response 

it is going across the wire back to the browser 



## Spring boot developer tools 
this is a huge addition to the spring tool set 

it is huge productively save 

added to proejct vai artificat spring-boot-detools, developer tools are automatically disabled when running a packaged app ie java =jar 
by default, not included in repackaged archives 
it refrehses with your package 

it has an autoamtic rstart, trigger a request of the spring context when calses chagnes, it uses two classloaders, one for your app, one for project jar dependency, restarts are very fast, since only your proejct classes are bring laoded 


intellij by default you eed to select build and make project, there is an dvanced settubg 



#### tempalte caching 
by dfeault templates are cached for performance 
but caching will require a container restart to refresh the cache 
developer tools will dsiable tempalte caching so the restart is not requred to see changes 


### liverolad
LiveReload is a technology to automatically trigger a browser refresh when resoruces are changed, spring boot dev tools include a LiveReload server, browser plugins are available for a free download at this 

it gives you quick reloade here 

you do not have to restart yoru server each time, only Build 

#### Intellij compiler configruation for spring boot development 
and build and save it automatically rebuidling the project and update the change , reflect immediately 


ctrl + shif + a , type in Registry - > compiler, build project automatically 


I forward a head a little bit, it is a little bit flakey, it might not come through right away





























