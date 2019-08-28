package com.authine.cloudpivot.ext.vo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SummaryTaskModel {

    //项目组
    private String BU;

    //签约主体
    private String contractLegalEntity;

    //客户集团
    private  String clientGroup;

    //客户品牌
    private String clientBrand;

    //项目代码
    private String Jobcode;

    //项目名称
    private String projectName;

    //项目开始日期
    private String ProjectStartDate;

    //项目结束日期
    private String ProjectEndDate;


    //Deliverable数量
    private Long deliverableNum;

    //工单总数量
    private Long taskNum;

    //已完成总数量
    private Long finishedNum;

    //project summary ID
    private String summaryId;


}
