package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import com.authine.cloudpivot.ext.vo.ClientPaymentVO;
import com.authine.cloudpivot.ext.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientPaymentService {
    public List<ClientPaymentVO> getClientPaymentList(@Param("queryClientPayment") QueryClientPayment queryClientPaymentt);
}
