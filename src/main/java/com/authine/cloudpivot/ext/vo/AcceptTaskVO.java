package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AcceptTaskVO {

    @ApiModelProperty("项目BU")
    private String bu;

    @ApiModelProperty("项目代码")
    private String jobCode;

    @ApiModelProperty("后台原来保存jobCode对应的ID")
    private String jobId;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("交付描述ID")
    private String deliverableDescriptionId;

    @ApiModelProperty("交付描述")
    private String deliverableDescription;

    @ApiModelProperty("交付品类ID")
    private String deliverableCategoryId;

    @ApiModelProperty("交付品类")
    private String deliverableCategory;

    @ApiModelProperty("任务发起人")
    private String taskDistributors;

    @ApiModelProperty("任务发起人Name")
    private String taskDistributorNames;

    @ApiModelProperty("要求描述")
    private String taskRequirement;

    @ApiModelProperty("交付数量")
    private Long quantity;

    @ApiModelProperty("交付时间")
    private Date deadline;

    @ApiModelProperty("交付时间")
    private String deadlineShow;

    @ApiModelProperty("实例ID")
    private String workflowInstanceId;

    @ApiModelProperty("workItemId")
    private String workItemId;

    @ApiModelProperty("接单URL")
    private String taskUrl;

    @ApiModelProperty("当前登陆用户id")
    private String userId;

    @ApiModelProperty("当前页码")
    private Integer page;

    @ApiModelProperty("当前页面数据量")
    private Integer pageSize;

    @ApiModelProperty("表单名称")
    private String formName;



}
