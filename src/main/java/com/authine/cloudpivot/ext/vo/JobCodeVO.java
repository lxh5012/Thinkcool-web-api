package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class JobCodeVO {


    @ApiModelProperty("返回的jobCode编码")
    private BigDecimal id;
    @ApiModelProperty("对应的bizObjectId")
    private String bizObjectId;
}
