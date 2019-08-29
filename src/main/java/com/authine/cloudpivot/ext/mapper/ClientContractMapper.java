package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientContractMapper {

    List<ClientContractVO> getClientContractList(@Param("queryClientContract") QueryClientContract queryClientContract);

}
