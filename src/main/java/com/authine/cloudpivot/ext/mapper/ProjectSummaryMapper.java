package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
import com.authine.cloudpivot.ext.vo.UserVO;


import java.util.List;

public interface ProjectSummaryMapper {
    public UserVO getUserVo();
    public List<ProjectSummaryVO> queryProjectSummary(ProjectSummaryParam projectSummaryParam);
    public int updateProjectStatus(ProjectSummaryParam projectSummaryParam);
    public int updateVendorPayFlag(ProjectSummaryParam projectSummaryParam);
    public int updateClientPayFlag(ProjectSummaryParam projectSummaryParam);
    public ProjectSummaryVO getProjectSummaryInfo(ProjectSummaryParam projectSummaryParam);
    public ProjectSummaryVO getWorItemInfoByProjectId(ProjectSummaryParam projectSummaryParam);
}
