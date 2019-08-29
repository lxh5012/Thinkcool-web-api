package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ProjectSummaryMapper;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="projectSummaryServiceImpl")
public class ProjectSummaryServiceImpl implements IProjectSummaryService {
    @Autowired
    public ProjectSummaryMapper projectSummaryMapper;
    @Override
    public UserVO getUserVo() {
        return projectSummaryMapper.getUserVo();
    }

    @Override
    public List<ProjectSummaryVO> queryProjectSummary() {
        return projectSummaryMapper.queryProjectSummary(null);
    }

    @Override
    public PageResult queryProjectSummaryPage(ProjectSummaryParam projectSummaryParam) {
        int pageNum = projectSummaryParam.getPageNum();
        int pageSize = projectSummaryParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectSummaryVO> projectSummaryVOList = projectSummaryMapper.queryProjectSummary(projectSummaryParam);
        for(ProjectSummaryVO projectSummaryVO:projectSummaryVOList){
            if(StringUtils.isNotBlank(projectSummaryVO.getJobCode())){
                projectSummaryVO.setCommercialFlag(Boolean.TRUE);
                projectSummaryVO.setVendorContractFlag(Boolean.TRUE);
                projectSummaryVO.setClientContractFlag(Boolean.TRUE);
            }
        }
        PageInfo<ProjectSummaryVO> projectSummaryVOPageInfo = new PageInfo<>(projectSummaryVOList);
        return PageUtils.getPageResult(projectSummaryVOPageInfo);
    }

}
