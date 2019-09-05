package com.authine.cloudpivot.ext.vo;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class DeliverableVO {

  /*  //项目代码
    private String Jobcode;
    //项目名称
    private String ProjectName;
    //交付描述
    private String DeliverableDescription;
    //交付品类
    private String DeliverableCategory;
    //客户价格(税前)
    private BigDecimal ClientPrice;
    //单位成本 (税前)
    private BigDecimal UnitCost;
    //数量
    private BigDecimal Quantity;
   //主表bigobjectid
    private String bizid;
    //子表每行数据的id
    private String id;*/
    //客户总价格
    private BigDecimal sumClientPrice;
    //单位总成本
    private BigDecimal sumUnitCost;



}
