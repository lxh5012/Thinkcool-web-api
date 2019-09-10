package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.ext.queryVo.ContractFinVO;
import com.authine.cloudpivot.ext.queryVo.DeliverableContractParam;
import com.authine.cloudpivot.ext.queryVo.ProjectSummaryParam;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.vo.*;
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

   @ApiOperation(value = "关联客户合同",notes = "关联客户合同")
   @PostMapping("/addClientContractInfo")
   public ResponseResult<Integer>  addClientContractInfo(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      Map<String, List<ClientContractVO> > deliverableMap = new HashMap<>();
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getClientContractVOS());
      }
      List<ClientContractVO> clientContractVOSSave = new ArrayList<>();
      ClientContractVO clientContractVOTemp = null;
      for(Map.Entry<String,List<ClientContractVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<ClientContractVO> clientContractVOList = map.getValue();
         String uuid = null;
         for(ClientContractVO clientContractVO:clientContractVOList){
            uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
            clientContractVOTemp = new ClientContractVO();
            clientContractVOTemp.setId(uuid);
            clientContractVOTemp.setParentId(deliverableId);
            clientContractVOTemp.setClientContractStarttime(clientContractVO.getClientContractStarttime());
            clientContractVOTemp.setClientContractEndtime(clientContractVO.getClientContractEndtime());
            clientContractVOTemp.setClientContractStatus(clientContractVO.getClientContractStatus());
            clientContractVOTemp.setContractType(clientContractVO.getContractType());
            clientContractVOTemp.setClientName(clientContractVO.getClientName());
            clientContractVOTemp.setClientContractVersion(clientContractVO.getClientContractVersion());
            clientContractVOTemp.setClientContractCode(clientContractVO.getClientContractCode());
            clientContractVOTemp.setContractValue(clientContractVO.getContractValue());
            clientContractVOSSave.add(clientContractVOTemp);
         }
      }
      int returnCode = deliverableService.addClientContractInfo(clientContractVOSSave);
      return getOkResponseResult( returnCode,"关联客户合同成功");
   }

   @ApiOperation(value = "供应商合同",notes = "关联供应商合同")
   @PostMapping("/addVendorContractInfo")
   public ResponseResult<Integer>  addVendorContractInfo(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      Map<String, List<VendorContractVO> > deliverableMap = new HashMap<>();
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getVendorContractVOS());
      }
      List<VendorContractVO> vendorContractVOS = new ArrayList<>();
      VendorContractVO vendorContractVOTemp = null;
      for(Map.Entry<String,List<VendorContractVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<VendorContractVO> vendorContractVOList = map.getValue();
         for(VendorContractVO vendorContractVO:vendorContractVOList){
            vendorContractVOTemp = new VendorContractVO();
            String uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
            vendorContractVOTemp.setId(uuid);
            vendorContractVOTemp.setParentId(deliverableId);
            vendorContractVOS.add(vendorContractVOTemp);
         }
      }
      int returnCode = deliverableService.addVendorContractInfo(vendorContractVOS);
      return getOkResponseResult( returnCode,"关联供应商成功");
   }

   @ApiOperation(value = "客户收款",notes = "关联客户收款")
   @PostMapping("/addClientPaymentInfo")
   public ResponseResult<Integer>  addClientPaymentInfo(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      Map<String, List<ClientPaymentFinVO> > deliverableMap = new HashMap<>();
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getClientPaymentVOS());
      }
      List<ClientPaymentFinVO> clientPaymentVOSave = new ArrayList<>();
      ClientPaymentFinVO clientPaymentFinVOTemp = null;
      for(Map.Entry<String,List<ClientPaymentFinVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<ClientPaymentFinVO> clientPaymentFinVOList = map.getValue();
         String uuid = null;
         for(ClientPaymentFinVO clientPaymentFinVO:clientPaymentFinVOList){
            uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
            clientPaymentFinVOTemp = new ClientPaymentFinVO();
            clientPaymentFinVOTemp.setId(uuid);
            clientPaymentFinVOTemp.setParentId(deliverableId);
            clientPaymentFinVOTemp.setClientPO(clientPaymentFinVO.getClientPO());
            clientPaymentFinVOTemp.setClientPOvalue(clientPaymentFinVO.getClientPOvalue());
            clientPaymentFinVOTemp.setInvoicingDateClient(clientPaymentFinVO.getInvoicingDateClient());
            clientPaymentFinVOTemp.setClientInvoice(clientPaymentFinVO.getClientInvoice());
            clientPaymentFinVOTemp.setAmountBeforeTax(clientPaymentFinVO.getAmountBeforeTax());
            clientPaymentFinVOTemp.setAmountAfterTax(clientPaymentFinVO.getAmountAfterTax());
            clientPaymentFinVOTemp.setClientPaymentCheckDate(clientPaymentFinVO.getClientPaymentCheckDate());
            clientPaymentFinVOTemp.setClientPaymentAging(clientPaymentFinVO.getClientPaymentAging());
            clientPaymentFinVOTemp.setClientaymentRemittanceDate(clientPaymentFinVO.getClientaymentRemittanceDate());
            clientPaymentFinVOTemp.setClientPaymentOverDue(clientPaymentFinVO.getClientPaymentOverDue());

            clientPaymentVOSave.add(clientPaymentFinVOTemp);
         }
      }
      int returnCode = deliverableService.addClientPaymentInfo(clientPaymentVOSave);
      return getOkResponseResult( returnCode,"关联客户收款成功");
   }

   @ApiOperation(value = "供应商付款",notes = "供应商付款")
   @PostMapping("/addVendorPaymentInfo")
   public ResponseResult<Integer>  addVendorPaymentInfo(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      Map<String, List<StagePaymentVO> > deliverableMap = new HashMap<>();
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getVendorPaymentVOS());
      }
      List<StagePaymentVO> stagePaymentVOSave = new ArrayList<>();
      StagePaymentVO stagePaymentVOTemp = null;
      for(Map.Entry<String,List<StagePaymentVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<StagePaymentVO> vendorPaymentVOList = map.getValue();
         String uuid = null;
         for(StagePaymentVO stagePaymentVO:vendorPaymentVOList){
            uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");

            stagePaymentVOTemp.setId(uuid);
            stagePaymentVOTemp.setParentId(deliverableId);
            stagePaymentVOTemp.setPay(stagePaymentVO.getPay());
            stagePaymentVOTemp.setVendorInvoice(stagePaymentVO.getVendorInvoice());
            stagePaymentVOTemp.setVendorInvoicingDate(stagePaymentVO.getVendorInvoicingDate());
            stagePaymentVOTemp.setInstallment(stagePaymentVO.getInstallment());
            stagePaymentVOTemp.setPaymentVendorDate(stagePaymentVO.getPaymentVendorDate());
            stagePaymentVOTemp.setIndex(stagePaymentVO.getIndex());
            stagePaymentVOTemp.setPaymentCheckDate(stagePaymentVO.getPaymentCheckDate());
            stagePaymentVOTemp.setActualPaymentDate(stagePaymentVO.getActualPaymentDate());

            stagePaymentVOSave.add(stagePaymentVOTemp);
         }
      }
      int returnCode = deliverableService.addVendorPaymentInfo(stagePaymentVOSave);
      return getOkResponseResult( returnCode,"关联供应商付款成功");
   }

}
