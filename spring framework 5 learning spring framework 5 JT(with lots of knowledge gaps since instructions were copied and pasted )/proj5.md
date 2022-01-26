


## @MappedSuperclass 
need to add dependency for javax.persistence   JPA

we will inherit from this class or other classes will be inheriting and it won't map to the database 

```java


package com.uwindsor.clinic.models;


import java.io.Serializable;



@MappedSuperclass
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

```