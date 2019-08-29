package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.service.ClientPaymentService;
import com.authine.cloudpivot.ext.vo.ClientPaymentVO;
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
import java.util.List;

/**
 * 项目管理页面接口
 */
@Api(value = " 客户付款页面接口", tags = "客户付款页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/api/clientPayment")
@CustomizedOrigin(level = 0)
public class ClientPaymentController extends BaseController {
   @Autowired
   private ClientPaymentService clientPaymentServiceImpl;
   @ApiOperation(value = "查询clientPayment",notes = "查询clientPayment")
   @GetMapping("/queryProject")
   public ResponseResult<List<ClientPaymentVO>>  getClientPaymentList(@Valid @RequestBody QueryClientPayment queryClientPayment){
      List<ClientPaymentVO> userVo = clientPaymentServiceImpl.getClientPaymentList(queryClientPayment);
      return getOkResponseResult( userVo,"查询成功");
   }

}
