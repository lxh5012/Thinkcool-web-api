package com.authine.cloudpivot.ext.queryVo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QueryClientContract {

    /*@ApiModelProperty("项目代码")
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
    private String clientContractCode;*/

    @ApiModelProperty("客户合同开始时间")
    private String starttimeStart;
    @ApiModelProperty("客户合同开始时间")
    private String starttimeEnd;

    /*@ApiModelProperty("客户合同结束时间")
    private String endtimeStart;
    @ApiModelProperty("客户合同结束时间")
    private String endtimeEnd;*/

    @ApiModelProperty("客户合同状态")
    private String clientContractStatus;

    @ApiModelProperty("合同金额")
    private Double contractValue1;
    @ApiModelProperty("合同金额")
    private Double contractValue2;

    @ApiModelProperty("当前页码")
    private Integer page;

    @ApiModelProperty("当前页面数据量")
    private Integer pageSize;


}
