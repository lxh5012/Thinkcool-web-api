package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/ClientContractController")
public class ClientContractController extends BaseController {
    @GetMapping("/testClientContract/{id}")
    @ResponseBody
    public ResponseResult<String> testClientContract(@PathVariable("id") String id) {
        return getOkResponseResult(id, "testClientContract");
    }


}
