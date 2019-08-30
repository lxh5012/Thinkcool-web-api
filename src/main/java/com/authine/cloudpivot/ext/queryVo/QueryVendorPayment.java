package com.authine.cloudpivot.ext.queryVo;
public class QueryVendorPayment {

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
    //当前页码
    private Integer page;
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

    public String getVendorInvoicingDate() {
        return vendorInvoicingDate;
    }

    public void setVendorInvoicingDate(String vendorInvoicingDate) {
        this.vendorInvoicingDate = vendorInvoicingDate;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getPaymentVendorDate() {
        return PaymentVendorDate;
    }

    public void setPaymentVendorDate(String paymentVendorDate) {
        PaymentVendorDate = paymentVendorDate;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPaymentCheckDate() {
        return PaymentCheckDate;
    }

    public void setPaymentCheckDate(String paymentCheckDate) {
        PaymentCheckDate = paymentCheckDate;
    }

    public String getActualPaymentDate() {
        return ActualPaymentDate;
    }

    public void setActualPaymentDate(String actualPaymentDate) {
        ActualPaymentDate = actualPaymentDate;
    }

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
}
