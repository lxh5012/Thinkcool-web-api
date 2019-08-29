package com.authine.cloudpivot.ext.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DeliverableTaskVO {

    //项目代码
    private String jobCode;

    //项目名称
    private String projectName;

    //交付描述
    private String deliverableDescription;

    //交付品类
    private String deliverableCategory;

    //工单数量
    private Integer taskNumber;

    //工单完成数
    private Integer finishNumber;

    //工单未完成数
    private Integer unfinishNumber;

    //交付时间
    private Date deadline;

    //当前登陆用户id
    private String userId;

    //当前页码
    private Integer page;

    //当前页面数据量
    private Integer pageSize;

    //表单名称
    private String formName;

}
