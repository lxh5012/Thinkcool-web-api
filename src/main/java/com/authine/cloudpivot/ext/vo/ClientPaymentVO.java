package com.authine.cloudpivot.ext.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class ClientPaymentVO {

    //项目代码
    private String Jobcode;

    //项目名称
    private String ProjectName;

    //客户发票日
    private String InvoicingDateClient;

    //客户发票号码
    private String ClientInvoice;

    //客户发票总金额(税前)
    private Integer amountBeforeTax;

    //客户发票总金额
    private Integer amountAfterTax;

    //客户付款账龄
    private String ClientPaymentAging;

    //客户实际付款日
    private String ClientaymentRemittanceDate;
    //客户付款确认日
    private String ClientPaymentCheckDate;

    //客户付款逾期日
    private Integer ClientPaymentOverDue;
   //主表bigobjectid
    private String bizid;
    //子表每行数据的id
    private String id;



}
