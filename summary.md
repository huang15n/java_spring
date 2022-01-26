
1. create a model 


```java
@Entity 
public class ClassOne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attribute;

    @ManyToMany(name ="classOne_classTwo", JoinColunms = @JoinColumn("classOne_id"), inverseJoinColums = @JoinColum("classTwo)id")  )
    private Set <ClassTwo> classTwo = new HashSet<>() ;



    // getter and setter 

    





}

@Entity 
public class ClassTwo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attribute;

    @ManyToMany(mappedBy="classTwo") // htis has to be the exact set name in class one
    private Set <ClassOne> classOne  = new HashSet<>() ;;



    // getter and setter 

    





}

```

2. create a template in resources /templates

## xmlns:th="http://www.thymeleaf.org"
## th:each = "element: ${modelReturnCollection}"
## th:text = "${element.attribute}"


3. create a controller 


```java
@Controller 

public class XXXX{
    private final xxxService; 

    @RequestMapping({""})
    public String xxx(Model model){
        model.addAttribute("modelReturnCollection", xxxService.getXXXX());
        return "templateName";
    }
}

```

4. create a data.sql under resources 

``` SQL
INSERT INTO TABLENAME(attribute) values("")

```

in application.properties , add these 

server.port=8081
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.defer-datasource-initialization=true

5. create a repositiry package with xxRepository.java

```java

public interface xxxRepository extends CrudRepository<ClassName, Long>{

}

```

6. create a package called service with interface and implementaiton 

xxxService.java


```java
public interface xxxService{
    Set<ClassName> getClassName();
}

xxxServiceImpl.java

```java
@Service
public class xxxServiceImpl implements xxx Service{
    private final XxxRepository xxxRepsoitory ;
    public xxxServiceImpl(XxxRepository xxxRepsoitory){
        this.xxxRepsoitory = xxxRepsoitory;

    }

    Set<ClassName> getClassName(){
        Set<ClassName> xxxSet = new HashSet<>();
        xxxRepository.findAll().iterator().forEachRemaining(xxxSet::add);
        return xxxSet;

    }



}

```


7. create a runner class --- startup 


```java

public class XXXStartup implements ApplicationListener<ContextRefreshedEvent> {

    private ClassOneRepository  classOneRepository;
    private ClassTwoRepository classTwoRepository;

    public RecipeStartup(ClassOneRepository  classOneRepository, ClassTwoRepository classTwoRepository) {
        this.classOneRepository =  classOneRepository;
        this.classTwoRepositoryy = classTwoRepository;
    }


     @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<ClassOne> classOnes = new ArrayList<>();

        ClassOne classOne = new ClassOne();
        //set attribute for classOne 

        Optional<ClassTwo> classTwoOptional = classTwoRepository.findByxxxx("attribute");
        classTwo classTwoObject = classTwoOptional.get();
        classOne.getClassTwo().add(classTwoObject);

        classOnes.add(classOne);

        classOneRepository.saveAll(classOnes);


    }
```
