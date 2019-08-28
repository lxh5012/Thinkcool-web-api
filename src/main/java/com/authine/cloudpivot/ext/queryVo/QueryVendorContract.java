package com.authine.cloudpivot.ext.queryVo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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



}
