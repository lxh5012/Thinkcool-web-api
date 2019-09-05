package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.service.ClientContractService;
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


@Api(value = " 供应商合同页面接口", tags = "供应商合同页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/clientContractController")
@CustomizedOrigin(level = 0)
public class ClientContractController extends BaseController {
    @Autowired
    private ClientContractService clientContractService;

    @ApiOperation(value = "查询 ClientContract 数据列表接口")
    @PostMapping("/getClientContractList")
    public ResponseResult<PageResult> getClientContractList(@RequestBody QueryClientContract queryClientContract) {
        PageResult list = clientContractService.getClientContractList(queryClientContract);
        return getOkResponseResult(list,"查询成功");
    }




    @ApiOperation(value = "根据 jobcode 自动生成客户合同编码")
    @PostMapping("/getAutomaticGenerationCoding")
    public String getAutomaticGenerationCoding(@RequestBody TestVO testVO) {
        GenerationCodingUtils generationCodingUtils = new GenerationCodingUtils();
        StringBuffer stringBuffer = new StringBuffer();
        String coding = generationCodingUtils.getGenerationCoding();
        String coding1 = generationCodingUtils.getGenerationCoding1();
        String coding2 = generationCodingUtils.getGenerationCoding2();
        if (testVO.getContractType().equals("客户补充合同")){
            stringBuffer.append("R");
            stringBuffer.append(coding);
            stringBuffer.append(testVO.getJobCode());
            stringBuffer.append("A");
            stringBuffer.append(coding1);
        }else if (testVO.getContractType().equals("客户订单(适用于订单等同合同)")){
            stringBuffer.append("R");
            stringBuffer.append(coding);
            stringBuffer.append(testVO.getJobCode());
            stringBuffer.append("P");
            stringBuffer.append(coding2);
        }else {
            stringBuffer.append("R");
            stringBuffer.append(coding);
            stringBuffer.append(testVO.getJobCode());
        }
        return stringBuffer.toString();
    }



}
