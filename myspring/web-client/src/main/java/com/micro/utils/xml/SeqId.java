package com.micro.utils.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;
import org.apache.commons.digester3.annotations.rules.SetProperty;


@ObjectCreate(pattern = "config/seqid")
public class SeqId implements Serializable{
    private static final long serialVersionUID = 9025525256449268310L;

    @SetProperty(pattern = "config/seqid", attributeName = "join")
    private String join="";

    @Valid
    @NotEmpty
   // @Unique(key="zdPath")
    private List<Id> ids = new ArrayList<Id>();


    public String getJoin() {
        return join;
    }


    public void setJoin(String join) {
        this.join = join;
    }


    public List<Id> getIds() {
        return ids;
    }

    @SetNext
    public void addId(Id id){
        this.ids.add(id);
    }


}