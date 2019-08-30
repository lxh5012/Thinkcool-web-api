package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ClientContractMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.service.ClientContractService;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientContractServiceImpl implements ClientContractService {

    @Autowired
    private ClientContractMapper clientContractMapper;


    @Override
    public PageResult getClientContractList(@Param("queryClientContract") QueryClientContract queryClientContract) {
        int pageNum =queryClientContract.getPage();
        int pageSize = queryClientContract.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ClientContractVO> clientContractList = clientContractMapper.getClientContractList(queryClientContract);
        PageInfo<ClientContractVO> clientContractVOPageInfo = new PageInfo<>(clientContractList);
        return PageUtils.getPageResult(clientContractVOPageInfo);

    }
}
