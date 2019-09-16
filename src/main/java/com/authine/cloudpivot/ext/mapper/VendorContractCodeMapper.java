package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryClientContractCode;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContractCode;
import com.authine.cloudpivot.ext.vo.ClientContractCodeVO;
import com.authine.cloudpivot.ext.vo.VendorContractCodeVO;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorContractCodeMapper {

    public VendorContractCodeVO getContractCode1();

    public int insertBizObjectid1(QueryVendorContractCode queryVendorContractCode);

    public VendorContractCodeVO getContractCode2();

    public int insertBizObjectid2(QueryVendorContractCode queryVendorContractCode);

    public VendorContractCodeVO getContractCode3();

    public int insertBizObjectid3(QueryVendorContractCode queryVendorContractCode);
}
