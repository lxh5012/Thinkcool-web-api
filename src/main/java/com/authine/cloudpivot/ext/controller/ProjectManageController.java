package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.utils.DateUtils;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.ProjectSummaryParam;
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
import org.springframework.web.bind.annotation.*;

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
   @PostMapping("/queryProjectSummary")
   public ResponseResult<PageResult>  queryProjectSummary(@RequestBody ProjectSummaryParam projectSummaryParam){
      PageResult pageResult = projectSummaryServiceImpl.queryProjectSummaryPage(projectSummaryParam);
      return getOkResponseResult( pageResult,"查询成功");
   }
}
