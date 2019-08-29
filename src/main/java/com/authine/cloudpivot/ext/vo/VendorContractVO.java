package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;


@Getter
@Setter
public class VendorContractVO {

    @ApiModelProperty("客户合同开始时间")
    private String jobcode;

    @ApiModelProperty("客户合同开始时间")
    private String projectName;

    @ApiModelProperty("客户合同开始时间")
    private String contractType;

    @ApiModelProperty("客户合同开始时间")
    private String vendorName;

    @ApiModelProperty("客户合同开始时间")
    private String vendorContractVersion;

    @ApiModelProperty("客户合同开始时间")
    private String vendorContractCode;

    @ApiModelProperty("客户合同开始时间")
    private String vendorContractStarttime;

    @ApiModelProperty("客户合同开始时间")
    private String vendorContractEndtime;

    @ApiModelProperty("客户合同开始时间")
    private String venderContracgtSigningDate;

    @ApiModelProperty("客户合同开始时间")
    private String vendorContractStatus;

    @ApiModelProperty("客户合同开始时间")
    private Double contractValue;


}
