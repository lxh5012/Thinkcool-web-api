package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.utils.GenerationCodingUtils;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.TestVO;
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

import java.util.Random;


@Api(value = " 供应商合同页面接口", tags = "供应商合同页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/api/vendorContractController")
@CustomizedOrigin(level = 0)
public class VendorContractController extends BaseController {

    @Autowired
    private VendorContractService vendorContractService;

    @ApiOperation(value = "查询 VendorContract 数据列表接口")
    @PostMapping("/getVendorContractList")
    public ResponseResult<PageResult> getVendorContractList(@RequestBody QueryVendorContract queryVendorContract) {
        PageResult list = vendorContractService.getVendorContractList(queryVendorContract);
        return getOkResponseResult(list, "查询成功");
    }

    @ApiOperation(value = "根据 jobcode 自动生成供应商合同编码")
    @PostMapping("/getAutomaticGenerationCoding")
    public ResponseResult<String> getAutomaticGenerationCoding(String jobCode) {
        GenerationCodingUtils generationCodingUtils = new GenerationCodingUtils();
        String coding = generationCodingUtils.getGenerationCoding();
        String generationCoding = "P" + coding + jobCode;
        return getOkResponseResult(generationCoding, "查询成功");
    }


    @ApiOperation(value = "根据 jobcode 自动生成供应商合同编码")
    @PostMapping("/getVendorAutomaticGenerationCoding")
    public ResponseResult<String> getVendorAutomaticGenerationCoding(@RequestBody TestVO testVO) {

        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        int num1 = random.nextInt(9);
        String str1 = String.format("%01d", num1);

        int num2 = random.nextInt(99);
        String str2 = String.format("%02d", num2);

        int num3 = random.nextInt(999);
        String str3 = String.format("%03d", num3);
        try {
            if (testVO.getJobCode() != null || !testVO.getJobCode().equals("") || !testVO.getJobCode().equals("null") || testVO.getContractType() != null || !testVO.getContractType().equals("") || !testVO.getContractType().equals("null")) {

                if (testVO.getContractType().equals("客户补充合同")) {
                    stringBuffer.append("P");
                    stringBuffer.append(str3);
                    stringBuffer.append(testVO.getJobCode());
                    stringBuffer.append("A");
                    stringBuffer.append(str1);
                } else if (testVO.getContractType().equals("客户订单(适用于订单等同合同)")) {
                    stringBuffer.append("P");
                    stringBuffer.append(str3);
                    stringBuffer.append(testVO.getJobCode());
                    stringBuffer.append("P");
                    stringBuffer.append(str2);
                } else {
                    stringBuffer.append("P");
                    stringBuffer.append(str3);
                    stringBuffer.append(testVO.getJobCode());
                }

                return getOkResponseResult(stringBuffer.toString(), "编码生成成功");

            } else {
                return getOkResponseResult(stringBuffer.toString(), "传入参数异常，编码生成失败");
            }

        } catch (Exception e) {
            return getOkResponseResult(stringBuffer.toString(), "传入参数异常，编码生成失败");
        }
    }


}
