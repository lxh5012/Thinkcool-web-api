package com.authine.cloudpivot.ext.controller;

import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.queryVo.QueryClientContractCode;
import com.authine.cloudpivot.ext.service.ClientContractService;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.SelectVO;
import com.authine.cloudpivot.ext.vo.TestVO;
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

import java.util.List;
import java.util.Random;


@Api(value = " 供应商合同页面接口", tags = "供应商合同页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/api/clientContractController")
@CustomizedOrigin(level = 0)
public class ClientContractController extends BaseController {
    @Autowired
    private ClientContractService clientContractService;

    @ApiOperation(value = "查询 ClientContract 数据列表接口")
    @PostMapping("/getClientContractList")
    public ResponseResult<PageResult> getClientContractList(@RequestBody QueryClientContract queryClientContract) {
        PageResult list = clientContractService.getClientContractList(queryClientContract);
        return getOkResponseResult(list, "查询成功");
    }


    @ApiOperation(value = "根据 jobcode 自动生成客户合同编码")
    @PostMapping("/getClientAutomaticGenerationCoding")
    public ResponseResult<String> getClientAutomaticGenerationCoding(@RequestBody TestVO testVO) {

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

                if (testVO.getContractType().equals("客户补充合同")) {
                    stringBuffer.append("R");
                    stringBuffer.append(str3);
                    stringBuffer.append(testVO.getJobCode());
                    stringBuffer.append("A");
                    stringBuffer.append(str1);
                } else if (testVO.getContractType().equals("客户订单(适用于订单等同合同)")) {
                    stringBuffer.append("R");
                    stringBuffer.append(str3);
                    stringBuffer.append(testVO.getJobCode());
                    stringBuffer.append("P");
                    stringBuffer.append(str2);
                } else {
                    stringBuffer.append("R");
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

    @ApiOperation(value = "获取客户合同代码", notes = "获取客户合同代码，根据jobcode查询")
    @PostMapping("/getClientContractCode")
    public ResponseResult<List<SelectVO>> getClientContractCodeList(@RequestBody QueryClientContract queryClientContract) {
        log.info("|ClientContractController|getClientContractCodeList|jobcode|"+queryClientContract.getJobcode());
        List<SelectVO> clientContractVOS = clientContractService.getClientContractCodeList(queryClientContract);
        return getOkResponseResult(clientContractVOS, "获取客户合同代码");
    }


}
