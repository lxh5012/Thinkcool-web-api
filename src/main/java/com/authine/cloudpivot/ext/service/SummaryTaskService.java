package com.authine.cloudpivot.ext.service;


import com.authine.cloudpivot.engine.component.query.api.helper.PageableImpl;
import com.authine.cloudpivot.ext.vo.DeliverableTaskVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SummaryTaskModel;
import com.authine.cloudpivot.ext.vo.TaskDetialVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SummaryTaskService {


    public PageResult queryProjectTask(SummaryTaskModel summaryTaskModel);

    public PageResult queryDeliverableTask(DeliverableTaskVO deliverableTaskVO);

    public PageResult queryTaskDetial(TaskDetialVO taskDetialVO);
}
