package com.micro.utils.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GldxXmlVo {
    private Map<String, String> mainTabel = new HashMap<String, String>();

    private Map<String, List<Map<String, String>>> subTables = new HashMap<String, List<Map<String, String>>>();

    /**
     * @return the mainTabel
     */
    public Map<String, String> getMainTabel() {
        return mainTabel;
    }

    /**
     * @param mainTabel
     *            the mainTabel to set
     */
    public void setMainTabel(Map<String, String> mainTabel) {
        this.mainTabel = mainTabel;
    }

    /**
     * @return the subTables
     */
    public Map<String, List<Map<String, String>>> getSubTables() {
        return subTables;
    }

    /**
     * @param subTables
     *            the subTables to set
     */
    public void setSubTables(Map<String, List<Map<String, String>>> subTables) {
        this.subTables = subTables;
    }

    public void addToMainTable(String zdName, String value) {
        mainTabel.put(zdName, value);
    }

    public void addToSubTable(String subName, Map<String, String> subTable) {
        if (subTables.get(subName) == null) {
            subTables.put(subName, new ArrayList<Map<String,String>>());
        }
        subTables.get(subName).add(subTable);
    }
}
