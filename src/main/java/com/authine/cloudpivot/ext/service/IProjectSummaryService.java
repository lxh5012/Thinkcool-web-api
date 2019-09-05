package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
import com.authine.cloudpivot.ext.vo.UserVO;

import java.util.List;

public interface IProjectSummaryService {
    public UserVO getUserVo();

    public List<ProjectSummaryVO> queryProjectSummary();

    public PageResult queryProjectSummaryPage(ProjectSummaryParam projectSummaryParam);

    public int updateProjectStatus(ProjectSummaryParam projectSummaryParam);

    public int updateVendorPayFlag(ProjectSummaryParam projectSummaryParam);

    public int updateClientPayFlag(ProjectSummaryParam projectSummaryParam);

    public ProjectSummaryVO getProjectSummaryInfo(ProjectSummaryParam projectSummaryParam);
}
