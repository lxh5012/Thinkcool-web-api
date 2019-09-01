package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtherLegalDocumentVO {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("文件类型")
    private String documentType;

    @ApiModelProperty("文件名称")
    private String documentName;

    @ApiModelProperty("文件状态")
    private String documentStatus;

    @ApiModelProperty("备注")
    private String remark;


}
