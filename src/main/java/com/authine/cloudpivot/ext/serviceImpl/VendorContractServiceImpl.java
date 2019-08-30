package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.VendorContractMapper;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.VendorContractVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VendorContractServiceImpl implements VendorContractService {

    @Autowired
    private VendorContractMapper vendorContractMapper;


    @Override
    public PageResult getVendorContractList(@Param("queryVendorContract") QueryVendorContract queryVendorContract) {
        int pageNum =queryVendorContract.getPage();
        int pageSize = queryVendorContract.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<VendorContractVO> vendorContractList = vendorContractMapper.getVendorContractList(queryVendorContract);
        PageInfo<VendorContractVO> vendorContractVOPageInfo = new PageInfo<>(vendorContractList);
        return PageUtils.getPageResult(vendorContractVOPageInfo);

    }
}
