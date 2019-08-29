package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.vo.VendorContractVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorContractMapper {

    public List<VendorContractVO> getVendorContractList(@Param("queryVendorContract") QueryVendorContract queryVendorContract);

}
