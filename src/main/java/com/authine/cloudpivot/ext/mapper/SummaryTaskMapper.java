package com.authine.cloudpivot.ext.mapper;


import cn.hutool.db.PageResult;
import com.authine.cloudpivot.engine.component.query.api.helper.PageableImpl;
import com.authine.cloudpivot.ext.vo.DeliverableTaskVO;
import com.authine.cloudpivot.ext.vo.SummaryTaskModel;
import com.authine.cloudpivot.ext.vo.TaskDetialVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  SummaryTaskMapper {

    public List<SummaryTaskModel> queryProjectTask(SummaryTaskModel summaryTaskModel);

    public List<DeliverableTaskVO> queryDeliverableTask(DeliverableTaskVO deliverableTaskVO);

    public List<TaskDetialVO> queryTaskDetial(TaskDetialVO taskDetialVO);

}
