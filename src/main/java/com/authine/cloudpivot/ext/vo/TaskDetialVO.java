package com.authine.cloudpivot.ext.vo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskDetialVO {

    //后台原来保存jobCode对应的ID
    private String jobId;

    //前台用来显示
    private String jobCode;

    //交付描述
    private String deliverableDescription;

    //交付品类
    private String deliverableCategory;

    //任务发起人
    private String taskDistributor;

    //接单人
    private String taskOwner;

    //是否最终交付   0-true, 1-false
    private Integer isFinalDeliver;

    //执行回合数
    private Integer excuteTime;

    //附件URL
    private String attachmengUrl;


    //当前登陆用户id
    private String userId;

    //当前页码
    private Integer page;

    //当前页面数据量
    private Integer pageSize;

    //表单名称
    private String formName;


}
