package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryVendorPayment;
import com.authine.cloudpivot.ext.service.VendorPaymentService;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供应商付款页面接口
 */
@Api(value = " 供应商付款页面接口", tags = "供应商付款页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/vendorPaymentController")
@CustomizedOrigin(level = 0)
public class VendorPaymentController extends BaseController {
   @Autowired
   private VendorPaymentService vendorPaymentService;
   @ApiOperation(value = "查询vendorPayment",notes = "查询vendorPayment")
   @PostMapping("/getvendorPaymentList")
   public ResponseResult<PageResult>  getvendorPaymentList(@RequestBody QueryVendorPayment queryvendorPayment){
      PageResult list = vendorPaymentService.getVendorPaymentList(queryvendorPayment);
      return getOkResponseResult( list,"查询成功");
   }

}
