package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class DeliverableTaskVO {

    @ApiModelProperty("后台原来保存jobCode对应的ID")
    private String jobId;

    @ApiModelProperty("项目代码")
    private String jobCode;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("交付描述")
    private String deliverableDescription;

    @ApiModelProperty("交付品类")
    private String deliverableCategory;

    @ApiModelProperty("交付数量")
    private Long quantity;

    @ApiModelProperty("工单数量")
    private Integer taskNumber;

    @ApiModelProperty("工单完成数")
    private Integer finishNumber;

    @ApiModelProperty("工单未完成数")
    private Integer unfinishNumber;

    @ApiModelProperty("交付时间")
    private java.util.Date deadline;

    @ApiModelProperty("前端页面显示交付时间")
    private String deadlineShow;

    @ApiModelProperty("交付时间入参START")
    private String deadlineStart;

    @ApiModelProperty("交付时间入参END")
    private String deadlineEND;

    @ApiModelProperty("交付数量")
    private String deliverableQuantity;

    @ApiModelProperty("当前登陆用户id")
    private String userId;

    @ApiModelProperty("当前页码")
    private Integer page;

    @ApiModelProperty("当前页面数据量")
    private Integer pageSize;

    @ApiModelProperty("表单名称")
    private String formName;

    @ApiModelProperty("发起工单")
    private String dispatchUrl;

}
