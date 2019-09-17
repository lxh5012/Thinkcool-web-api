package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.mapper.ClientContractMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.ClientContractService;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.vo.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.List;
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
    @Autowired
    private ClientContractService clientContractService;

    @Autowired
    private ClientContractMapper clientContractMapper;

    @ApiOperation(value = "查询 VendorContract 数据列表接口")
    @PostMapping("/getVendorContractList")
    public ResponseResult<PageResult> getVendorContractList(@RequestBody QueryVendorContract queryVendorContract) {
        PageResult list = vendorContractService.getVendorContractList(queryVendorContract);
        return getOkResponseResult(list, "查询成功");
    }


    @ApiOperation(value = "根据供应商合同的jobcode 查询客户合同列表,并判断合同状态是否为已完成")
    @PostMapping("/getVendorContractStatus")
    public ResponseResult<String> getVendorContractStatus(@RequestBody QueryClientContract queryClientContract) {

        List<ClientContractVO> list = clientContractMapper.getClientContractList(queryClientContract);
        for (int i = 0;i<list.size();i++){
            if (list.get(i).getClientContractStatus().contains("进行中 In progress")){
                return getOkResponseResult("False", "客户合同还未结束");
            }
        }
        return getOkResponseResult("True", "客户合同已结束");
    }


    @ApiOperation(value = "根据 jobcode contractType 自动生成供应商合同编码")
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
            if (StringUtils.isNotBlank(testVO.getJobCode()) && StringUtils.isNotBlank(testVO.getContractType())) {

                if (testVO.getContractType().equals("供应商补充合同") || testVO.getContractType().equals("KOL供应商补充合同")) {
                    stringBuffer.append("P");
                    stringBuffer.append(str3);
                    stringBuffer.append(testVO.getJobCode());
                    stringBuffer.append("A");
                    stringBuffer.append(str1);
                } else if (testVO.getContractType().equals("供应商订单(适用于订单等同合同)") || testVO.getContractType().equals("KOL供应商订单(适用于订单等同合同)")) {
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
    @ApiOperation(value = "获取供应商客户合同代码", notes = "获取供应商客户合同代码，根据jobcode查询")
    @PostMapping("/getVendoeContractCode")
    public ResponseResult<List<SelectVO>> getVendorContractCodeList(@RequestBody QueryVendorContract queryVendorContract) {
        log.info("|ClientContractController|getVendorContractCodeList|jobcode|"+queryVendorContract.getJobcode());
        List<SelectVO> clientContractVOS = vendorContractService.getVendorContractCodeList(queryVendorContract);
        return getOkResponseResult(clientContractVOS, "获取供应商客户合同代码列表成功");
    }

}
