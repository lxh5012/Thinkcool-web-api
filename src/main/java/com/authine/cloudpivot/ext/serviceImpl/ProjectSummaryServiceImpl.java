package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.mapper.ProjectSummaryMapper;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="projectSummaryServiceImpl")
public class ProjectSummaryServiceImpl implements IProjectSummaryService {
    @Autowired
    public ProjectSummaryMapper projectSummaryMapper;
    @Override
    public UserVO getUserVo() {
        return projectSummaryMapper.getUserVo();
    }
}
