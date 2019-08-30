package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.soap.SAAJResult;

@Setter
@Getter
public class SummaryTaskVO {

    //表单名称
    private String formName;


    //项目组
    private String bu;

    private String userId;

    //签约主体
    private String contractLegalEntity;

    //客户集团
    private  String clientGroup;

    //客户品牌
    private String clientBrand;

    //后台原来保存jobCode对应的ID
    private String jobId;

    //项目代码
    private String jobCode;

    //项目名称
    private String projectName;

    //项目开始日期
    private String projectStartDate;

    //项目结束日期
    private String projectEndDate;


    //Deliverable数量
    private Long deliverableNum;

    //工单总数量
    private Long taskNum;

    //已完成总数量
    private Long finishedNum;


    //当前页码
    private Integer page;

    //当前页面数据量
    private Integer pageSize;

}
