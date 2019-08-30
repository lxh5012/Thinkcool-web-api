package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ClientPaymentMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.service.ClientPaymentService;
import com.authine.cloudpivot.ext.vo.ClientPaymentVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="clientPaymentServiceImpl")
public class ClientPaymentServiceImpl implements ClientPaymentService {
    @Autowired
    public ClientPaymentMapper clientPaymentMapper;

    @Override
    public PageResult getClientPaymentList(QueryClientPayment queryClientPayment) {
        int pageNum =queryClientPayment.getPage();
        int pageSize = queryClientPayment.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ClientPaymentVO> clientPaymentList = clientPaymentMapper.getClientPaymentList(queryClientPayment);
        PageInfo<ClientPaymentVO> clientPaymentVOVOPageInfo = new PageInfo<>(clientPaymentList);
        return PageUtils.getPageResult(clientPaymentVOVOPageInfo);
    }
}
