package com.micro.utils.xml;

import org.apache.commons.digester3.annotations.rules.CallMethod;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.digester3.annotations.rules.CallMethod;
import org.apache.commons.digester3.annotations.rules.CallParam;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
@ObjectCreate(pattern = "config/property/converter")
public class Converter implements Serializable,IConverter {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    public Map<String, String> entryMap = new HashMap<String, String>();

    @CallMethod(pattern = "config/property/converter/entry")
    public void addEntry(
            @CallParam(pattern = "config/property/converter/entry", attributeName = "key") String key,
            @CallParam(pattern = "config/property/converter/entry") String value) throws Exception {
        if (StringUtils.isEmpty(key)) {
            throw new Exception("config/property/converter/entry中key不能为空");
        }
        entryMap.put(key, value);
    }

    public String get(String key) {
        String value = entryMap.get(key);
        if (value == null) {
            value = "";
        }
        return value;
    }

}
