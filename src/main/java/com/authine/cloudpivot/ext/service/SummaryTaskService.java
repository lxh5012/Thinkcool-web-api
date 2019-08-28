package com.authine.cloudpivot.ext.service;


import com.authine.cloudpivot.engine.component.query.api.helper.PageableImpl;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SummaryTaskModel;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SummaryTaskService {


    public PageResult queryProjectTask(SummaryTaskModel summaryTaskModel);

}
