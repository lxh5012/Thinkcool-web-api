package com.authine.cloudpivot.ext.vo;


import javax.xml.crypto.Data;

public class ClientContractVO {

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
    private Data clientContractStarttime;

    //客户合同结束时间
    private Data clientContractEndtime;

    //客户合同状态
    private String clientContractStatus;

    //合同金额
    private Double contractValue;


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

    public Data getClientContractStarttime() {
        return clientContractStarttime;
    }

    public void setClientContractStarttime(Data clientContractStarttime) {
        this.clientContractStarttime = clientContractStarttime;
    }

    public Data getClientContractEndtime() {
        return clientContractEndtime;
    }

    public void setClientContractEndtime(Data clientContractEndtime) {
        this.clientContractEndtime = clientContractEndtime;
    }

    public String getClientContractStatus() {
        return clientContractStatus;
    }

    public void setClientContractStatus(String clientContractStatus) {
        this.clientContractStatus = clientContractStatus;
    }

    public Double getContractValue() {
        return contractValue;
    }

    public void setContractValue(Double contractValue) {
        this.contractValue = contractValue;
    }
}
