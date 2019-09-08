package com.authine.cloudpivot.ext.controller;

import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.ext.queryVo.QueryJobCode;
import com.authine.cloudpivot.ext.service.JobCodeService;
import com.authine.cloudpivot.ext.vo.JobCodeVO;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 生成JobCode编码接口
 */
@Api(value = " 生成JobCode编码接口", tags = "生成JobCode编码接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/api/jobCodeController")
@CustomizedOrigin(level = 0)
public class JobCodeController extends BaseController {
   @Autowired
   private JobCodeService jobCodeService;
   @ApiOperation(value = "生成JobCode编码",notes = "生成JobCode编码")
   @GetMapping("/getJobCode")
   public ResponseResult<JobCodeVO>  getJobCode(){
      JobCodeVO jobCodeVO = jobCodeService.getJobCode();
      return getOkResponseResult( jobCodeVO,"获取成功");
   }
   @ApiOperation(value = "插入bizobjectid",notes = "插入bizobjectid")
   @PostMapping("/insertBizobjectid")
   public ResponseResult<JSONObject>  insertBizobjectid(@RequestBody QueryJobCode queryJobCode){
      JSONObject json = new JSONObject();
      int result = 0;
      try {
         result = jobCodeService.insertBizObjectid(queryJobCode);
         BigDecimal id = queryJobCode.getId();
         json.put("resultdata",result);
         json.put("id",id);
         return getOkResponseResult( json,"插入数据成功");
      } catch (Exception e) {
         return getOkResponseResult( json,"传入参数异常，插入失败");
      }

   }

}
