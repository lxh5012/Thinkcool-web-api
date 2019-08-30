package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.SummaryTaskMapper;
import com.authine.cloudpivot.ext.service.SummaryTaskService;
import com.authine.cloudpivot.ext.vo.DeliverableTaskVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SummaryTaskVO;
import com.authine.cloudpivot.ext.vo.TaskDetialVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SummaryTaskServiceImpl implements SummaryTaskService {

    @Autowired
    private SummaryTaskMapper summaryTaskMapper;

    @Override
    public PageResult queryProjectTask(SummaryTaskVO summaryTaskVO) {
        int pageNum = summaryTaskVO.getPage();
        int pageSize = summaryTaskVO.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<SummaryTaskVO> summaryList = summaryTaskMapper.queryProjectTask(summaryTaskVO);
        PageInfo<SummaryTaskVO> summaryPage = new PageInfo<>(summaryList);
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

    @Override
    public PageResult queryTaskDetial(TaskDetialVO taskDetialVO) {
        int pageNum =taskDetialVO.getPage();
        int pageSize = taskDetialVO.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<TaskDetialVO> taskDetialList = summaryTaskMapper.queryTaskDetial(taskDetialVO);
        PageInfo<TaskDetialVO> taskDetialPage = new PageInfo<>(taskDetialList);
        return PageUtils.getPageResult(taskDetialPage);
    }
}
