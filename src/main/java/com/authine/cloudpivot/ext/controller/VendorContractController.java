package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.utils.GenerationCodingUtils;
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


@Api(value = " 供应商合同页面接口", tags = "供应商合同页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/vendorContractController")
@CustomizedOrigin(level = 0)
public class VendorContractController extends BaseController {

    @Autowired
    private VendorContractService vendorContractService;

    @ApiOperation(value = "查询 VendorContract 数据列表接口")
    @PostMapping("/getVendorContractList")
    public ResponseResult<PageResult> getVendorContractList(@RequestBody QueryVendorContract queryVendorContract) {
        PageResult list = vendorContractService.getVendorContractList(queryVendorContract);
        return getOkResponseResult(list,"查询成功");
    }

    @ApiOperation(value = "根据 jobcode 自动生成供应商合同编码")
    @PostMapping("/getAutomaticGenerationCoding")
    public ResponseResult<String> getAutomaticGenerationCoding(String jobCode) {
        GenerationCodingUtils generationCodingUtils = new GenerationCodingUtils();
        String coding = generationCodingUtils.getGenerationCoding();
        String generationCoding = "P" + coding + jobCode;
        return getOkResponseResult(generationCoding,"查询成功");
    }



}
