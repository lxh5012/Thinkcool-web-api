package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.ext.service.SummaryTaskService;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SummaryTaskModel;
import com.authine.cloudpivot.web.api.controller.base.BaseQueryRuntimeController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/formList/query")
@RestController
@Slf4j
public class ThinkCoolFormListController extends BaseQueryRuntimeController {


    @Autowired
    private SummaryTaskService summaryTaskService;

    @ApiOperation(value = "查询数据接口")
    @PostMapping("/list")
    public ResponseResult<PageResult> list(@RequestBody SummaryTaskModel summaryTaskParam) {
        if(Objects.isNull(summaryTaskParam)){
            log.debug("summaryTaskParam 不能为null");
            return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "summaryTaskParam 和 page 不能为null");
        }
        if(Objects.isNull(summaryTaskParam.getFormName())){
            log.debug("FormName不能为null");
            return getErrResponseResult(null, ErrCode.ORG_USER_NONEXISTENT.getErrCode(), "FormName不能为null");
        }

        if("Deliverable".equals(summaryTaskParam.getFormName())){
            PageResult res = summaryTaskService.queryProjectTask(summaryTaskParam);
            return getOkResponseResult(res, "成功获取 project summary可派工 列表数据 ");
        }

        return null;
    }




}

