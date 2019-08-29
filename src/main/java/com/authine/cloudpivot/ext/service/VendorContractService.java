package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.vo.PageResult;
import org.apache.ibatis.annotations.Param;

public interface VendorContractService {


    public PageResult getVendorContractList(@Param("queryVendorContract") QueryVendorContract queryVendorContract);

}
