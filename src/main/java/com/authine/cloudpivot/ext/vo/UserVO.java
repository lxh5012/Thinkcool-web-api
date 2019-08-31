package com.authine.cloudpivot.ext.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserVO {


    private String name;

    private String userId;

    @ApiModelProperty("云枢选人控件后台返回userId")
    private String id;

    @ApiModelProperty("云枢选人控件后台返回type")
    private String type;
}
