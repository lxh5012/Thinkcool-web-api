package com.authine.cloudpivot.ext.utils;

public enum ProjectStatusEnum {
    doing("进行中"), done("已结束"),cancel("已取消");


    private String value;

    ProjectStatusEnum(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
