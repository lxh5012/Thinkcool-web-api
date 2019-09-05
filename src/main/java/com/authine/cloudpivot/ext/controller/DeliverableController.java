package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.service.ClientPaymentService;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.vo.DeliverableVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
   @ApiOperation(value = "查询deliverable",notes = "查询deliverable")
   @GetMapping("/getDeliverableList")
   public ResponseResult<DeliverableVO>  getDeliverableList(){
      DeliverableVO deliverableVO = deliverableService.getDeliverableList();
      return getOkResponseResult( deliverableVO,"获取成功");
   }

}
