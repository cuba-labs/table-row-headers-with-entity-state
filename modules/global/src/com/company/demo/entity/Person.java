package com.company.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "DEMO_PERSON")
@Entity(name = "demo$Person")
public class Person extends StandardEntity {
    private static final long serialVersionUID = 3111962661255477492L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "AGE")
    protected Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }


}