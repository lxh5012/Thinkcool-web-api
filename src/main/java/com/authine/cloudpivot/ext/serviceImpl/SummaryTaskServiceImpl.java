package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.engine.component.query.api.helper.PageableImpl;
import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.SummaryTaskMapper;
import com.authine.cloudpivot.ext.service.SummaryTaskService;
import com.authine.cloudpivot.ext.vo.DeliverableTaskVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SummaryTaskModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SummaryTaskServiceImpl implements SummaryTaskService {

    @Autowired
    private SummaryTaskMapper summaryTaskMapper;

    @Override
    public PageResult queryProjectTask(SummaryTaskModel summaryTaskModel) {
        int pageNum =summaryTaskModel.getPage();
        int pageSize = summaryTaskModel.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<SummaryTaskModel> summaryList = summaryTaskMapper.queryProjectTask(summaryTaskModel);
        PageInfo<SummaryTaskModel> summaryPage = new PageInfo<>(summaryList);
        return PageUtils.getPageResult(summaryPage);
    }

    @Override
    public PageResult queryDeliverableTask(DeliverableTaskVO deliverableTaskVO) {
        int pageNum =deliverableTaskVO.getPage();
        int pageSize = deliverableTaskVO.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<DeliverableTaskVO> deliverableTaskList = summaryTaskMapper.queryDeliverableTask(deliverableTaskVO);
        PageInfo<DeliverableTaskVO> deliverableTaskPage = new PageInfo<>(deliverableTaskList);
        return PageUtils.getPageResult(deliverableTaskPage);
    }
}
