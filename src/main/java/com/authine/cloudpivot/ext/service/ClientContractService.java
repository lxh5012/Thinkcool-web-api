package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.vo.PageResult;
import org.apache.ibatis.annotations.Param;

public interface ClientContractService {


    public PageResult getClientContractList(QueryClientContract queryClientContract);

}
