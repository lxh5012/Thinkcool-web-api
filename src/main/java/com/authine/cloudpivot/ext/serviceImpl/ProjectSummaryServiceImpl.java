package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ProjectSummaryMapper;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.utils.ProjectStatusEnum;
import com.authine.cloudpivot.ext.vo.*;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            projectSummaryVO.setCommercialFlag(Boolean.TRUE);
            if(StringUtils.isNotBlank(projectSummaryVO.getJobCode())&& ProjectStatusEnum.doing.name().equals(projectSummaryVO.getProjectStatus())){

                projectSummaryVO.setVendorContractFlag(Boolean.TRUE);
                projectSummaryVO.setClientContractFlag(Boolean.TRUE);
            }
            projectSummaryVO.setProjectStatusView(ProjectStatusEnum.valueOf(projectSummaryVO.getProjectStatus()).getValue());
            projectSummaryVO.setClientPayFlag(Boolean.TRUE);
            if(!ProjectStatusEnum.doing.name().equals(projectSummaryVO.getProjectStatus())){
                projectSummaryVO.setClientPayFlag(Boolean.FALSE);
                projectSummaryVO.setVendorPayFlag(Boolean.FALSE);
            }
            projectSummaryVO.setProfitCommercialUrl(getProjectSummaryFormUrl(projectSummaryVO.getWorkItemId(),projectSummaryVO.getInstanceId()));
        }

        PageInfo<ProjectSummaryVO> projectSummaryVOPageInfo = new PageInfo<>(projectSummaryVOList);
        PageResult pageResult = PageUtils.getPageResult(projectSummaryVOPageInfo);
        return pageResult ;
    }


    public String getProjectSummaryFormUrl(String workitemId,String workflowInstanceId){
        String ip = "47.103.123.171";
        StringBuffer fromUrl = new StringBuffer();
        fromUrl.append("http://");
        fromUrl.append(ip);
        fromUrl.append("/form/detail?");
        fromUrl.append("workitemId=").append(workitemId);
        fromUrl.append("&workflowInstanceId=").append(workflowInstanceId);
        fromUrl.append("&return=/workflow-center/my-unfinished-workitem");
        return fromUrl.toString();
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
