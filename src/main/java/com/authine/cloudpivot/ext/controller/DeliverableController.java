package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.vo.DeliverableVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
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

import java.util.Objects;


/**
 * Deliverable页面接口
 */
@Api(value = " Deliverable页面接口", tags = "Deliverable页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/deliverableController")
@CustomizedOrigin(level = 0)
public class DeliverableController extends BaseController {
   @Autowired
   private DeliverableService deliverableService;

   @Autowired
   private IProjectSummaryService projectSummaryServiceImpl;
   @ApiOperation(value = "查询deliverable",notes = "查询deliverable")
   @GetMapping("/getDeliverableList")
   public ResponseResult<DeliverableVO>  getDeliverableList(){
      DeliverableVO deliverableVO = deliverableService.getDeliverableList();
      return getOkResponseResult( deliverableVO,"获取成功");
   }

   @ApiOperation(value = "查询deliverables",notes = "查询deliverables")
   @PostMapping("/queryDeliverables")
   public ResponseResult<PageResult>  queryDeliverables(@RequestBody QueryDeliverable queryDeliverable){
      ProjectSummaryParam  projectSummaryParam = new ProjectSummaryParam();
      if(StringUtils.isBlank(queryDeliverable.getProjectSummaryId())){
         return getErrResponseResult( null,-1l,"项目ID不能为空");
      }
      projectSummaryParam.setId(queryDeliverable.getProjectSummaryId());
      ProjectSummaryVO projectSummaryVO = projectSummaryServiceImpl.getProjectSummaryInfo(projectSummaryParam);
      if(Objects.isNull(projectSummaryVO)){
         return getErrResponseResult( null,-1l,"获取项目信息失败");
      }
      queryDeliverable.setJobcode(projectSummaryVO.getJobCode());
      PageResult pageResult = deliverableService.queryDeliverables(queryDeliverable);
      return getOkResponseResult( pageResult,"获取成功");
   }

}
