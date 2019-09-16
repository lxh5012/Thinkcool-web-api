package com.authine.cloudpivot.ext.queryVo;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class QueryVendorContractCode extends PageInfo {

    @ApiModelProperty("返回的id编码")
    private BigDecimal id;
    @ApiModelProperty("对应的bizObjectId")
    private String bizobjectid;


}
