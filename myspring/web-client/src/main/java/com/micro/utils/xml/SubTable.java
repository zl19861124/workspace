package com.micro.utils.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;
import org.apache.commons.digester3.annotations.rules.SetProperty;


@SuppressWarnings("serial")
@ObjectCreate(pattern="config/subTable")
public class SubTable implements Serializable{

    @NotEmpty
    @SetProperty(pattern="config/subTable",attributeName="name")
    private String name;

    @NotEmpty
    @SetProperty(pattern="config/subTable",attributeName="subPath")
    private String subPath;

    @SetProperty(pattern="config/subTable",attributeName="notNull")
    private String notNull="false";


    @Valid
    @NotEmpty
   // @Unique(key="name")
    private List<SubProperty> properties = new ArrayList<SubProperty>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }


    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }


    public List<SubProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<SubProperty> properties) throws Exception {
        if(this.properties.contains(properties)){
            throw new Exception("SubProperty中的property name必需唯一");
        }
        this.properties = properties;
    }

    @SetNext
    public void addProperty(SubProperty subProperty){
        this.properties.add(subProperty);
    }
}
