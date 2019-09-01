package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.VendorContractMapper;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.VendorContractVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for(VendorContractVO vendorContractVO:vendorContractList){
            vendorContractVO.setFormUrl(getFormUrl(vendorContractVO.getId()));
        }
        PageInfo<VendorContractVO> vendorContractVOPageInfo = new PageInfo<>(vendorContractList);
        PageResult pageResult = PageUtils.getPageResult(vendorContractVOPageInfo);
        return pageResult;

    }
    private String getFormUrl(String id){
        String ip = "47.103.123.171";
        StringBuffer fromUrl = new StringBuffer();
        fromUrl.append("http://");
        fromUrl.append(ip);
        fromUrl.append("/form/detail?");
        fromUrl.append("sheetCode=VendorContract");
        fromUrl.append("&objectId=").append(id);
        fromUrl.append("&schemaCode=VendorContract");
        fromUrl.append("&return=/application/ProjectSummary/application-list/VendorContract?parentId=2c93208b6c9e0bc6016c9e36d7ac0011");
        fromUrl.append("&code=VendorContract");
        fromUrl.append("&openMode");
        fromUrl.append("&pcUrl");
        return fromUrl.toString();
    }
}
