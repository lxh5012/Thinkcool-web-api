package com.authine.cloudpivot.ext.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 下拉框选择通用类
 * @author laixh
 * @date 2019/09/17
 */
@Getter
@Setter
public class SelectVO {
    @ApiModelProperty("下拉框id")
    private String id;
    @ApiModelProperty("下拉框显示")
    private String name;
    @Override
    public String toString(){
        return "id"+id+",name"+name;
    }
}
