package com.authine.cloudpivot.ext.queryVo;


import javax.xml.crypto.Data;

public class QueryClientContract {

    //项目代码
    private String jobcode;

    //项目名称
    private String projectName;

    //合同类型
    private String contractType;

    //客户合同版本
    private String clientContractVersion;

    //客户合同编码
    private String clientContractCode;

    //客户合同开始时间
    private Data starttimeStart;
    //客户合同开始时间
    private Data starttimeEnd;

    //客户合同结束时间
    private Data endtimeStart;
    //客户合同结束时间
    private Data endtimeEnd;

    //客户合同状态
    private String clientContractStatus;

    //合同金额
    private Double contractValue1;
    //合同金额
    private Double contractValue2;

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

    public String getClientContractVersion() {
        return clientContractVersion;
    }

    public void setClientContractVersion(String clientContractVersion) {
        this.clientContractVersion = clientContractVersion;
    }

    public String getClientContractCode() {
        return clientContractCode;
    }

    public void setClientContractCode(String clientContractCode) {
        this.clientContractCode = clientContractCode;
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

    public String getClientContractStatus() {
        return clientContractStatus;
    }

    public void setClientContractStatus(String clientContractStatus) {
        this.clientContractStatus = clientContractStatus;
    }

    public Double getContractValue1() {
        return contractValue1;
    }

    public void setContractValue1(Double contractValue1) {
        this.contractValue1 = contractValue1;
    }

    public Double getContractValue2() {
        return contractValue2;
    }

    public void setContractValue2(Double contractValue2) {
        this.contractValue2 = contractValue2;
    }
}
