package com.authine.cloudpivot.ext.queryVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtivateActivityVO {
    @ApiModelProperty("活动节点")
    private String activityCode;
    @ApiModelProperty("流程实例ID")
    private String workflowInstanceId;

}
