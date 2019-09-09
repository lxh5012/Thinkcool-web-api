package com.authine.cloudpivot.ext.queryVo;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QueryVendorContract extends PageInfo {

    @ApiModelProperty("项目代码")
    private String jobcode;

    /*@ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("合同类型")
    private String contractType;

    @ApiModelProperty("供应商名称")
    private String vendorName;

    @ApiModelProperty("供应商合同版本")
    private String vendorContractVersion;

    @ApiModelProperty("供应商合同编码")
    private String vendorContractCode;*/

    @ApiModelProperty("供应商合同开始时间")
    private String starttimeStart;
    @ApiModelProperty("供应商合同开始时间")
    private String starttimeEnd;

    /*@ApiModelProperty("供应商合同结束时间")
    private String endtimeStart;
    @ApiModelProperty("供应商合同结束时间")
    private String endtimeEnd;

    @ApiModelProperty("供应商合同签订日期")
    private String startSigningDate;
    @ApiModelProperty("供应商合同签订日期")
    private String endSigningDate;*/

    @ApiModelProperty("供应商合同状态")
    private String vendorContractStatus;

    @ApiModelProperty("供应商合同最小金额")
    private Double contractValue1;

    @ApiModelProperty("供应商合同最大金额")
    private Double contractValue2;




}
