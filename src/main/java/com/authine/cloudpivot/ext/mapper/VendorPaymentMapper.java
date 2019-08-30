package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryVendorPayment;
import com.authine.cloudpivot.ext.vo.VendorPaymentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorPaymentMapper {
    public List<VendorPaymentVO> getVendorPaymentList(@Param("queryVendorPayment") QueryVendorPayment queryVendorPayment);
}
