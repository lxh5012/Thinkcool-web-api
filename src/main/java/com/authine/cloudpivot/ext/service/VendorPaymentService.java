package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryVendorPayment;
import com.authine.cloudpivot.ext.vo.PageResult;
import org.apache.ibatis.annotations.Param;

public interface VendorPaymentService {
    public PageResult getVendorPaymentList(@Param("queryVendorPayment") QueryVendorPayment queryVendorPayment);
    public PageResult getStagePaymentInfoList(QueryVendorPayment queryVendorPayment);
}
