package com.authine.cloudpivot.ext.controller;

import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.ext.queryVo.QueryClientContractCode;
import com.authine.cloudpivot.ext.queryVo.QueryVendorContractCode;
import com.authine.cloudpivot.ext.service.ClientContractCodeService;
import com.authine.cloudpivot.ext.service.VendorContractCodeService;
import com.authine.cloudpivot.ext.vo.ClientContractCodeVO;
import com.authine.cloudpivot.ext.vo.TestVO;
import com.authine.cloudpivot.ext.vo.VendorContractCodeVO;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 生成VendorContractCode编码接口
 */
@Api(value = " 生成VendorContractCode编码接口", tags = "生成VendorContractCode编码接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/api/vendorContractCodeController")
@CustomizedOrigin(level = 0)
public class VendorContractCodeController extends BaseController {
    @Autowired
    private VendorContractCodeService vendorContractCodeService;

    @ApiOperation(value = "生成JobCode编码", notes = "生成JobCode编码")
    @PostMapping("/getVendorContractCode")
    public ResponseResult<String> getVendorContractCode(@RequestBody TestVO testVO) {

        StringBuffer stringBuffer = new StringBuffer();
        VendorContractCodeVO vendorContractCodeVO3 = vendorContractCodeService.getContractCode3();

        //获取ID,取ID的后三位,后两位数
        Integer integer3 = vendorContractCodeVO3.getId();
        DecimalFormat decimalFormat3 = new DecimalFormat("000");
        String str3 = decimalFormat3.format(integer3);
        String string3 = String.valueOf(str3);
        int i3 = string3.length();
        String str33 = string3.substring(i3-3,i3);


        try {
            if (StringUtils.isNotBlank(testVO.getJobCode()) && StringUtils.isNotBlank(testVO.getContractType())) {
                if (testVO.getContractType().equals("供应商补充合同")) {
                    //获取ID,取ID的后一位,后两位数
                    VendorContractCodeVO vendorContractCodeVO1 = vendorContractCodeService.getContractCode1();
                    Integer integer1 = vendorContractCodeVO1.getId();
                    DecimalFormat decimalFormat = new DecimalFormat("0");
                    String str1 = decimalFormat.format(integer1);
                    String string1 = String.valueOf(str1);
                    int i1 = string1.length();
                    String str11 = string1.substring(i1-1,i1);

                    stringBuffer.append("R");
                    stringBuffer.append(str33);
                    stringBuffer.append(testVO.getJobCode());
                    stringBuffer.append("A");
                    stringBuffer.append(str11);
                } else if (testVO.getContractType().equals("供应商订单(适用于订单等同合同)")) {
                    //获取ID,取ID的后一位,后两位数
                    VendorContractCodeVO vendorContractCodeVO2 = vendorContractCodeService.getContractCode2();
                    Integer integer2 = vendorContractCodeVO2.getId();
                    DecimalFormat decimalFormat2 = new DecimalFormat("00");
                    String str2 = decimalFormat2.format(integer2);
                    String string2 = String.valueOf(str2);
                    int i2 = string2.length();
                    String str22 = string2.substring(i2-2,i2);

                    stringBuffer.append("R");
                    stringBuffer.append(str33);
                    stringBuffer.append(testVO.getJobCode());
                    stringBuffer.append("P");
                    stringBuffer.append(str22);
                } else {
                    stringBuffer.append("R");
                    stringBuffer.append(str33);
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



    @ApiOperation(value = "一位数插入bizobjectid", notes = "一位数插入bizobjectid")
    @PostMapping("/insertBizobjectid1")
    public ResponseResult<JSONObject> insertBizobjectid1(@RequestBody QueryVendorContractCode queryVendorContractCode) {
        JSONObject json = new JSONObject();
        int result = 0;
        try {
            result = vendorContractCodeService.insertBizObjectid1(queryVendorContractCode);
            BigDecimal id = queryVendorContractCode.getId();
            json.put("resultdata", result);
            json.put("id", id);
            return getOkResponseResult(json, "插入数据成功");
        } catch (Exception e) {
            return getOkResponseResult(json, "传入参数异常，插入失败");
        }
    }


    @ApiOperation(value = "两位数插入bizobjectid", notes = "两位数插入bizobjectid")
    @PostMapping("/insertBizobjectid2")
    public ResponseResult<JSONObject> insertBizobjectid2(@RequestBody QueryVendorContractCode queryVendorContractCode) {
        JSONObject json = new JSONObject();
        int result = 0;
        try {
            result = vendorContractCodeService.insertBizObjectid2(queryVendorContractCode);
            BigDecimal id = queryVendorContractCode.getId();
            json.put("resultdata", result);
            json.put("id", id);
            return getOkResponseResult(json, "插入数据成功");
        } catch (Exception e) {
            return getOkResponseResult(json, "传入参数异常，插入失败");
        }
    }


    @ApiOperation(value = "三位数插入bizobjectid", notes = "三位数插入bizobjectid")
    @PostMapping("/insertBizobjectid3")
    public ResponseResult<JSONObject> insertBizobjectid3(@RequestBody QueryVendorContractCode queryVendorContractCode) {
        JSONObject json = new JSONObject();
        int result = 0;
        try {
            result = vendorContractCodeService.insertBizObjectid3(queryVendorContractCode);
            BigDecimal id = queryVendorContractCode.getId();
            json.put("resultdata", result);
            json.put("id", id);
            return getOkResponseResult(json, "插入数据成功");
        } catch (Exception e) {
            return getOkResponseResult(json, "传入参数异常，插入失败");
        }
    }

}
