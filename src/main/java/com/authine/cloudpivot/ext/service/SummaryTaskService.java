package com.authine.cloudpivot.ext.service;


import com.authine.cloudpivot.ext.vo.DeliverableTaskVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SummaryTaskVO;
import com.authine.cloudpivot.ext.vo.TaskDetialVO;

public interface SummaryTaskService {


    public PageResult queryProjectTask(SummaryTaskVO summaryTaskVO);

    public PageResult queryDeliverableTask(DeliverableTaskVO deliverableTaskVO);

    public PageResult queryTaskDetial(TaskDetialVO taskDetialVO);
}
