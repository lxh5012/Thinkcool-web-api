package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class VendorPaymentVO {
    //主表bigobjectid
    private String bizid;
    //子表每行数据的id
    private String id;
    //项目代码
    private String Jobcode;

    //项目名称
    private String ProjectName;
    //供应商开票日期
    private String vendorInvoicingDate;
    //付款（100%）
    private String installment;
    //向供应商付款日期类型
    private String PaymentVendorDate;
    //参数
    private String index;
    //预计付款日期
    private String PaymentCheckDate;
    //实际付款日期
    private String ActualPaymentDate;

    private String formUrl;



}
