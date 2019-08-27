package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/VendorContractController")
public class VendorContractController extends BaseController {

    @GetMapping("/testVendorContract/{id}")
    @ResponseBody
    public ResponseResult<String> testVendorContract(@PathVariable("id") String id) {
        return getOkResponseResult(id, "testVendorContract");
    }
}
