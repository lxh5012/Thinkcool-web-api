package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientContractService {


    List<ClientContractVO> getClientContractList(@Param("queryClientContract") QueryClientContract queryClientContract);

}
