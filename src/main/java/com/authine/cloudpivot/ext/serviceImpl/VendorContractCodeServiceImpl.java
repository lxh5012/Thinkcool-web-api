package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.mapper.VendorContractCodeMapper;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContractCode;
import com.authine.cloudpivot.ext.service.VendorContractCodeService;
import com.authine.cloudpivot.ext.vo.VendorContractCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "vendorContractCodeServiceImpl")
public class VendorContractCodeServiceImpl implements VendorContractCodeService {
    @Autowired
    public VendorContractCodeMapper vendorContractCodeMapper;


    @Override
    public VendorContractCodeVO getContractCode1() {
        VendorContractCodeVO vendorContractCodeVO = vendorContractCodeMapper.getContractCode1();
        return vendorContractCodeVO;
    }

    @Override
    public int insertBizObjectid1(QueryVendorContractCode queryVendorContractCode) {
        return vendorContractCodeMapper.insertBizObjectid1(queryVendorContractCode);
    }

    @Override
    public VendorContractCodeVO getContractCode2() {
        VendorContractCodeVO vendorContractCodeVO = vendorContractCodeMapper.getContractCode2();
        return vendorContractCodeVO;
    }

    @Override
    public int insertBizObjectid2(QueryVendorContractCode queryVendorContractCode) {
        return vendorContractCodeMapper.insertBizObjectid2(queryVendorContractCode);
    }

    @Override
    public VendorContractCodeVO getContractCode3() {
        VendorContractCodeVO vendorContractCodeVO = vendorContractCodeMapper.getContractCode3();
        return vendorContractCodeVO;
    }

    @Override
    public int insertBizObjectid3(QueryVendorContractCode queryVendorContractCode) {
        return vendorContractCodeMapper.insertBizObjectid3(queryVendorContractCode);
    }
}
