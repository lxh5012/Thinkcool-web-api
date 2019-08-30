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

    @ApiModelProperty("组织")
    private String bu;

    @ApiModelProperty("客户集团")
    private String clientGroup;

    @ApiModelProperty("客户品牌")
    private String clientBrand;

    @ApiModelProperty("项目类型")
    private String projectType;

    @ApiModelProperty("项目开始时间")
    private String projectStartDate;

    @ApiModelProperty("项目结束时间")
    private String projectEndDate;


}
