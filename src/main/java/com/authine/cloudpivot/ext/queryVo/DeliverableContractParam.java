package com.authine.cloudpivot.ext.queryVo;

import com.authine.cloudpivot.ext.vo.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeliverableContractParam {
    private String deliverableId;
    private String type;
    private List<ContractFinVO> contractFinVOS;
    private List<ClientContractVO> clientContractVOS;
    private List<VendorContractVO> vendorContractVOS;
    private List<ClientPaymentFinVO> clientPaymentVOS;
    private List<StagePaymentVO> vendorPaymentVOS;

}
