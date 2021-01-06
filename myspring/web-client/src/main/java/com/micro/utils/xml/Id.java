package com.micro.utils.xml;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetProperty;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ObjectCreate(pattern="config/seqid/id")
public class Id implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3414419768647355443L;

    @NotEmpty
    @SetProperty(pattern = "config/seqid/id", attributeName = "zdPath")
    private String zdPath;

    public String getZdPath() {
        return zdPath;
    }

    public void setZdPath(String zdPath) {
        this.zdPath = zdPath;
    }


}
