package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TaskDetialVO {

    @ApiModelProperty("后台原来保存jobCode对应的ID")
    private String jobId;

    @ApiModelProperty("表单名称")
    private String jobCode;

    @ApiModelProperty("交付描述")
    private String deliverableDescription;

    @ApiModelProperty("交付品类")
    private String deliverableCategory;

    @ApiModelProperty("任务发起人")
    private String taskDistributors;


    //前端页面显示任务发起人，userName用","连接
    @ApiModelProperty("任务发起人")
    private String taskDistributorUserName;


    @ApiModelProperty("接单人")
    private String taskOwners;

    //前端页面显示接单人，userName用","连接
    @ApiModelProperty("前端页面显示接单人")
    private String taskOwnerUserName;

    @ApiModelProperty("是否最终交付   0-true, 1-false")
    private Integer isFinalDeliver;

    @ApiModelProperty("执行回合数")
    private Integer excuteTime;

    @ApiModelProperty("附件URL")
    private String attachmentUrl;

    @ApiModelProperty("派工状态")
    private String taskStatus;


    @ApiModelProperty("交付数量")
    private Long quantity;

    @ApiModelProperty("实例ID")
    private String workflowInstanceId;

    @ApiModelProperty("workItemId")
    private String workItemId;

    @ApiModelProperty("审核工单URL")
    private String approveUrl;

    @ApiModelProperty("当前登陆用户id")
    private String userId;

    @ApiModelProperty("当前页码")
    private Integer page;

    @ApiModelProperty("当前页面数据量")
    private Integer pageSize;

    @ApiModelProperty("表单名称")
    private String formName;
}
