package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.service.ClientContractService;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/ClientContractController")
public class ClientContractController extends BaseController {
    @Autowired
    private ClientContractService clientContractService;


    @PostMapping("/getClientContractList")
    @ResponseBody
    public List<ClientContractVO> getClientContractList(@Param("queryClientContract") QueryClientContract queryClientContract) {
        return clientContractService.getClientContractList(queryClientContract);
    }



}
