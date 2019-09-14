package com.authine.cloudpivot.ext.queryVo;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryDeliverable extends PageInfo {


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

    private String projectSummaryId;
    @ApiModelProperty("主键ID")
    private String deliverableId;
    private int deliverableStatus;
}
