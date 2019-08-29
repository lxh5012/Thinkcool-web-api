package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.utils.DateUtils;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
import com.authine.cloudpivot.ext.vo.UserVO;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 项目管理页面接口
 */
@Api(value = "项目管理页面接口", tags = "项目管理页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/api/projectmanage")
@CustomizedOrigin(level = 0)
public class ProjectManageController  extends BaseController {
   @Autowired
   private IProjectSummaryService projectSummaryServiceImpl;
   @ApiOperation(value = "查询用户测试",notes = "查询用户测试")
   @GetMapping("/queryUser")
   public ResponseResult<UserVO>  queryUser(){
      UserVO userVo = projectSummaryServiceImpl.getUserVo();
      return getOkResponseResult( userVo,"查询成功");
   }

   @ApiOperation(value = "查询projectsummary",notes = "查询projectsummary")
   @GetMapping("/queryProjectSummary")
   public ResponseResult< List<ProjectSummaryVO>>  queryProjectSummary(){

      List<ProjectSummaryVO> projectSummaryVOList = projectSummaryServiceImpl.queryProjectSummary();
      for(ProjectSummaryVO projectSummaryVO:projectSummaryVOList){

         Date projectEndDate = DateUtils.getStringToDate( projectSummaryVO.getProjectEndDate());
         String projectEndDateStr = DateUtils.getDateToString(projectEndDate,DateUtils.XG_DATE_FORMAT);
         projectSummaryVO.setProjectEndDate(projectEndDateStr);
         Date projectStartDate = DateUtils.getStringToDate(projectSummaryVO.getProjectStartDate());
         String projectStartDateStr = DateUtils.getDateToString(projectStartDate,DateUtils.XG_DATE_FORMAT);
         projectSummaryVO.setProjectStartDate(projectStartDateStr);

         if(StringUtils.isNotBlank(projectSummaryVO.getJobCode())){
            projectSummaryVO.setCommercialFlag(Boolean.TRUE);
            projectSummaryVO.setClientContractFlag(Boolean.TRUE);
            projectSummaryVO.setVendorContractFlag(Boolean.TRUE);
         }

      }
      return getOkResponseResult( projectSummaryVOList,"查询成功");
   }
}
