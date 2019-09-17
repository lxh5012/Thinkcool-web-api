package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SelectVO;
import com.authine.cloudpivot.ext.vo.VendorContractVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VendorContractService {


    public PageResult getVendorContractList(@Param("queryVendorContract") QueryVendorContract queryVendorContract);

    public List<SelectVO> getVendorContractCodeList(QueryVendorContract queryVendorContract);

}
