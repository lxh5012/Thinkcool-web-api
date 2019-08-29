package com.authine.cloudpivot.ext.queryVo;


import javax.xml.crypto.Data;

public class QueryVendorContract {

    //项目代码
    private String jobcode;

    //项目名称
    private String projectName;

    //合同类型
    private String contractType;

    //供应商名称
    private String vendorName;

    //供应商合同版本
    private String vendorContractVersion;

    //供应商合同编码
    private String vendorContractCode;

    //供应商合同开始时间
    private Data starttimeStart;
    //供应商合同开始时间
    private Data starttimeEnd;

    //供应商合同结束时间
    private Data endtimeStart;
    //供应商合同结束时间
    private Data endtimeEnd;

    //供应商合同签订日期
    private Data startSigningDate;
    //供应商合同签订日期
    private Data endSigningDate;

    public String getJobcode() {
        return jobcode;
    }

    public void setJobcode(String jobcode) {
        this.jobcode = jobcode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContractVersion() {
        return vendorContractVersion;
    }

    public void setVendorContractVersion(String vendorContractVersion) {
        this.vendorContractVersion = vendorContractVersion;
    }

    public String getVendorContractCode() {
        return vendorContractCode;
    }

    public void setVendorContractCode(String vendorContractCode) {
        this.vendorContractCode = vendorContractCode;
    }

    public Data getStarttimeStart() {
        return starttimeStart;
    }

    public void setStarttimeStart(Data starttimeStart) {
        this.starttimeStart = starttimeStart;
    }

    public Data getStarttimeEnd() {
        return starttimeEnd;
    }

    public void setStarttimeEnd(Data starttimeEnd) {
        this.starttimeEnd = starttimeEnd;
    }

    public Data getEndtimeStart() {
        return endtimeStart;
    }

    public void setEndtimeStart(Data endtimeStart) {
        this.endtimeStart = endtimeStart;
    }

    public Data getEndtimeEnd() {
        return endtimeEnd;
    }

    public void setEndtimeEnd(Data endtimeEnd) {
        this.endtimeEnd = endtimeEnd;
    }

    public Data getStartSigningDate() {
        return startSigningDate;
    }

    public void setStartSigningDate(Data startSigningDate) {
        this.startSigningDate = startSigningDate;
    }

    public Data getEndSigningDate() {
        return endSigningDate;
    }

    public void setEndSigningDate(Data endSigningDate) {
        this.endSigningDate = endSigningDate;
    }
}
