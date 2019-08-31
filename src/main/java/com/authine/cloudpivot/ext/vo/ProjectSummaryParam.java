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

    @ApiModelProperty("项目状态:项目进行中（doing）、项目取消（cancel）、项目结束（done）")
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

    @ApiModelProperty("签约主体")
    private String legalEntity;

    public String toString(){
        StringBuilder sb = new StringBuilder("PageInfo{");
        sb.append("pageNum=").append(super.getPageNum());
        sb.append(", pageSize=").append(super.getPageSize());
        sb.append(", jobCode=").append(this.jobCode);
        sb.append(", projectName=").append(this.projectName);
        sb.append(", bu=").append(this.bu);
        sb.append(", clientGroup=").append(this.clientGroup);
        sb.append(", clientBrand=").append(this.clientBrand);
        sb.append(", projectType=").append(this.projectType);
        sb.append(", projectStartDate=").append(this.projectStartDate);
        sb.append(", projectEndDate=").append(this.projectEndDate);
        sb.append(", projectStatus=").append(this.projectStatus);
        sb.append(", legalEntity=").append(this.legalEntity);
        sb.append('}');
        return sb.toString();
    }

}
