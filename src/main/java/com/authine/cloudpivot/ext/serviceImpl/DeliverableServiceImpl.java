package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ClientPaymentMapper;
import com.authine.cloudpivot.ext.mapper.DeliverableMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.service.ClientPaymentService;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.vo.ClientPaymentVO;
import com.authine.cloudpivot.ext.vo.DeliverableVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="deliverableServiceImpl")
public class DeliverableServiceImpl implements DeliverableService {
    @Autowired
    public DeliverableMapper deliverableMapper;


    @Override
    public DeliverableVO getDeliverableList() {
        DeliverableVO list = deliverableMapper.getDeliverableList();
        return list;
    }
}
