package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
import com.authine.cloudpivot.ext.vo.UserVO;


import java.util.List;

/**
 *
 *ProjectSummaryMapper
 * @author laixh
 * @date 2019/09/01
 */
public interface ProjectSummaryMapper {

    /**
     *
     * @param projectSummaryParam
     * @return
     */
    public List<ProjectSummaryVO> queryProjectSummary(ProjectSummaryParam projectSummaryParam);

    /**
     *
     * @param projectSummaryParam
     * @return
     */
    public int updateProjectStatus(ProjectSummaryParam projectSummaryParam);

    /**
     *
     * @param projectSummaryParam
     * @return
     */
    public int updateVendorPayFlag(ProjectSummaryParam projectSummaryParam);

    /**
     *
     * @param projectSummaryParam
     * @return
     */
    public int updateClientPayFlag(ProjectSummaryParam projectSummaryParam);

    /**
     *
     * @param projectSummaryParam
     * @return
     */
    public ProjectSummaryVO getProjectSummaryInfo(ProjectSummaryParam projectSummaryParam);

    /**
     *
     * @param projectSummaryParam
     * @return
     */
    public ProjectSummaryVO getWorItemInfoByProjectId(ProjectSummaryParam projectSummaryParam);
}
