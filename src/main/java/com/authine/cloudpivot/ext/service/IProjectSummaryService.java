package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
import com.authine.cloudpivot.ext.vo.UserVO;

import java.util.List;

public interface IProjectSummaryService {

    public List<ProjectSummaryVO> queryProjectSummary();

    /**
     * 分页查询项目列表
     * @param projectSummaryParam
     * @return
     */

    public PageResult queryProjectSummaryPage(ProjectSummaryParam projectSummaryParam);

    /**
     * 更新项目状态
     * @param projectSummaryParam
     * @return
     */
    public int updateProjectStatus(ProjectSummaryParam projectSummaryParam);

    /**
     * 更新供应商付款标记
     * @param projectSummaryParam
     * @return
     */
    public int updateVendorPayFlag(ProjectSummaryParam projectSummaryParam);

    /**
     * 更新客户收款标记
     * @param projectSummaryParam
     * @return
     */
    public int updateClientPayFlag(ProjectSummaryParam projectSummaryParam);

    /**
     * 查询项目信息
     * @param projectSummaryParam
     * @return
     */

    public ProjectSummaryVO getProjectSummaryInfo(ProjectSummaryParam projectSummaryParam);

    /**
     * 根据项目id获取代码信息
     * @param projectSummaryParam
     * @return
     */
    public ProjectSummaryVO getWorItemInfoByProjectId(ProjectSummaryParam projectSummaryParam);
}
