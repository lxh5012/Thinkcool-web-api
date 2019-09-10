package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.vo.PageResult;
import org.apache.ibatis.annotations.Param;

public interface ClientPaymentService {
    public PageResult getClientPaymentList(@Param("queryClientPayment") QueryClientPayment queryClientPaymentt);
    public PageResult getClientPaymentFinList( QueryClientPayment queryClientPaymentt);
}
