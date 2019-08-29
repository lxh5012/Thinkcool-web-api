package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
import com.authine.cloudpivot.ext.vo.UserVO;

import java.util.List;

public interface IProjectSummaryService {
    public UserVO getUserVo();

    public List<ProjectSummaryVO> queryProjectSummary();
}
