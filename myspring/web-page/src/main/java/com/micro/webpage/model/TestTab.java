package com.micro.webpage.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class TestTab  implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

}
