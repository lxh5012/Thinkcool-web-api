package com.authine.cloudpivot.ext.queryVo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class QueryClientPayment {

    //项目代码
    private String Jobcode;

    //项目名称
    private String ProjectName;

    //客户发票日
    private Date InvoicingDateClient;

    //客户发票号码
    private String ClientInvoice;

    //客户发票总金额(税前)
    private Integer amountBeforeTax;

    //客户发票总金额
    private Integer amountAfterTax;

    //客户付款账龄
    private String ClientPaymentAging;

    //客户实际付款日
    private Date ClientaymentRemittanceDate;

    //客户付款逾期日
    private Integer ClientPaymentOverDue;

    public String getJobcode() {
        return Jobcode;
    }

    public void setJobcode(String jobcode) {
        Jobcode = jobcode;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public Date getInvoicingDateClient() {
        return InvoicingDateClient;
    }

    public void setInvoicingDateClient(Date invoicingDateClient) {
        InvoicingDateClient = invoicingDateClient;
    }

    public String getClientInvoice() {
        return ClientInvoice;
    }

    public void setClientInvoice(String clientInvoice) {
        ClientInvoice = clientInvoice;
    }

    public Integer getAmountBeforeTax() {
        return amountBeforeTax;
    }

    public void setAmountBeforeTax(Integer amountBeforeTax) {
        this.amountBeforeTax = amountBeforeTax;
    }

    public Integer getAmountAfterTax() {
        return amountAfterTax;
    }

    public void setAmountAfterTax(Integer amountAfterTax) {
        this.amountAfterTax = amountAfterTax;
    }

    public String getClientPaymentAging() {
        return ClientPaymentAging;
    }

    public void setClientPaymentAging(String clientPaymentAging) {
        ClientPaymentAging = clientPaymentAging;
    }

    public Date getClientaymentRemittanceDate() {
        return ClientaymentRemittanceDate;
    }

    public void setClientaymentRemittanceDate(Date clientaymentRemittanceDate) {
        ClientaymentRemittanceDate = clientaymentRemittanceDate;
    }

    public Integer getClientPaymentOverDue() {
        return ClientPaymentOverDue;
    }

    public void setClientPaymentOverDue(Integer clientPaymentOverDue) {
        ClientPaymentOverDue = clientPaymentOverDue;
    }
}
