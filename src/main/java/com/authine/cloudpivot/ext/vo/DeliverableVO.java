package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class DeliverableVO {

    @ApiModelProperty("项目代码")
    private String jobCode;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("交付描述")
    private String deliverableDescription;

    @ApiModelProperty("交付品类")
    private String deliverableCategory;

    @ApiModelProperty("客户价格税前")
    private BigDecimal clientPrice;

    @ApiModelProperty("单位成本税前")
    private BigDecimal unitCost;

    @ApiModelProperty("数量")
    private BigDecimal quantity;

    @ApiModelProperty("利润")
    private BigDecimal profit;

    @ApiModelProperty("利润%")
    private BigDecimal margin;

    @ApiModelProperty("主表bigobjectid")
    private String bizid;

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("客户总价格")
    private BigDecimal sumClientPrice;

    @ApiModelProperty("单位总成本")
    private BigDecimal sumUnitCost;

    @ApiModelProperty("供应商税")
    private BigDecimal venforTax;

    @ApiModelProperty("客户税 % Client tax %")
    private BigDecimal clientTax;

    @ApiModelProperty("客户单价税前")
    private BigDecimal clientUnitPrice;

    @ApiModelProperty("客户合同信息")
    private String clientContractContent;

    @ApiModelProperty("供应商合同信息")
    private String vendorContractContent;

    @ApiModelProperty("客户收款内容")
    private String clientPaymentContent;

    @ApiModelProperty("供应商付款内容")
    private String vendorPaymentContent;

    @ApiModelProperty("表单编辑url")
    private String formUrl;

    @ApiModelProperty("deliverable状态")
    private Integer deliverableStatus;

    @ApiModelProperty("deliverable状态显示")
    private String deliverableStatusView;


}
