package com.authine.cloudpivot.ext.queryVo;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QueryOtherLegalDocument extends PageInfo {


    @ApiModelProperty("文件类型")
    private String documentType;

}
