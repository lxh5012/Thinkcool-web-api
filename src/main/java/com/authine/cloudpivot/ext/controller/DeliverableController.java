package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.engine.api.model.runtime.AttachmentModel;
import com.authine.cloudpivot.ext.queryVo.ContractFinVO;
import com.authine.cloudpivot.ext.queryVo.DeliverableContractParam;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.service.ClientContractService;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.vo.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
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

   @Autowired
   private ClientContractService clientContractService;

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
      JSONArray clientContractVOS = JSONArray.fromObject(deliverableContractParam.getClientContractVOS());
      log.info("DeliverableController|addClientContractInfo|deliverableIds|"+deliverableIds+"|clientContractVOS|"+clientContractVOS.toString());
      Map<String, List<ClientContractVO> > deliverableMap = new HashMap<>();
      List<String> contractIds = new ArrayList<>();
      // 通过合同id获取数据 todo
      List<ClientContractVO> clientContractVOListVal = deliverableContractParam.getClientContractVOS();
      for(ClientContractVO clientContractVO:clientContractVOListVal){
         contractIds.add(clientContractVO.getId());
      }
      List<ClientContractVO> voList = clientContractService.getClientContractById(contractIds);
      deliverableContractParam.setClientContractVOS(voList);

      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getClientContractVOS());
      }
      //保存关联合同信息数据
      List<ClientContractRelationVO> clientContractRelationVOS = new ArrayList<>();
      ClientContractRelationVO clientContractRelationVO = null;
      for(Map.Entry<String,List<ClientContractVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<ClientContractVO> clientContractVOList = map.getValue();
         int cnt = clientContractVOList.size();
         String objectId = null;
         for(int i=0;i<cnt;i++){
               ClientContractVO clientContractVO = clientContractVOList.get(i);
               objectId = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
               clientContractRelationVO = new ClientContractRelationVO();
               BeanUtils.copyProperties(clientContractVO,clientContractRelationVO);
               clientContractRelationVO.setParentId(deliverableId);
               clientContractRelationVO.setClientContractId(clientContractVO.getId());
               clientContractRelationVO.setId(objectId);
               List<ClientContractRelationVO> clientContractRelationVOTemp = deliverableService.getClientContractRelation(clientContractRelationVO);
               if(clientContractRelationVOTemp.size()>0){
                  clientContractRelationVOS.add(clientContractRelationVO);
               }
         }
      }

      int returnCode = 0;
      if(clientContractRelationVOS.size()>0){
          returnCode = deliverableService.addClientContractInfo(clientContractRelationVOS);
      }
      //保存附件
      AttachmentModel resourceModel = getBizObjectFacade().getAttachmentByRefId("971e2865ce6d4d31990459a7d18fd859");
      return getOkResponseResult( returnCode,"关联客户合同成功");
   }

   @ApiOperation(value = "供应商合同",notes = "关联供应商合同")
   @PostMapping("/addVendorContractInfo")
   public ResponseResult<Integer>  addVendorContractInfo(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      JSONArray vendorContractVOS = JSONArray.fromObject(deliverableContractParam.getVendorContractVOS());
      log.info("DeliverableController|addVendorContractInfo|deliverableIds|"+deliverableIds+"|vendorContractVOS|"+vendorContractVOS.toString());
      Map<String, List<VendorContractVO> > deliverableMap = new HashMap<>();
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getVendorContractVOS());
      }
      List<VendorContractRelationVO> vendorContractRelationVOS = new ArrayList<>();
      VendorContractRelationVO vendorContractRelationVO = null;
      for(Map.Entry<String,List<VendorContractVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<VendorContractVO> vendorContractVOList = map.getValue();
         for(VendorContractVO vendorContractVO:vendorContractVOList){
            vendorContractRelationVO = new VendorContractRelationVO();
            BeanUtils.copyProperties(vendorContractVO,vendorContractRelationVO);
            vendorContractRelationVO.setParentId(deliverableId);
            String objectId = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
            vendorContractRelationVO.setId(objectId);
            vendorContractRelationVO.setVendorContractId(vendorContractVO.getId());

            List<VendorContractRelationVO> vendorContractRelationVOList = deliverableService.getVendorContractRelation(vendorContractRelationVO);
            if(vendorContractRelationVOList.size()<1){
               vendorContractRelationVOS.add(vendorContractRelationVO);
            }
         }
      }
      int returnCode = 0;
      if(vendorContractRelationVOS.size()>0){
         returnCode = deliverableService.addVendorContractInfo(vendorContractRelationVOS);
      }
      return getOkResponseResult( returnCode,"关联供应商成功");
   }

   @ApiOperation(value = "客户收款",notes = "关联客户收款")
   @PostMapping("/addClientPaymentInfo")
   public ResponseResult<Integer>  addClientPaymentInfo(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      JSONArray clientPaymentVOS = JSONArray.fromObject(deliverableContractParam.getClientPaymentVOS());
      log.info("DeliverableController|addClientPaymentInfo|deliverableIds|"+deliverableIds+"|clientPaymentVOS|"+clientPaymentVOS.toString());
      Map<String, List<ClientPaymentFinVO> > deliverableMap = new HashMap<>();
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getClientPaymentVOS());
      }
      List<ClientPaymentFinRelationVO> clientPaymentFinRelationVOS = new ArrayList<>();
      ClientPaymentFinRelationVO clientPaymentFinRelationVO = null;
      for(Map.Entry<String,List<ClientPaymentFinVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<ClientPaymentFinVO> clientPaymentFinVOList = map.getValue();
         String objectId = null;
         for(ClientPaymentFinVO clientPaymentFinVO:clientPaymentFinVOList){
            clientPaymentFinRelationVO = new ClientPaymentFinRelationVO();
            BeanUtils.copyProperties(clientPaymentFinVO,clientPaymentFinRelationVO);
            objectId = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
            clientPaymentFinRelationVO.setId(objectId);
            clientPaymentFinRelationVO.setParentId(deliverableId);
            clientPaymentFinRelationVO.setClientPayFinId(clientPaymentFinVO.getId());
            // 检查是否已经关联 todo
            List<ClientPaymentFinRelationVO> clientPaymentFinRelationVOList = deliverableService.getClientPaymentFinVO(clientPaymentFinRelationVO);
            if(clientPaymentFinRelationVOList.size()<1){
               clientPaymentFinRelationVOS.add(clientPaymentFinRelationVO);
            }
         }
      }
      int returnCode = 0;
      if(clientPaymentFinRelationVOS.size()>0){
         returnCode = deliverableService.addClientPaymentInfo(clientPaymentFinRelationVOS);
      }


      return getOkResponseResult( returnCode,"关联客户收款成功");
   }

   @ApiOperation(value = "供应商付款",notes = "供应商付款")
   @PostMapping("/addVendorPaymentInfo")
   public ResponseResult<Integer>  addVendorPaymentInfo(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      JSONArray vendorPaymentVOS = JSONArray.fromObject(deliverableContractParam.getVendorPaymentVOS());
      log.info("DeliverableController|addVendorPaymentInfo|deliverableIds|"+deliverableIds+"|vendorPaymentVOS|"+vendorPaymentVOS.toString());
      Map<String, List<StagePaymentVO> > deliverableMap = new HashMap<>();
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getVendorPaymentVOS());
      }
      List<VendorPaymentRelationVO> vendorPaymentRelationVOS = new ArrayList<>();
      VendorPaymentRelationVO vendorPaymentRelationVO = null;
      for(Map.Entry<String,List<StagePaymentVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<StagePaymentVO> vendorPaymentVOList = map.getValue();
         String objectId = null;
         for(StagePaymentVO stagePaymentVO:vendorPaymentVOList){
            vendorPaymentRelationVO = new VendorPaymentRelationVO();
            BeanUtils.copyProperties(stagePaymentVO,vendorPaymentRelationVO);
            objectId = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
            vendorPaymentRelationVO.setId(objectId);
            vendorPaymentRelationVO.setStagePaymentId(stagePaymentVO.getId());
            vendorPaymentRelationVO.setParentId(deliverableId);
            //判断是否已关联 todo
            List<VendorPaymentRelationVO> vendorPaymentRelationVOList = deliverableService.getStagePaymentVO(vendorPaymentRelationVO);
            if(vendorPaymentRelationVOList.size()<1){
               vendorPaymentRelationVOS.add(vendorPaymentRelationVO);
            }

         }
      }
      int returnCode = 0;
      if(vendorPaymentRelationVOS.size()>0){
         returnCode = deliverableService.addVendorPaymentInfo(vendorPaymentRelationVOS);
      }

      return getOkResponseResult( returnCode,"关联供应商付款成功");
   }

   @ApiOperation(value = "更新project状态",notes = "更新project状态")
   @PostMapping("/updateDeliverableStatus")
   public ResponseResult<Integer>  updateDeliverableStatus(@RequestBody QueryDeliverable queryDeliverable){
      log.info("DeliverableController|updateDeliverableStatus|"+queryDeliverable.toString());
      int result = deliverableService.updateDeliverableStatus(queryDeliverable);
      return getOkResponseResult( result,"更新项目状态成功");
   }

}
