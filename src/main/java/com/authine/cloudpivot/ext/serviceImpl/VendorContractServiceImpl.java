package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.mapper.VendorContractMapper;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.vo.VendorContractVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VendorContractServiceImpl implements VendorContractService {

    @Autowired
    private VendorContractMapper vendorContractMapper;


    @Override
    public List<VendorContractVO> getVendorContractList(@Param("queryVendorContract")QueryVendorContract queryVendorContract) {
        return vendorContractMapper.getVendorContractList(queryVendorContract);
    }
}
