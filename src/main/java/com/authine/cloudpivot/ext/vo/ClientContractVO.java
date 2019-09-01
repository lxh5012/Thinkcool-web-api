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
public class ClientContractVO {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("项目代码")
    private String jobcode;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("合同类型")
    private String contractType;

    @ApiModelProperty("客户名称")
    private String clientName;

    @ApiModelProperty("客户合同版本")
    private String clientContractVersion;

    @ApiModelProperty("客户合同编码")
    private String clientContractCode;

    @ApiModelProperty("客户合同开始时间")
    private String clientContractStarttime;

    @ApiModelProperty("客户合同结束时间")
    private String clientContractEndtime;

    @ApiModelProperty("客户合同状态")
    private String clientContractStatus;

    @ApiModelProperty("合同金额")
    private Double contractValue;

    @ApiModelProperty("表单url")
    private String formUrl;



}
