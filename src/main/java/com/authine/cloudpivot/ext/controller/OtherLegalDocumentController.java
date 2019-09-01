package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryOtherLegalDocument;
import com.authine.cloudpivot.ext.service.OtherLegalDocumentService;
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


@Api(value = " 法务页面接口 ", tags = " 法务页面接口 ")
@RestController
@Validated
@Slf4j
@RequestMapping("/otherLegalDocumentController")
@CustomizedOrigin(level = 0)
public class OtherLegalDocumentController extends BaseController {

    @Autowired
    private OtherLegalDocumentService otherLegalDocumentService;

    @ApiOperation(value = "查询 OtherLegalDocument 数据列表接口")
    @PostMapping("/getOtherLegalDocumentList")
    public ResponseResult<PageResult> getOtherLegalDocumentList(@RequestBody QueryOtherLegalDocument queryOtherLegalDocument) {
        PageResult list = otherLegalDocumentService.getOtherLegalDocumentList(queryOtherLegalDocument);
        return getOkResponseResult(list,"查询成功");
    }

}
