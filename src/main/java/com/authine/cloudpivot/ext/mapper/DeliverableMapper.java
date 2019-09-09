package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverableMapper {
    public DeliverableVO getDeliverableList();

    public List<DeliverableVO> queryDeliverables(QueryDeliverable queryDeliverable);

    public int addContractRelation(List<DeliverableContract> deliverableContracts);

    public int addClientContractInfo(List<ClientContractVO> clientContractVOList);

    public int addVendorContractInfo(List<VendorContractVO> vendorContractVOList);

    public int addClientPaymentInfo(List<ClientPaymentVO> clientPaymentVOList);

    public int addVendorPaymentInfo(List<VendorPaymentVO> vendorPaymentVOList);
}
