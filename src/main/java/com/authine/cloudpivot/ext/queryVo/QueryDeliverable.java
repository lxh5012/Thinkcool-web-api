package com.authine.cloudpivot.ext.queryVo;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryDeliverable extends PageInfo {

    @ApiModelProperty("项目代码")
    private String Jobcode;
    @ApiModelProperty("项目名称")
    private String ProjectName;
    @ApiModelProperty("交付描述")
    private String DeliverableDescription;
    @ApiModelProperty("交付品类")
    private String DeliverableCategory;
    @ApiModelProperty("客户价格(税前)")
    private String ClientPrice;
    @ApiModelProperty("单位成本 (税前)")
    private String UnitCost;
    @ApiModelProperty("数量")
    private String Quantity;
    @ApiModelProperty("projectSummary主键ID")
    private String projectSummaryId;
    @ApiModelProperty("主键ID")
    private String deliverableId;
    @ApiModelProperty("deliverable状态")
    private String deliverableStatus;
    @ApiModelProperty("参与者")
    private String participant;

}
