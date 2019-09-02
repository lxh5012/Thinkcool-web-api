package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.service.ClientPaymentService;
import com.authine.cloudpivot.ext.vo.ClientPaymentVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.POST;
import java.util.List;

/**
 * 供应商付款页面接口
 */
@Api(value = " 客户收款页面接口", tags = "客户收款页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/clientPaymentController")
@CustomizedOrigin(level = 0)
public class ClientPaymentController extends BaseController {
   @Autowired
   private ClientPaymentService clientPaymentService;
   @ApiOperation(value = "查询clientPayment",notes = "查询clientPayment")
   @PostMapping("/getclientPaymentList")
   public ResponseResult<PageResult>  getClientPaymentList(@RequestBody QueryClientPayment queryClientPayment){
      PageResult list = clientPaymentService.getClientPaymentList(queryClientPayment);
      return getOkResponseResult( list,"查询成功");
   }

}
