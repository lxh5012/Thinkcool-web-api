package com.authine.cloudpivot.ext.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSummaryVO {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("签约主体/Contract legal entity")
    private String ContractLegalEntity;

    @ApiModelProperty("项目组/BU")
    private String BU;

    @ApiModelProperty("客户集团/Client Group")
    private String ClientGroup;

    @ApiModelProperty("客户品牌/Client Brand")
    private String ClientBrand;

    @ApiModelProperty("项目类型/Project type")
    private String ProjectType;

    @ApiModelProperty("项目开始日期/Project start date")
    private String ProjectStartDate;

    @ApiModelProperty("项目结束日期/Project end date")
    private String ProjectEndDate;

    @ApiModelProperty("项目代码/Jobcode")
    private String JobCode;

    @ApiModelProperty("项目名称/Project name")
    private String ProjectName;

    @ApiModelProperty("备注")
    private String Remark;

    @ApiModelProperty("审批意见")
    private String ApprovalOpinion;

    @ApiModelProperty("上传扫描文件")
    private String MarginAttachment;

    @ApiModelProperty("项目预期利润/Project expected margin")
    private BigDecimal ProjectExpectedMargin;

    @ApiModelProperty("项目利润/Project Margin")
    private BigDecimal ProjectMargin;

    @ApiModelProperty("项目预算/Project budget")
    private BigDecimal ProjectBudget;

    @ApiModelProperty("项目利润 &商务信息 Project profit& commercial info 操作标记")
    private Boolean commercialFlag = Boolean.FALSE;

    @ApiModelProperty("发起客户合同流程 Client Contract approval 操作标记")
    private Boolean clientContractFlag = Boolean.FALSE;

    @ApiModelProperty("发起供应商合同流程 Vendor Contract approval")
    private Boolean vendorContractFlag = Boolean.FALSE;

    @ApiModelProperty("客户收款 Client payment 操作标记")
    private Boolean clientPayFlag = Boolean.FALSE;

    @ApiModelProperty("供应商付款 Vendor payment操作标记")
    private Boolean vendorPayFlag = Boolean.FALSE;

}
