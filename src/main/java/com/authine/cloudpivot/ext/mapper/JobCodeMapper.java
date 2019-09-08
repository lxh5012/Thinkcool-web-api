package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryJobCode;
import com.authine.cloudpivot.ext.vo.JobCodeVO;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCodeMapper {
    public JobCodeVO   getJobCode(String bizObjectid);
    public int insertBizObjectid(QueryJobCode queryJobCode);
}
