package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
import com.authine.cloudpivot.ext.vo.UserVO;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


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
   public ResponseResult<UserVO>  queryUser() throws UnsupportedEncodingException {
      String str = "http://47.103.123.171/form/detail?workitemId=5e1ee9090d3b4b62a610b1b21e537286&workflowInstanceId=69234693194c4314b5e5c66adc0adf91&return=%2Fworkflow-center%2Fmy-unfinished-workitem";
      str = URLDecoder.decode(str,"UTF-8");
      UserVO userVo = projectSummaryServiceImpl.getUserVo();
      return getOkResponseResult( userVo,"查询成功");
   }

   @ApiOperation(value = "查询projectsummary",notes = "查询projectsummary")
   @PostMapping("/queryProjectSummary")
   public ResponseResult<PageResult>  queryProjectSummary(@RequestBody ProjectSummaryParam projectSummaryParam)  {
      log.info("ProjectManageController|projectSummaryParam|"+projectSummaryParam.toString());
      PageResult pageResult = projectSummaryServiceImpl.queryProjectSummaryPage(projectSummaryParam);
      return getOkResponseResult( pageResult,"查询成功");
   }

   @ApiOperation(value = "更新project状态",notes = "更新project状态")
   @PostMapping("/updateProjectStatus")
   public ResponseResult<Integer>  updateProjectStatus(@RequestBody ProjectSummaryParam projectSummaryParam){
      log.info("ProjectManageController|updateProjectStatus|"+projectSummaryParam.toString());
      int result = projectSummaryServiceImpl.updateProjectStatus(projectSummaryParam);
      return getOkResponseResult( result,"更新项目状态成功");
   }

   @ApiOperation(value = "更新供应商付款操作标记",notes = "更新供应商付款操作标记")
   @PostMapping("/updateVendorPayFlag")
   public ResponseResult<Integer>  updateVendorPayFlag(@RequestBody ProjectSummaryParam projectSummaryParam){
      log.info("ProjectManageController|updateVendorPayFlag|"+projectSummaryParam.toString());
      int result = projectSummaryServiceImpl.updateVendorPayFlag(projectSummaryParam);
      return getOkResponseResult( result,"更新供应商付款操作标记");
   }

   @ApiOperation(value = "更新客户收款操作标记",notes = "更新客户付款操作标记")
   @PostMapping("/updateClientPayFlag")
   public ResponseResult<Integer>  updateClientPayFlag(@RequestBody ProjectSummaryParam projectSummaryParam){
      log.info("ProjectManageController|updateClientPayFlag|"+projectSummaryParam.toString());
      int result = projectSummaryServiceImpl.updateClientPayFlag(projectSummaryParam);
      return getOkResponseResult( result,"更新客户付款操作标记");
   }

   @ApiOperation(value = "获取projectSummary表单数据url",notes = "获取projectSummary表单数据url")
   @PostMapping("/getProjectSummaryFormUrl")
   public ResponseResult<Map<String,String>>  getProjectSummaryFormUrl(@RequestBody ProjectSummaryParam projectSummaryParam){
      log.info("ProjectManageController|getProjectSummaryFormUrl|"+projectSummaryParam.toString());
      String ip = "47.103.123.171";
      StringBuffer fromUrl = new StringBuffer();
      fromUrl.append("http://");
      fromUrl.append(ip);
      fromUrl.append("/form/detail?");
      fromUrl.append("sheetCode=project_summary");
      fromUrl.append("&objectId=").append(projectSummaryParam.getId());
      fromUrl.append("&schemaCode=project_summary");
      fromUrl.append("&return=/application/ProjectSummary/application-list/project_summary?parentId=2c93208b6c9e0bc6016c9e36d7ac0011");
      fromUrl.append("&code=project_summary");
      fromUrl.append("&openMode");
      fromUrl.append("&pcUrl");
      Map<String,String> resultMap = new HashMap<>();
      resultMap.put("formUrl",fromUrl.toString());
      return getOkResponseResult( resultMap,"获取表单url成功");
   }
   @ApiOperation(value = "获取projectSummary表单数据",notes = "获取projectSummary表单数据")
   @PostMapping("/getProjectSummaryInfo")
   public ResponseResult<ProjectSummaryVO> getProjectSummaryInfo(@RequestBody ProjectSummaryParam projectSummaryParam){
      ProjectSummaryVO projectSummaryVO = projectSummaryServiceImpl.getProjectSummaryInfo(projectSummaryParam);
      return getOkResponseResult( projectSummaryVO,"获取成功");
   }



}
