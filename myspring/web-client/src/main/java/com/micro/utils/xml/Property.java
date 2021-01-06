package com.micro.utils.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;
import org.apache.commons.digester3.annotations.rules.SetProperty;


/**
 * 主表中的property对象
 * */
@ObjectCreate(pattern = "config/property")
public class Property implements Serializable,IProperty {

    private static final long serialVersionUID = 7113806771929587279L;

    @NotEmpty
    @SetProperty(pattern = "config/property", attributeName = "name")
    private String name = "";

    @NotEmpty
    @SetProperty(pattern = "config/property", attributeName = "zdPath")
    private String zdPath;

    @SetProperty(pattern = "config/property", attributeName = "notNull")
    private String notNull = "false";

    @SetProperty(pattern = "config/property", attributeName = "pattern")
    private String pattern;

    @SetProperty(pattern = "config/property", attributeName = "default")
    private String defaultValue = "";
    @Valid
    private Converter converter;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZdPath() {
        return zdPath;
    }

    public void setZdPath(String zdPath) {
        this.zdPath = zdPath;
    }


    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }


    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Converter getConverter() {
        return converter;
    }

    @SetNext
    public void setConverter(Converter converter) {
        this.converter = converter;
    }



    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj) {
            return true;
        }

        if (obj instanceof Property) {
            Property property = (Property) obj;
            return property.name.equals(this.name);
        }
        return false;

    }

    public int hashCode() {
        return name.hashCode();
    }

}

