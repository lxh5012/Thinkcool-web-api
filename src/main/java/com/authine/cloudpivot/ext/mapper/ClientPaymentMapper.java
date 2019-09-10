package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.vo.ClientPaymentFinVO;
import com.authine.cloudpivot.ext.vo.ClientPaymentVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientPaymentMapper {
    public List<ClientPaymentVO> getClientPaymentList(@Param("queryClientPayment") QueryClientPayment queryClientPayment);

    public List<ClientPaymentFinVO> getClientPaymentFinList(QueryClientPayment queryClientPaymentt);
}
