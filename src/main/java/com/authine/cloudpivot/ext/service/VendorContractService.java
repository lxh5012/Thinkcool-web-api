package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import com.authine.cloudpivot.ext.vo.VendorContractVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VendorContractService {


    List<VendorContractVO> getVendorContractList(@Param("queryVendorContract") QueryVendorContract queryVendorContract);

}
