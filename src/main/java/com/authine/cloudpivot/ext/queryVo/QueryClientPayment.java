package com.authine.cloudpivot.ext.queryVo;
public class QueryClientPayment {

    //项目代码
    private String Jobcode;

    //项目名称
    private String ProjectName;

    //客户发票日
    private String InvoicingDateClient;

    private String ClientaymentRemittanceDate;

    //客户发票号码
    private String ClientInvoice;

    //客户发票总金额(税前)
    private Integer amountBeforeTax;

    //客户发票总金额
    private Integer amountAfterTax;

    //客户付款账龄
    private String ClientPaymentAging;
    //客户付款账龄
    private String ClientPaymentCheckDate;
    //客户付款逾期日
    private Integer ClientPaymentOverDue;
    //当前页码
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    //当前页面数据量
    private Integer pageSize;

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

    public String getInvoicingDateClient() {
        return InvoicingDateClient;
    }

    public void setInvoicingDateClient(String invoicingDateClient) {
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

    public Integer getClientPaymentOverDue() {
        return ClientPaymentOverDue;
    }

    public void setClientPaymentOverDue(Integer clientPaymentOverDue) {
        ClientPaymentOverDue = clientPaymentOverDue;
    }
    public String getClientaymentRemittanceDate() {
        return ClientaymentRemittanceDate;
    }

    public void setClientaymentRemittanceDate(String clientaymentRemittanceDate) {
        ClientaymentRemittanceDate = clientaymentRemittanceDate;
    }

    public String getClientPaymentCheckDate() {
        return ClientPaymentCheckDate;
    }

    public void setClientPaymentCheckDate(String clientPaymentCheckDate) {
        ClientPaymentCheckDate = clientPaymentCheckDate;
    }
}
