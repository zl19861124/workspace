package com.micro.utils.xml;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;
import org.apache.commons.digester3.annotations.rules.SetProperty;
public class Config implements Serializable {
    private static final long serialVersionUID = 6139110810362636379L;

    @NotEmpty
    @SetProperty(pattern = "config", attributeName = "gldxlx")
    private String gldxlx;

    @SetProperty(pattern = "config", attributeName = "description")
    private String description;

    @NotEmpty
    @SetProperty(pattern = "config", attributeName = "itemPath")
    private String itemPath;

    @NotEmpty
    @SetProperty(pattern = "config", attributeName = "handlerFeedback")
    private String handlerFeedback = "true";

    @NotEmpty
    @SetProperty(pattern = "config/dxmc", attributeName = "zdPath")
    private String dxmc;

    @SetProperty(pattern = "config", attributeName = "feedBackUrl")
    private String feedBackUrl = "http://0.0.0.0:0";


    @Valid
    @NotNull
    private SeqId seqid;


    @Valid
    @NotEmpty
    //@Unique(key = "name")
    private List<Property> properties = new ArrayList<Property>();



    @Valid
   // @Unique(key = "name")
    private List<SubTable> subTables = new ArrayList<SubTable>();

    public String getGldxlx() {
        return gldxlx;
    }

    public void setGldxlx(String gldxlx) {
        this.gldxlx = gldxlx;
    }

    public String getDescription() {
        return description;
    }

    public String getHandlerFeedback() {
        return handlerFeedback;
    }

    public void setHandlerFeedback(String handlerFeedback) {
        this.handlerFeedback = handlerFeedback;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemPath() {
        return itemPath;
    }

    public void setItemPath(String itemPath) {
        this.itemPath = itemPath;
    }

    public String getDxmc() {
        return dxmc;
    }

    public void setDxmc(String dxmc) {
        this.dxmc = dxmc;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<SubTable> getSubTables() {
        return subTables;
    }



    public SeqId getSeqid() {
        return seqid;
    }

    @SetNext
    public void setSeqid(SeqId seqid) {
        this.seqid = seqid;
    }



    @SetNext
    public void addProperty(Property property) {
        this.properties.add(property);
    }

    @SetNext
    public void addSubTable(SubTable subtable) {
        this.subTables.add(subtable);
    }



    public String getFeedBackUrl() {
        return feedBackUrl;
    }

    public void setFeedBackUrl(String feedBackUrl) {
        this.feedBackUrl = feedBackUrl;
    }

}

