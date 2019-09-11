package com.authine.cloudpivot.ext.service;


import com.authine.cloudpivot.ext.vo.*;

import java.util.List;

public interface SummaryTaskService {


    public PageResult queryProjectTask(SummaryTaskVO summaryTaskVO);

    public PageResult queryDeliverableTask(DeliverableTaskVO deliverableTaskVO);

    public PageResult queryTaskDetial(TaskDetialVO taskDetialVO);

    public PageResult acceptTaskList(AcceptTaskVO acceptTaskVO);

    List queryProjectTeamBuList();
}
