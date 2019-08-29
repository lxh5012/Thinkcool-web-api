package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/vendorContractController")
public class VendorContractController extends BaseController {

    @Autowired
    private VendorContractService vendorContractService;

    @RequestMapping("/getVendorContractList")
    @ResponseBody
    public ResponseResult<PageResult> getVendorContractList(@Param("queryVendorContract") QueryVendorContract queryVendorContract) {
        PageResult list = vendorContractService.getVendorContractList(queryVendorContract);
        return getOkResponseResult(list,"查询成功");
    }

}
