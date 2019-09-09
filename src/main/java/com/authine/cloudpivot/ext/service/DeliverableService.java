package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.vo.DeliverableContract;
import com.authine.cloudpivot.ext.vo.DeliverableVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeliverableService {
    public DeliverableVO getDeliverableList();

    public PageResult queryDeliverables(QueryDeliverable queryDeliverable);

    public int addContractRelation(@Param("deliverableContracts") List<DeliverableContract> deliverableContracts);
}
