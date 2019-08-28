package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.mapper.SummaryTaskMapper;
import com.authine.cloudpivot.ext.service.SummaryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryTaskServiceImpl implements SummaryTaskService {

    @Autowired
    private SummaryTaskMapper summaryTaskMapper;

    @Override
    public Integer getcount(){
        return summaryTaskMapper.getcount();
    }
}
