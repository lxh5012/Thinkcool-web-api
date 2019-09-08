package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ClientPaymentMapper;
import com.authine.cloudpivot.ext.mapper.DeliverableMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.service.ClientPaymentService;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.utils.ProjectStatusEnum;
import com.authine.cloudpivot.ext.utils.ThinkoolProjectUtils;
import com.authine.cloudpivot.ext.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service(value="deliverableServiceImpl")
public class DeliverableServiceImpl implements DeliverableService {
    @Autowired
    public DeliverableMapper deliverableMapper;


    @Override
    public DeliverableVO getDeliverableList() {
        DeliverableVO list = deliverableMapper.getDeliverableList();
        return list;
    }

    @Override
    public PageResult queryDeliverables(QueryDeliverable queryDeliverable) {
        int pageNum = queryDeliverable.getPageNum() == 0?1:queryDeliverable.getPageNum();
        int pageSize = queryDeliverable.getPageSize() == 0?10:queryDeliverable.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<DeliverableVO> queryDeliverableVOList = deliverableMapper.queryDeliverables(queryDeliverable);
        PageInfo<DeliverableVO> queryDeliverableVOPageInfo =new PageInfo<>(queryDeliverableVOList);
        PageResult pageResult = PageUtils.getPageResult(queryDeliverableVOPageInfo);
        return pageResult ;
    }

    @Override
    public int addContractRelation(List<DeliverableContract> deliverableContracts) {
        return 0;
    }


}
