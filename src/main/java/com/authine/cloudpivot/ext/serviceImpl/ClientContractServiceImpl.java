package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.mapper.ClientContractMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.service.ClientContractService;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientContractServiceImpl implements ClientContractService {

    @Autowired
    private ClientContractMapper clientContractMapper;


    @Override
    public List<ClientContractVO> getClientContractList(QueryClientContract queryClientContract) {
        return clientContractMapper.getClientContractList(queryClientContract);
    }
}
