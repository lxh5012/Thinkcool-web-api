package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ProjectSummaryMapper;
import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.utils.ProjectStatusEnum;
import com.authine.cloudpivot.ext.utils.ThinkoolProjectUtils;
import com.authine.cloudpivot.ext.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

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
        ProjectSummaryVO projectSummaryVO =null;
        for(int i=0;i<projectSummaryVOList.size();i++){
            projectSummaryVO = projectSummaryVOList.get(i);

            if(StringUtils.isNotBlank(projectSummaryVO.getJobCode())){
                //项目审批节点控制
                projectSummaryVO.setProjectApprovalFlag(Boolean.FALSE);
            }
            if(StringUtils.isNotBlank(projectSummaryVO.getJobCode())&&
                    ProjectStatusEnum.doing.name().equals(projectSummaryVO.getProjectStatus())){
                //填写商务信息&项目利润
                projectSummaryVO.setCommercialFlag(Boolean.TRUE);
                //发起供应商合同
                projectSummaryVO.setVendorContractFlag(Boolean.TRUE);
                //发起客户合同
                projectSummaryVO.setClientContractFlag(Boolean.TRUE);
            }

            //1、编写项目利润和商务信息 and 2、客户合同结束后
            if(projectSummaryVO.getClientPayFlag() && !Objects.isNull(projectSummaryVO.getProjectMargin())){
                projectSummaryVO.setClientPayFlag(Boolean.TRUE);
            }

            //项目进行中才有 操作权限
            if(!ProjectStatusEnum.doing.name().equals(projectSummaryVO.getProjectStatus())){
                projectSummaryVO.setClientPayFlag(Boolean.FALSE);
                projectSummaryVO.setVendorPayFlag(Boolean.FALSE);
            }
            projectSummaryVO.setProjectStatusView(ProjectStatusEnum.valueOf(projectSummaryVO.getProjectStatus()).getValue());
            projectSummaryVO.setProfitCommercialUrl(ThinkoolProjectUtils.getWoritemUrl(projectSummaryVO.getWorkItemId(),projectSummaryVO.getInstanceId()));
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

    @Override
    public int updateClientPayFlag(ProjectSummaryParam projectSummaryParam) {
        return projectSummaryMapper.updateClientPayFlag(projectSummaryParam);
    }

    @Override
    public ProjectSummaryVO getProjectSummaryInfo(ProjectSummaryParam projectSummaryParam) {
        return projectSummaryMapper.getProjectSummaryInfo(projectSummaryParam);
    }

    @Override
    public ProjectSummaryVO getWorItemInfoByProjectId(ProjectSummaryParam projectSummaryParam) {
        return projectSummaryMapper.getWorItemInfoByProjectId(projectSummaryParam);
    }


}
