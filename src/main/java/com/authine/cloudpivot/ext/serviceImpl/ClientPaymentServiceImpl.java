package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.mapper.ClientPaymentMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.service.ClientPaymentService;
import com.authine.cloudpivot.ext.vo.ClientPaymentVO;
import com.authine.cloudpivot.ext.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="clientPaymentServiceImpl")
public class ClientPaymentServiceImpl implements ClientPaymentService {
    @Autowired
    public ClientPaymentMapper clientPaymentMapper;

    @Override
    public List<ClientPaymentVO> getClientPaymentList(QueryClientPayment queryClientPayment) {
        return clientPaymentMapper.getClientPaymentList(queryClientPayment);
    }
}
