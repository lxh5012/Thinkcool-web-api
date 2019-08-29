package com.authine.cloudpivot.ext.vo;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectSummaryParam extends PageInfo {

    @ApiModelProperty("项目代码")
    private String jobCode;
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("项目状态")
    private String projectStatus;

}
