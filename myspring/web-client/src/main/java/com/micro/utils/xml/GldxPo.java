package com.micro.utils.xml;

import org.apache.commons.beanutils.PropertyUtils;

import java.io.Serializable;

public class GldxPo implements Serializable {
    private static final long serialVersionUID = 1L;


    private int status;

    private String messsage;

    private String seqId;

    private String uid;

    private String gldxlxid;

    private String gldxid;

    private String thirdId;

    private String dxmc;

    private String xtdw;

    private String ext1;

    private String ext2;

    private String ext3;

    /**
     * 消息错误类型
     */
    private String errorType;

    public void setStatus(int status, String message) {
        this.status = status;
        this.messsage = message;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the messsage
     */
    public String getMesssage() {
        return messsage;
    }

    /**
     * @return the seqId
     */
    public String getSeqId() {
        return seqId;
    }

    /**
     * @param seqId
     *            the seqId to set
     */
    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getXtdw() {
        String xtdw = "";
        try {
            xtdw = (String) PropertyUtils.getProperty(this, "xtdw");
        } catch (Exception ex) {

        }
        return xtdw;

    }

    public void setXtdw(String xtdw) {
        try {
            PropertyUtils.setProperty(this, "xtdw", xtdw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDxmc() {
        return dxmc;
    }

    public void setDxmc(String dxmc) {
        this.dxmc = dxmc;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getSyncMessage() {
        return this.messsage;
    }

    public Integer getSyncStatus() {
        return this.status;
    }

    public void setSyncStatus(Integer status, String msg) {
        this.status=status;
        this.messsage=msg;
    }

    public String getGldxlxid() {
        return gldxlxid;
    }

    public void setGldxlxid(String gldxlxid) {
        this.gldxlxid = gldxlxid;
    }

    public String getUid(){
        return this.gldxlxid+"_"+this.getSeqId();
    }

    public String getGldxid() {
        return gldxid;
    }

    public void setGldxid(String gldxid) {
        this.gldxid = gldxid;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

}
