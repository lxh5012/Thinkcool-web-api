package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorContractVO {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("项目代码")
    private String jobcode;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("合同类型")
    private String contractType;

    @ApiModelProperty("供应商名称")
    private String vendorName;

    @ApiModelProperty("供应商合同版本")
    private String vendorContractVersion;

    @ApiModelProperty("供应商合同编码")
    private String vendorContractCode;

    @ApiModelProperty("供应商合同开始时间")
    private String vendorContractStarttime;

    @ApiModelProperty("供应商合同结束时间")
    private String vendorContractEndtime;

    @ApiModelProperty("供应商合同签订日期")
    private String venderContracgtSigningDate;

    @ApiModelProperty("供应商合同状态")
    private String vendorContractStatus;

    @ApiModelProperty("供应商合同金额")
    private Double contractValue;

    @ApiModelProperty("代办id")
    private String workItemId;

    @ApiModelProperty("流程实例id")
    private String instanceId;

    @ApiModelProperty("填写profitCommercial url")
    private String profitCommercialUrl;

    @ApiModelProperty("关联客户合同，保存子表数据时使用")
    private String parentId;

}
