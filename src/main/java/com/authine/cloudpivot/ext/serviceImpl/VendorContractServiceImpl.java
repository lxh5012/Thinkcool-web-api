package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.VendorContractMapper;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.utils.ThinkoolProjectUtils;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.VendorContractVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VendorContractServiceImpl implements VendorContractService {

    @Autowired
    private VendorContractMapper vendorContractMapper;


    @Override
    public PageResult getVendorContractList(QueryVendorContract queryVendorContract) {
        int pageNum = queryVendorContract.getPageNum() == 0?1:queryVendorContract.getPageNum();
        int pageSize = queryVendorContract.getPageSize() == 0?10:queryVendorContract.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<VendorContractVO> vendorContractList = vendorContractMapper.getVendorContractList(queryVendorContract);
        List<VendorContractVO> list = new ArrayList<VendorContractVO>();
        for (int i = 0;i < vendorContractList.size();i++){
            VendorContractVO vendorContractVO = vendorContractList.get(i);
            if (!"".equals(vendorContractVO.getJobcode())){
                list.add(vendorContractVO);
            }
            vendorContractVO.setProfitCommercialUrl(ThinkoolProjectUtils.getWoritemUrl(vendorContractVO.getWorkItemId(),vendorContractVO.getInstanceId()));
        }
        PageInfo<VendorContractVO> vendorContractVOPageInfo = new PageInfo<>(list);
        PageResult pageResult = PageUtils.getPageResult(vendorContractVOPageInfo);
        return pageResult;

    }



}
