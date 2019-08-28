package com.authine.cloudpivot.ext.vo;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ClientContractVO {

    //项目代码
    private String jobcode;

    //项目名称
    private String projectName;

    //合同类型
    private String contractType;

    //客户合同版本
    private String clientContractVersion;

    //客户合同编码
    private String clientContractCode;

    //客户合同开始时间
    private String clientContractStarttime;

    //客户合同结束时间
    private String clientContractEndtime;

    //客户合同状态
    private String clientContractStatus;

    //合同金额
    private Long contractValue;



}
