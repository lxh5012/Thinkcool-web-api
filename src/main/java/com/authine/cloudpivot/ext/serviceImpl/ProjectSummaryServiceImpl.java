package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ProjectSummaryMapper;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.utils.ProjectStatusEnum;
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
        int pageNum = projectSummaryParam.getPageNum() == 0?1:projectSummaryParam.getPageNum();
        int pageSize = projectSummaryParam.getPageSize() == 0?10:projectSummaryParam.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectSummaryVO> projectSummaryVOList = projectSummaryMapper.queryProjectSummary(projectSummaryParam);
        for(int i=0;i<projectSummaryVOList.size();i++){
            ProjectSummaryVO projectSummaryVO = projectSummaryVOList.get(i);
            if(StringUtils.isNotBlank(projectSummaryVO.getJobCode())&& ProjectStatusEnum.doing.name().equals(projectSummaryVO.getProjectStatus())){
                projectSummaryVO.setCommercialFlag(Boolean.TRUE);
                projectSummaryVO.setVendorContractFlag(Boolean.TRUE);
                projectSummaryVO.setClientContractFlag(Boolean.TRUE);
            }
            projectSummaryVO.setProjectStatusView(ProjectStatusEnum.valueOf(projectSummaryVO.getProjectStatus()).name());
            projectSummaryVO.setClientPayFlag(Boolean.TRUE);
            projectSummaryVO.setVendorPayFlag(Boolean.TRUE);
        }
        PageInfo<ProjectSummaryVO> projectSummaryVOPageInfo = new PageInfo<>(projectSummaryVOList);
        PageResult pageResult = PageUtils.getPageResult(projectSummaryVOPageInfo);
        return pageResult ;
    }

    @Override
    public int updateProjectStatus(ProjectSummaryParam projectSummaryParam) {
        return projectSummaryMapper.updateProjectStatus(projectSummaryParam);
    }

    @Override
    public int updateVendorPayFlag(ProjectSummaryParam projectSummaryParam) {
        return projectSummaryMapper.updateVendorPayFlag(projectSummaryParam);
    }


}
