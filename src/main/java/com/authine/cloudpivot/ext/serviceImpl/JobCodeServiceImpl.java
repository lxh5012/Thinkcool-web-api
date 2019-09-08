package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.mapper.JobCodeMapper;
import com.authine.cloudpivot.ext.queryVo.QueryJobCode;
import com.authine.cloudpivot.ext.service.JobCodeService;
import com.authine.cloudpivot.ext.vo.JobCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="jobCodeServiceImpl")
public class JobCodeServiceImpl implements JobCodeService {
    @Autowired
    public JobCodeMapper jobCodeMapper;


    @Override
    public JobCodeVO  getJobCode(String bizObjectid) {
        JobCodeVO jobCodeVO = jobCodeMapper.getJobCode(bizObjectid);
        return jobCodeVO;
    }

    @Override
    public int insertBizObjectid(QueryJobCode queryJobCode) {
        return jobCodeMapper.insertBizObjectid(queryJobCode);
    }

}
