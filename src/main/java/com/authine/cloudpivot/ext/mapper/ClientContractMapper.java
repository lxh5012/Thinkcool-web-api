package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import com.authine.cloudpivot.ext.vo.SelectVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientContractMapper {

    public List<ClientContractVO> getClientContractList(QueryClientContract queryClientContract);

    public List<ClientContractVO> getClientContractById(List<String> ids);

    public List<SelectVO> getClientContractCodeList(QueryClientContract queryClientContract);
}
