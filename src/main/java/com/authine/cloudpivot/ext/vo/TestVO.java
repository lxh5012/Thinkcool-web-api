package com.authine.cloudpivot.ext.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestVO {

    @ApiModelProperty
    private String jobCode;

    @ApiModelProperty
    private String contractType;


}
