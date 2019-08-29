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
    private String starttimeStart;
    //供应商合同开始时间
    private String starttimeEnd;

    //供应商合同结束时间
    private String endtimeStart;
    //供应商合同结束时间
    private String endtimeEnd;

    //供应商合同签订日期
    private String startSigningDate;
    //供应商合同签订日期
    private String endSigningDate;

    //供应商合同状态
    private String vendorContractStatus;

    //供应商合同最小金额
    private Double contractValue1;

    //供应商合同最大金额
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

    public String getStarttimeStart() {
        return starttimeStart;
    }

    public void setStarttimeStart(String starttimeStart) {
        this.starttimeStart = starttimeStart;
    }

    public String getStarttimeEnd() {
        return starttimeEnd;
    }

    public void setStarttimeEnd(String starttimeEnd) {
        this.starttimeEnd = starttimeEnd;
    }

    public String getEndtimeStart() {
        return endtimeStart;
    }

    public void setEndtimeStart(String endtimeStart) {
        this.endtimeStart = endtimeStart;
    }

    public String getEndtimeEnd() {
        return endtimeEnd;
    }

    public void setEndtimeEnd(String endtimeEnd) {
        this.endtimeEnd = endtimeEnd;
    }

    public String getStartSigningDate() {
        return startSigningDate;
    }

    public void setStartSigningDate(String startSigningDate) {
        this.startSigningDate = startSigningDate;
    }

    public String getEndSigningDate() {
        return endSigningDate;
    }

    public void setEndSigningDate(String endSigningDate) {
        this.endSigningDate = endSigningDate;
    }

    public String getVendorContractStatus() {
        return vendorContractStatus;
    }

    public void setVendorContractStatus(String vendorContractStatus) {
        this.vendorContractStatus = vendorContractStatus;
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
