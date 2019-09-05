package com.authine.cloudpivot.ext.queryVo;
public class QueryDeliverable {


    //项目代码
    private String Jobcode;
    //项目名称
    private String ProjectName;
    //交付描述
    private String DeliverableDescription;
    //交付品类
    private String DeliverableCategory;
    //客户价格(税前)
    private String ClientPrice;
    //单位成本 (税前)
    private String UnitCost;
    //数量
    private String Quantity;

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

    public String getDeliverableDescription() {
        return DeliverableDescription;
    }

    public void setDeliverableDescription(String deliverableDescription) {
        DeliverableDescription = deliverableDescription;
    }

    public String getDeliverableCategory() {
        return DeliverableCategory;
    }

    public void setDeliverableCategory(String deliverableCategory) {
        DeliverableCategory = deliverableCategory;
    }

    public String getClientPrice() {
        return ClientPrice;
    }

    public void setClientPrice(String clientPrice) {
        ClientPrice = clientPrice;
    }

    public String getUnitCost() {
        return UnitCost;
    }

    public void setUnitCost(String unitCost) {
        UnitCost = unitCost;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
