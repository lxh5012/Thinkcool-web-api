package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.soap.SAAJResult;
import java.sql.Date;

@Setter
@Getter
public class SummaryTaskVO {

    @ApiModelProperty("表单名称")
    private String formName;

    @ApiModelProperty("项目组")
    private String bu;

    @ApiModelProperty("useriId")
    private String userId;

    @ApiModelProperty("签约主体")
    private String contractLegalEntity;

    @ApiModelProperty("客户集团")
    private  String clientGroup;

    @ApiModelProperty("客户品牌")
    private String clientBrand;

    @ApiModelProperty("后台原来保存jobCode对应的ID")
    private String jobId;

    @ApiModelProperty("项目代码")
    private String jobCode;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("项目开始日期")
    private String projectStartDate;

    @ApiModelProperty("项目开始日期入参Begin")
    private Date projectStartDateBegin;

    @ApiModelProperty("项目开始日期入参Start")
    private Date projectStartDateEnd;


    @ApiModelProperty("项目结束日期")
    private String projectEndDate;

    @ApiModelProperty("项目结束日期Begin")
    private Date projectEndDateBegin;

    @ApiModelProperty("项目结束日期end")
    private String projectEndDateEnd;

    @ApiModelProperty("Deliverable数量")
    private Long deliverableNum;

    @ApiModelProperty("工单总数量")
    private Long taskNum;

    @ApiModelProperty("已完成总数量")
    private Long finishedNum;


    @ApiModelProperty("当前页码")
    private Integer page;

    @ApiModelProperty("当前页面数据量")
    private Integer pageSize;

}
