package com.authine.cloudpivot.ext.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class ClientPaymentFinVO {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("客户订单号")
    private String clientPO;
    @ApiModelProperty("客户订单号")
    private BigDecimal clientPOvalue;
    @ApiModelProperty("客户订单金额")
    private String invoicingDateClient;
    @ApiModelProperty("发票号")
    private String clientInvoice;
    @ApiModelProperty("客户发票总金额(税前)")
    private BigDecimal amountBeforeTax;
    @ApiModelProperty("客户发票总金额(税后)")
    private BigDecimal amountAfterTax;
    @ApiModelProperty("客户付款确认日")
    private String clientPaymentCheckDate;
    @ApiModelProperty("客户付款账龄")
    private String clientPaymentAging;
    @ApiModelProperty("实际付款日")
    private String   clientaymentRemittanceDate;
    @ApiModelProperty("客户付款逾期日")
    private String clientPaymentOverDue;
    @ApiModelProperty("关联客户收款，保存子表数据时使用")
    private String parentId;
}
