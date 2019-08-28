package com.authine.cloudpivot.ext.vo;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
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
    private String vendorContractStarttime;

    //供应商合同结束时间
    private String vendorContractEndtime;

    //供应商合同签订日期
    private String venderContracgtSigningDate;




}
