package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.engine.enums.type.OperationType;
import com.authine.cloudpivot.ext.service.SummaryTaskService;
import com.authine.cloudpivot.ext.vo.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/formList/query")
@RestController
@Slf4j
public class ThinkCoolFormListController extends BaseController {


    @Autowired
    private SummaryTaskService summaryTaskService;

    @ApiOperation(value = "查询project summary可派工数据接口")
    @PostMapping("/summaryTask/list")
    public ResponseResult<PageResult> list(@RequestBody SummaryTaskVO summaryTaskParam) {
        //  String userId = this.getUserId();
        //   summaryTaskParam.setUserId(userId);
        if(checkParam(summaryTaskParam,summaryTaskParam.getFormName(), summaryTaskParam.getPage(), summaryTaskParam.getPageSize()) ){
            log.debug("FormName/Page/PageSize/UserId不能为null");
            return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName/Page/PageSize/UserId不能为null");
        }
        if("Deliverable".equals(summaryTaskParam.getFormName())){
            PageResult res = summaryTaskService.queryProjectTask(summaryTaskParam);
            return getOkResponseResult(res, "成功获取 project summary可派工 列表数据 ");
        }
        return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName不是 Deliverable");
    }

    @ApiOperation(value = "查询 Deliverable工单执行情况 数据接口")
    @PostMapping("/deliverableTask/list")
    public ResponseResult<PageResult> queryDeliverableTask(@RequestBody DeliverableTaskVO deliverableTaskVO) {
       // String userId = this.getUserId();
        //deliverableTaskVO.setUserId(userId);
        if(checkParam(deliverableTaskVO, deliverableTaskVO.getFormName(), deliverableTaskVO.getPage(), deliverableTaskVO.getPageSize()) ){
            log.debug("FormName/Page/PageSize/UserId不能为null");
            return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName/Page/PageSize/UserId不能为null");
        }
        if("DeliverableTask".equals(deliverableTaskVO.getFormName())){
            PageResult res = summaryTaskService.queryDeliverableTask(deliverableTaskVO);
            return getOkResponseResult(res, "成功获取 Deliverable工单执行情况 列表数据 ");
        }
        return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName不是 DeliverableTask");
    }


    @ApiOperation(value = "查看执行工单 数据接口")
        @PostMapping("/queryTaskDetial/list")
    public ResponseResult<PageResult> queryTaskDetial(@RequestBody TaskDetialVO taskDetialVO) {
        //  String userId = this.getUserId();
        //   taskDetialVO.setUserId(userId);
        if(checkParam(taskDetialVO, taskDetialVO.getFormName(), taskDetialVO.getPage(), taskDetialVO.getPageSize()) ){
            return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName/Page/PageSize/UserId不能为null");
        }
        if("TaskDetial".equals(taskDetialVO.getFormName())){
            PageResult res = summaryTaskService.queryTaskDetial(taskDetialVO);
            return getOkResponseResult(res, "成功获取 查看执行工单 列表数据 ");
        }
        return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName不是 Deliverable");
    }


    @ApiOperation(value = "取消流程接口")
    @GetMapping("/cancelActivity")
    public ResponseResult<Boolean> cancelActivity(@RequestParam("workflowInstanceId") String workflowInstanceId,@RequestParam("activityCode") String activityCode) {
        String userId = this.getUserId();
        Boolean cancelResult = getWorkflowInstanceFacade().cancelActivity(userId, workflowInstanceId, activityCode);
        if (cancelResult) {
            return getOkResponseResult(cancelResult, "节点任务取消成功");
        }
        return getOkResponseResult(cancelResult, "节点任务取消失败");
    }

    @ApiOperation(value = "查看 接单管理 数据接口")
    @PostMapping("/queryacceptTask/list")
    public ResponseResult<PageResult> acceptTaskList(@RequestBody AcceptTaskVO acceptTaskVO) {
        //    String userId = this.getUserId();
        //  acceptTaskVO.setUserId(userId);
        if(checkParam(acceptTaskVO, acceptTaskVO.getFormName(), acceptTaskVO.getPage(), acceptTaskVO.getPageSize()) ){
            return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName/Page/PageSize/UserId不能为null");
        }
        if("AcceptTask".equals(acceptTaskVO.getFormName())){
            PageResult res = summaryTaskService.acceptTaskList(acceptTaskVO);
            return getOkResponseResult(res, "成功获取 接单管理 列表数据 ");
        }
        return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName不是 AcceptTask");
    }


    private boolean checkParam(Object obj, String formName, Integer page, Integer PageSize){
        if(Objects.isNull(obj)){
            log.debug("入参对象不能为null");
            return true;
        }
        if(Objects.isNull(formName) || Objects.isNull(page)
                || Objects.isNull(PageSize) ){
            log.debug("FormName不能为null");
            return true;
        }
        return  false;
    }


}

