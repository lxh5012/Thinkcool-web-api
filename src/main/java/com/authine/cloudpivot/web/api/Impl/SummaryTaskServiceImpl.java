package com.authine.cloudpivot.web.api.Impl;

import com.authine.cloudpivot.web.api.mapper.SummaryTaskMapper;
import com.authine.cloudpivot.web.api.service.SummaryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class SummaryTaskServiceImpl implements SummaryTaskService {

    @Autowired
    private SummaryTaskMapper summaryTaskMapper;

    @Override
    public Integer getcount(){
        return summaryTaskMapper.getcount();
    }
}
