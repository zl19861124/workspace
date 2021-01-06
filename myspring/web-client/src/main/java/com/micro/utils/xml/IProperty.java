package com.micro.utils.xml;

public interface IProperty {
    public IConverter getConverter();
    public String getPattern();
    public String getDefaultValue();
    public String getNotNull();
    public String getName();
    public String getZdPath();
}
