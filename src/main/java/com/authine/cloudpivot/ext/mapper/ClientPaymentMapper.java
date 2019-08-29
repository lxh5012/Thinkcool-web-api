package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.vo.ClientPaymentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientPaymentMapper {
    public List<ClientPaymentVO> getClientPaymentList(@Param("queryClientPayment") QueryClientPayment queryClientPayment);
}
