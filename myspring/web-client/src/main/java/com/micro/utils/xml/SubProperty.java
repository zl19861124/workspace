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
 * 子表中的property对象
 * */
@ObjectCreate(pattern = "config/subTable/property")
public class SubProperty  implements Serializable,IProperty {
    /**
     *
     */
    private static final long serialVersionUID = -1914464409949984682L;

    @NotEmpty
    @SetProperty(pattern = "config/subTable/property", attributeName = "name")
    private String name = "";

    @NotEmpty
    @SetProperty(pattern = "config/subTable/property", attributeName = "zdPath")
    private String zdPath;

    @SetProperty(pattern = "config/subTable/property", attributeName = "notNull")
    private String notNull = "false";

    @SetProperty(pattern = "config/subTable/property", attributeName = "pattern")
    private String pattern;

    @SetProperty(pattern = "config/subTable/property", attributeName = "default")
    private String defaultValue = "";

    @Valid
    private SubConverter subConverter;


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


    /**
     * @return the notNull
     */
    public String getNotNull() {
        return notNull;
    }

    /**
     * @param notNull
     *            the notNull to set
     */
    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    /**
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param pattern
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue
     *            the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public IConverter getConverter() {
        return subConverter;
    }

    @SetNext
    public void setConverter(SubConverter subConverter) {
        this.subConverter = subConverter;
    }




    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj instanceof SubProperty) {
            SubProperty subProperty = (SubProperty) obj;
            return subProperty.name.equals(this.name);
        }
        return false;

    }

    public int hashCode() {
        return name.hashCode();
    }

}