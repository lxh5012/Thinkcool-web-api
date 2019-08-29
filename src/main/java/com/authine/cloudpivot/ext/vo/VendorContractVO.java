package com.authine.cloudpivot.ext.vo;


import javax.xml.crypto.Data;

public class VendorContractVO {

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
    private Data vendorContractStarttime;

    //供应商合同结束时间
    private Data vendorContractEndtime;

    //供应商合同签订日期
    private Data venderContracgtSigningDate;

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

    public Data getVendorContractStarttime() {
        return vendorContractStarttime;
    }

    public void setVendorContractStarttime(Data vendorContractStarttime) {
        this.vendorContractStarttime = vendorContractStarttime;
    }

    public Data getVendorContractEndtime() {
        return vendorContractEndtime;
    }

    public void setVendorContractEndtime(Data vendorContractEndtime) {
        this.vendorContractEndtime = vendorContractEndtime;
    }

    public Data getVenderContracgtSigningDate() {
        return venderContracgtSigningDate;
    }

    public void setVenderContracgtSigningDate(Data venderContracgtSigningDate) {
        this.venderContracgtSigningDate = venderContracgtSigningDate;
    }
}
