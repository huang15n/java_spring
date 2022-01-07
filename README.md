# Java Spring and Spring Boot Application Development 



## Preface 
I will convey as much as I have to you. Going forward, you need to complete and follow along with examples, leverage github for branches, compares and forks, collaborate with other gurus. It is a great way to proceed in this and really get you over any little speed bumps. It does not stumble me up but it is going to trip you up to become a true expert/ guru on the spring framework. We will roll a lot of transcational processing and things into it that have scale. make yourself actively involved in the spring community, emerging practices. 

Some opinionated design, a loosely concept in the industry. spring boot brings a number of opinionated default configruations. thus using spring boot is following an opinionated design. others are rigid. You can skip ahead if you have a solid foundation. Do not take offence. 


Spring framework itself is considered to be opinionated because what it is doing is brining in a lot of configurations in the typical fanshion. 

Best practice of a design including:
1. OOP/ SOLID
2. TDD
3. Naming Convetions 
4. Software development life cycles 


Github: 
1. try to emulate a real world software project
2. use github, master branch
3. follow mainline dev model
4. step by step
5. use github issues to plan work and tasks 




the full project should consist of the following: 
1. JavaBeans based app configuration IoC 
2. Model View Controller presentation
3. Praticial database access through JDBC, java persistent API, JAP or Spring Data JPA
4. Application monitoring based on JMX
5. Declarative Transaction Management using AOP
6. data vadlidation that supports but is not dependent on the presentation layer 


JSP is still very popular out there, one of the thing is Thymeleaf. 

Views -- JSP with custom tags, webjars, boostrap/css, custom less 
Controller -- spring @MVC annotations, bean validation 
Service -- @Cachable, @Transactional 
Repository: 3 spring profiles, spring data JPA, JPA default, JDBC. 



Leave me a detailed message if you get stuck and I will jump in and go through it

somethigns are really off. 
database supprots HSQLDB default, mysql, postgreSQL. 
connection parameters and drivers are declared into Maven profiles 
DDL and DML SQL scripts for each database vendors 



java based configurations such as spring xml configuration could be replaced by java configuration, checkout the javaconfig branch 


unit testing framework, spring test, junit, HSQLDB, Mockito, AssertJ, Hamcrest, Json path. tests are shared between persistence technologies 

going forward to spring to start off absolutely from beginning from scratch to reinforce what we have learnt 
it is pivitol all times 



## Table of Content 


Core Spring Content 
[create your first Spring application](lect1.md)
[Dependency Injection and Inversion of Control](lect2.md)
[More on Dependency Injection and Inversion of Control](lect2plus.md)


Project







## Run Spring App


```java

cd project-directory 
./mvnw spring-boot:run 


```

a lot of depedencies will be downloaded to your local maven repository 



## Development Environment 

1. download JDK and JRE 
https://www.java.com/en/download/manual.jsp
https://www.oracle.com/java/technologies/downloads/

```

javac --version  
javac 11.0.11
java --version  
openjdk 11.0.11 2021-04-20
OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.20.04)



```

2. maven 3.2.5 or above ideally configured for command line use 


```
sudo apt-get install maven

```
3. gradle 3.4.1. or higher installed 


```
sudo apt-get install gradle 

```

3. install IntelliJ IDEA:
https://www.jetbrains.com/help/idea/installation-guide.html

INtellij will lose its mind and we reindexed the project, we got to the file and validate caches and restart, it is bizzare. it broke how intellij picks up the classpat hand all the jars in the class. that is a hidden cost. it is a good way to get you quickly rolling again, you better progress through it and hit a problem that you cannot resolve. 




## Github 
git is software you install on your ocmputer to interact with git repositories. 
there is a lot of repositories that you can use and utilize for resources. 


### Github Basics 
github work flow, 
we will get you up to speed on how to use github to your benefit 

start with master branch and then follow along the handson coding and absorb the material in this 


how to fork a repository: click Fork -> Code -> Clone -> copy the link 


in intellij open new -> project from version control


a quirky thing that you see now and then 

so being able to compare your state of your code to my different branches that are kind of beyond there is a very powerful tool 

we can now do comparisons against the github 

the comparison will based on the folder that you are clicking on 
right click on project folder -> compare with branch 



where things are in grey, that means that is not there 

intellij does a real job comparing difference 


we want to add another remote repo to our github configuration locally 

```shell 
git add remote name url

git fetch url 
```



### branches 
when start on a new project or jumping into an existing one, it is important that we start anything new out on a new branch 

we should be careful if we want to do any change to the master branch, it shold be a representation of whta is on the production to get additional collaboration and communication going 

#### creating a branch: git branch branch-new_name

this will create a branch later we can switch to


#### delete a branch: git branch -d branch_name 

now if it is not fully merged in, it will give you a warning to substitute that lower case d to capital D 


#### swtich branch: git checkout branch-name 

we obviously cannot delete the branch that we are on 

if we swtich branches, any work that we might have in the staging area or the working directory will come over with us 
```shell
git status 

```

we cannot switch to a new branch if any of the files in our working directory or staging area would be overwritten 

the files will stay on their respective branches until you merge them 

branches are a key element of a propery git and github workflow 





### git branck_name checkout 

we are rewriting the working tree from the persecptive of that branch, it let us see the documents we are working on in that branch and version controlling from the perspective of that branch 

and we can veriy what branch we are on and which branch have we checked out to 

```shell
git branch 
* master
git status 

git checkout branch-name 

```


the purpose of checkout is to change the branch or the context where our upcoming commits will be made 

another purpose of checkout is to explore what your working tree are the directory and files at your system 


```shell

git log


```


```shell
git checkout xxxxxx_commit_number 
```
and our working tree is rewritten from that point in the commit time line 
when in this state, bear in mind that we are in detached head, this is not a place where we want to make any commit 


once you are done exploring your detached head state just to check out to one of your branches where you intent on making commits 


discarding edits: 
checkout: 
another purpose is to undo and discard edits we simply want to throw away 

we want to throw away those changes 
we want do 
```shell
git status
git checkout -- filename.txt

```


what that does for us is write thta file contents from the last commit 


the last tip for making checkout shortcut a few other steps 

if you want to create a branch and check out all in one step just 

```shell
git checkout -b branch_name 

```
that will create a branch and check you out all in one step 





##  gitignore 

avoid committing file that should be in your project, 


gitignore allows us to purposefully avoid staging or committing files to our git repo 

.css .bundle directory or pycache 


create a .gitignore file in your root directory 


```shell
touch .gitignore
git add .gitignore 
git commit -m "ignore temp files"

vim .gitignore 

```

from there each file should have a pattern that matches the files you want to ignore and not have to get paid attention to 
ignore patterns can have simple string matches or even better wildcards using * asterisk symbol 
*.log 
.sass-cache 
directory pattern name / if you want to ignore a directory 

ignore pattern applys to all files wihtin the directory they are in and any sub directory that are below that 

ignore patterns can also be placed in subdirectory those take place in one of those we put in the root 
it also allows us to negate patterns using ! exclamation marks 

!xx.log
the "#" describe what you ignore patterns about 
in github, it also helps you to populate that file 


if you want to know what files have been ignored in your repository and you do not want to look at .gitignores just go to command line 


#### git ls-files --others -- ignored --exclude-standard 

```shell
 git ls-files --others --ignored --exclude-standard
.idea/.gitignore
.idea/compiler.xml
.idea/encodings.xml
.idea/jarRepositories.xml
.idea/misc.xml
.idea/runConfigurations.xml
.idea/vcs.xml
.idea/workspace.xml


```








## Intellij
Intelliji is what you can see the most of, a lot of shops are still on eclipse, it is still a batch process on my side. 




















#### GitHub brancing

if you understand branches, you may be thinking this is a odd use of branches 

branches are long lived, those are very static points in time. 

the starting branch is the state of code at the beginning 
the ending branch is the state of code at the end 


benefits of branches: as you can compare your source code, to the branch source code. it is a great way to find errors, also, allows you to update the source code, could not do this with tags or commit ids 


It is recommended for you to fork the repositories to your own github account, this allows you to build you github examples and great content to show to future employers 

## Github Repository 

create a new repository 
xx-xx-xx

add gitignore Java not the standard one 

it works pretty seamlessly with command line 

show the hidden files 
```shell
ls -ltra 

```

 https://start.spring.io/

 i will start making transitions right now to start to catch up the new release 


Quite a few things to select there: 
 1. DevTools 
 2. Lombok 
 3. Spring Web
 4. Thymeleaf 
 5. MySQL
 6. H2
 7. JPA
 8. Actuator 

that is a nice way to start off 


CVS-> select git, add the project and type in your credentials in git 

replace gitignore file with your desired or much comprehensives contents 
just call out to start the spring and push to github and initialize externally 



## issue trackers 

we use github issue tracker, a typical workflow is you are gonna have your partners create issues in and you are going to work for them 


github panel -> isssues screen -> new issue 



we will work on issues and close out issues 






