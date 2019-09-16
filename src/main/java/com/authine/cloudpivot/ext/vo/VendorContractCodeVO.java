package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class VendorContractCodeVO {


    @ApiModelProperty("返回的jobCode编码")
    private int id;
    @ApiModelProperty("对应的bizObjectId")
    private String bizObjectId;
}
