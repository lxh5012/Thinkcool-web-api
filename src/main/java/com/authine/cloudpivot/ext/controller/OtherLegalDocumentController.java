package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/OtherLegalDocumentController")
public class OtherLegalDocumentController extends BaseController {

    @GetMapping("/testOtherLegalDocument/{id}")
    @ResponseBody
    public ResponseResult<String> testOtherLegalDocument(@PathVariable("id") String id) {
        return getOkResponseResult(id, "testOtherLegalDocument");
    }

}
