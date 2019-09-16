package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryClientContractCode;
import com.authine.cloudpivot.ext.vo.ClientContractCodeVO;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientContractCodeMapper {

    public ClientContractCodeVO getContractCode1();

    public int insertBizObjectid1(QueryClientContractCode queryClientContractCode);

    public ClientContractCodeVO getContractCode2();

    public int insertBizObjectid2(QueryClientContractCode queryClientContractCode);

    public ClientContractCodeVO getContractCode3();

    public int insertBizObjectid3(QueryClientContractCode queryClientContractCode);
}
