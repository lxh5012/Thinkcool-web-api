package com.authine.cloudpivot.ext.mapper;


import com.authine.cloudpivot.ext.vo.AcceptTaskVO;
import com.authine.cloudpivot.ext.vo.DeliverableTaskVO;
import com.authine.cloudpivot.ext.vo.SummaryTaskVO;
import com.authine.cloudpivot.ext.vo.TaskDetialVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  SummaryTaskMapper {

    public List<SummaryTaskVO> queryProjectTask(SummaryTaskVO summaryTaskVO);

    public List<DeliverableTaskVO> queryDeliverableTask(DeliverableTaskVO deliverableTaskVO);

    public List<TaskDetialVO> queryTaskDetial(TaskDetialVO taskDetialVO);

    public List<AcceptTaskVO> acceptTaskList(AcceptTaskVO acceptTaskVO);

}
