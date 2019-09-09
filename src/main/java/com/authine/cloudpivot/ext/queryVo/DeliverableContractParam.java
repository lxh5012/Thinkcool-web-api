package com.authine.cloudpivot.ext.queryVo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeliverableContractParam {
    private String deliverableId;
    private String type;
    private List<ContractFinVO> contractFinVOS;
}
