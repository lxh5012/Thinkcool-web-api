package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryVendorContract;
import com.authine.cloudpivot.ext.service.VendorContractService;
import com.authine.cloudpivot.ext.vo.VendorContractVO;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/VendorContractController")
public class VendorContractController extends BaseController {

    @Autowired
    private VendorContractService vendorContractService;

    @PostMapping("/getVendorContractList")
    @ResponseBody
    public List<VendorContractVO> getVendorContractList(@Param("queryVendorContract") QueryVendorContract queryVendorContract) {
        return vendorContractService.getVendorContractList(queryVendorContract);
    }
}
