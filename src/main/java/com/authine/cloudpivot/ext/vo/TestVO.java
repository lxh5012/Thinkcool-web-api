package com.authine.cloudpivot.ext.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestVO {

    @ApiModelProperty("项目代码")
    private String jobCode;

    @ApiModelProperty("合同类型")
    private String contractType;


}
