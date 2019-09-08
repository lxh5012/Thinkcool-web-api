package com.authine.cloudpivot.ext.queryVo;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class QueryJobCode extends PageInfo {
    //jobcode编码
    private BigDecimal id;
    //唯一标识bizobjectid
    private String bizobjectid;
}
