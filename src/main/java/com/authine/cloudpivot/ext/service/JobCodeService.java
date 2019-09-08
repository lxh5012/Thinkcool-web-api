package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.queryVo.QueryJobCode;
import com.authine.cloudpivot.ext.vo.DeliverableVO;
import com.authine.cloudpivot.ext.vo.JobCodeVO;
import com.authine.cloudpivot.ext.vo.PageResult;

public interface JobCodeService {
    public JobCodeVO   getJobCode();
    public int insertBizObjectid(QueryJobCode queryJobCode);
}
