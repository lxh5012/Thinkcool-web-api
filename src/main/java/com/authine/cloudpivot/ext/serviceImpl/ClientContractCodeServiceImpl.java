package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.mapper.ClientContractCodeMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientContractCode;
import com.authine.cloudpivot.ext.service.ClientContractCodeService;
import com.authine.cloudpivot.ext.vo.ClientContractCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "clientContractCodeServiceImpl")
public class ClientContractCodeServiceImpl implements ClientContractCodeService {
    @Autowired
    public ClientContractCodeMapper clientContractCodeMapper;


    @Override
    public ClientContractCodeVO getContractCode1() {
        ClientContractCodeVO clientContractCodeVO = clientContractCodeMapper.getContractCode1();
        return clientContractCodeVO;
    }

    @Override
    public int insertBizObjectid1(QueryClientContractCode queryClientContractCode) {
        return clientContractCodeMapper.insertBizObjectid1(queryClientContractCode);
    }

    @Override
    public ClientContractCodeVO getContractCode2() {
        ClientContractCodeVO clientContractCodeVO = clientContractCodeMapper.getContractCode2();
        return clientContractCodeVO;
    }

    @Override
    public int insertBizObjectid2(QueryClientContractCode queryClientContractCode) {
        return clientContractCodeMapper.insertBizObjectid2(queryClientContractCode);
    }

    @Override
    public ClientContractCodeVO getContractCode3() {
        ClientContractCodeVO clientContractCodeVO = clientContractCodeMapper.getContractCode3();
        return clientContractCodeVO;
    }

    @Override
    public int insertBizObjectid3(QueryClientContractCode queryClientContractCode) {
        return clientContractCodeMapper.insertBizObjectid3(queryClientContractCode);
    }
}
