package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SelectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientContractService {


    public PageResult getClientContractList(QueryClientContract queryClientContract);

    public List<ClientContractVO> getClientContractById(List<String> ids);

    public List<SelectVO> getClientContractCodeList(QueryClientContract queryClientContract);

}
