package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.ContractFinVO;
import com.authine.cloudpivot.ext.queryVo.DeliverableContractParam;
import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.vo.DeliverableContract;
import com.authine.cloudpivot.ext.vo.DeliverableVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.authine.cloudpivot.ext.vo.ProjectSummaryVO;
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

import java.util.*;


/**
 * Deliverable页面接口
 */
@Api(value = " Deliverable页面接口", tags = "Deliverable页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/api/deliverableController")
@CustomizedOrigin(level = 0)
public class DeliverableController extends BaseController {
   @Autowired
   private DeliverableService deliverableService;

   @ApiOperation(value = "查询deliverable",notes = "查询deliverable")
   @GetMapping("/getDeliverableList")
   public ResponseResult<DeliverableVO>  getDeliverableList(){
      DeliverableVO deliverableVO = deliverableService.getDeliverableList();
      return getOkResponseResult( deliverableVO,"获取成功");
   }

   @ApiOperation(value = "关联客户合同/供应商合同/客户收款/供应商付款",notes = "关联客户合同/供应商合同/客户收款/供应商付款")
   @PostMapping("/addContractRelation")
   public ResponseResult<Integer>  addContractRelation(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      List<DeliverableContract> deliverableContracts = new ArrayList<>();
      Map<String, List<ContractFinVO> > deliverableMap = new HashMap<>();
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getContractFinVOS());
      }
      DeliverableContract deliverableContract = null;
      for(Map.Entry<String,List<ContractFinVO>> map:deliverableMap.entrySet()){
         String key = map.getKey();
         List<ContractFinVO> contractFinVOS = map.getValue();
         for(ContractFinVO contractFinVO:contractFinVOS){
            deliverableContract = new DeliverableContract();
            String uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
            deliverableContract.setId(uuid);
            deliverableContract.setDeliverableId(key);
            deliverableContract.setContractId(contractFinVO.getId());
            deliverableContract.setType(deliverableContractParam.getType());
            deliverableContracts.add(deliverableContract);
         }
      }
      deliverableService.addContractRelation(deliverableContracts);
      return getOkResponseResult( 1,"关联成功");
   }
}
