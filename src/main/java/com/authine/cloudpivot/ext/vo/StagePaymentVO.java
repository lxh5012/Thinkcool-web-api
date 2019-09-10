package com.authine.cloudpivot.ext.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hpsf.Decimal;

import java.util.Date;
@Getter
@Setter
public class StagePaymentVO {
     @ApiModelProperty("id")
     private  String id;
     @ApiModelProperty("付款期数")
     private String pay;
     @ApiModelProperty("供应商发票号")
     private  String vendorInvoice;
     @ApiModelProperty("供应商开票日期")
     private Date vendorInvoicingDate ;
     @ApiModelProperty("付款")
     private String installment;
     @ApiModelProperty("向供应商付款日期")
     private Date paymentVendorDate ;
     @ApiModelProperty("参数")
     private  String index ;
     @ApiModelProperty("预计付款日期")
     private Date paymentCheckDate;
     @ApiModelProperty("实际付款日期")
     private Date actualPaymentDate;
     @ApiModelProperty("关联供应商付款，保存子表数据时使用")
     private String parentId;
}
