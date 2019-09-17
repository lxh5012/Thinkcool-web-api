package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.engine.api.model.runtime.AttachmentModel;
import com.authine.cloudpivot.ext.queryVo.*;
import com.authine.cloudpivot.ext.service.ClientContractService;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.service.IProjectSummaryService;
import com.authine.cloudpivot.ext.vo.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;


/**
 *   项目管理页面接口
 *   @author laixh
 *   @date 2019/09/01
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
   private IProjectSummaryService projectSummaryServiceImpl;
   @Autowired
   private ClientContractService clientContractService;

   @ApiOperation(value = "查询deliverable",notes = "查询deliverable")
   @PostMapping("/getDeliverableList")
   public ResponseResult<DeliverableVO>  getDeliverableList(@RequestBody QueryDeliverable queryDeliverable){
      DeliverableVO deliverableVO = deliverableService.getDeliverableList(queryDeliverable);
      if(Objects.isNull(deliverableVO)){
         deliverableVO = new DeliverableVO();
         deliverableVO.setSumClientPrice( BigDecimal.ZERO);
         deliverableVO.setSumUnitCost( BigDecimal.ZERO);
      }
      return getOkResponseResult( deliverableVO,"获取成功");
   }
   @ApiOperation(value = "查询deliverables",notes = "查询deliverables")
   @PostMapping("/queryDeliverables")
   public ResponseResult<PageResult>  queryDeliverables(@RequestBody QueryDeliverable queryDeliverable){
      ProjectSummaryParam projectSummaryParam = new ProjectSummaryParam();
      if(StringUtils.isBlank(queryDeliverable.getProjectSummaryId())){
         return getErrResponseResult( null,-1L,"项目ID不能为空");
      }
      projectSummaryParam.setId(queryDeliverable.getProjectSummaryId());
      ProjectSummaryVO projectSummaryVO = projectSummaryServiceImpl.getProjectSummaryInfo(projectSummaryParam);
      if(Objects.isNull(projectSummaryVO)){
         return getErrResponseResult( null,-1L,"获取项目信息失败");
      }
      if(StringUtils.isBlank(projectSummaryVO.getJobCode())){
         return getErrResponseResult( null,-1L,"项目代码为空");
      }
      queryDeliverable.setJobcode(projectSummaryVO.getJobCode());
      PageResult pageResult = deliverableService.queryDeliverables(queryDeliverable);
      return getOkResponseResult( pageResult,"获取成功");
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
            String uuid = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
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
      Map<String, List<ClientContractVO> > deliverableMap = new HashMap<>(deliverableIdArr.length);
      List<String> contractIds = new ArrayList<>();
      // 通过合同id获取数据
      List<ClientContractVO> clientContractVOListVal = deliverableContractParam.getClientContractVOS();
      for(ClientContractVO clientContractVO:clientContractVOListVal){
         contractIds.add(clientContractVO.getId());
      }
      List<ClientContractVO> voList = clientContractService.getClientContractById(contractIds);
      deliverableContractParam.setClientContractVOS(voList);

      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getClientContractVOS());
      }
      Map<String,List<AttachmentModelVO>> clientContractAttachmentFile = new HashMap<>();
      //组装关联合同信息数据
      List<ClientContractRelationVO> clientContractRelationVOS = new ArrayList<>();
      ClientContractRelationVO clientContractRelationVO = null;
      String objectId = null;
      for(Map.Entry<String,List<ClientContractVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<ClientContractVO> clientContractVOList = map.getValue();
         int cnt = clientContractVOList.size();
         for(int i=0;i<cnt;i++){
               ClientContractVO clientContractVO = clientContractVOList.get(i);
               String clientContractId = clientContractVO.getId();
               objectId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
               clientContractRelationVO = new ClientContractRelationVO();
               BeanUtils.copyProperties(clientContractVO,clientContractRelationVO);
               clientContractRelationVO.setParentId(deliverableId);
               clientContractRelationVO.setClientContractId(clientContractId);
               clientContractRelationVO.setId(objectId);
               List<ClientContractRelationVO> clientContractRelationVOTemp = deliverableService.getClientContractRelation(clientContractRelationVO);
               if(clientContractRelationVOTemp.size()<1){
                  clientContractRelationVOS.add(clientContractRelationVO);
                  //获取合同中的附件
                  List<AttachmentModelVO> attachmentModels = deliverableService.getAttachmentS(clientContractId);
                  clientContractAttachmentFile.put(clientContractId,attachmentModels);
               }
         }
      }

      int returnCode = 0;
      //保存合同关联信息
      if(clientContractRelationVOS.size()>0){
          returnCode = deliverableService.addClientContractInfo(clientContractRelationVOS);
      }
      //组装附件信息
      List<AttachmentModelVO> attachmentModelList = new ArrayList<>();
      for(ClientContractRelationVO contractRelationVO:clientContractRelationVOS){
         List<AttachmentModelVO> attachmentModels = clientContractAttachmentFile.get(contractRelationVO.getClientContractId());
         for(AttachmentModel attachmentModel:attachmentModels){
            AttachmentModelVO attachmentModelTemp = new AttachmentModelVO();
            BeanUtils.copyProperties(attachmentModel,attachmentModelTemp);
            objectId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
            attachmentModelTemp.setId(objectId);
            attachmentModelTemp.setSchemaCode("ClientContractInfo");
            attachmentModelTemp.setParentSchemaCode("Deliverable");
            attachmentModelTemp.setBizObjectId(contractRelationVO.getId());
            attachmentModelTemp.setParentBizObjectId(contractRelationVO.getParentId());
            attachmentModelTemp.setCreatedBy(StringUtils.isNotBlank(getUserId())?getUserId():"2c9280a26706a73a016706a93ccf002b");

            attachmentModelList.add(attachmentModelTemp);
         }
      }
      //保存附件
      if(attachmentModelList.size()>0){
         deliverableService.saveAttachment(attachmentModelList);
      }
      return getOkResponseResult( returnCode,"关联客户合同成功");
   }

   @ApiOperation(value = "供应商合同",notes = "关联供应商合同")
   @PostMapping("/addVendorContractInfo")
   public ResponseResult<Integer>  addVendorContractInfo(@RequestBody DeliverableContractParam deliverableContractParam){
      String deliverableIds = deliverableContractParam.getDeliverableId();
      String [] deliverableIdArr = deliverableIds.split(",");
      JSONArray vendorContractVOS = JSONArray.fromObject(deliverableContractParam.getVendorContractVOS());
      log.info("DeliverableController|addVendorContractInfo|deliverableIds|"+deliverableIds+"|vendorContractVOS|"+vendorContractVOS.toString());
      Map<String, List<VendorContractVO> > deliverableMap = new HashMap<>(deliverableIdArr.length);
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getVendorContractVOS());
      }
      List<VendorContractRelationVO> vendorContractRelationVOS = new ArrayList<>();
      VendorContractRelationVO vendorContractRelationVO = null;
      String objectId = null;
      Map<String,List<AttachmentModelVO>> vendorContractAttachmentFile = new HashMap<>();
      for(Map.Entry<String,List<VendorContractVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<VendorContractVO> vendorContractVOList = map.getValue();
         for(VendorContractVO vendorContractVO:vendorContractVOList){
            String vendorContractId = vendorContractVO.getId();
            vendorContractRelationVO = new VendorContractRelationVO();
            BeanUtils.copyProperties(vendorContractVO,vendorContractRelationVO);
            vendorContractRelationVO.setParentId(deliverableId);
            objectId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
            vendorContractRelationVO.setId(objectId);
            vendorContractRelationVO.setVendorContractId(vendorContractId);

            List<VendorContractRelationVO> vendorContractRelationVOList = deliverableService.getVendorContractRelation(vendorContractRelationVO);
            if(vendorContractRelationVOList.size()<1){
               vendorContractRelationVOS.add(vendorContractRelationVO);
               //获取合同中的附件
               List<AttachmentModelVO> attachmentModels = deliverableService.getAttachmentS(vendorContractId);
               vendorContractAttachmentFile.put(vendorContractId,attachmentModels);
            }
         }
      }
      int returnCode = 0;
      if(vendorContractRelationVOS.size()>0){
         returnCode = deliverableService.addVendorContractInfo(vendorContractRelationVOS);
      }
      //保存附件
      //组装附件信息
      List<AttachmentModelVO> attachmentModelList = new ArrayList<>();
      for(VendorContractRelationVO contractRelationVO:vendorContractRelationVOS){
         List<AttachmentModelVO> attachmentModels = vendorContractAttachmentFile.get(contractRelationVO.getVendorContractId());
         for(AttachmentModel attachmentModel:attachmentModels){
            AttachmentModelVO attachmentModelTemp = new AttachmentModelVO();
            BeanUtils.copyProperties(attachmentModel,attachmentModelTemp);
            objectId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
            attachmentModelTemp.setId(objectId);
            attachmentModelTemp.setSchemaCode("VendorContractInfo");
            attachmentModelTemp.setParentSchemaCode("Deliverable");
            attachmentModelTemp.setBizObjectId(contractRelationVO.getId());
            attachmentModelTemp.setParentBizObjectId(contractRelationVO.getParentId());
            attachmentModelTemp.setCreatedBy(StringUtils.isNotBlank(getUserId())?getUserId():"2c9280a26706a73a016706a93ccf002b");
            attachmentModelList.add(attachmentModelTemp);
         }
      }
      if(attachmentModelList.size()>0){
         deliverableService.saveAttachment(attachmentModelList);
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
      Map<String, List<ClientPaymentFinVO> > deliverableMap = new HashMap<>(deliverableIdArr.length);
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getClientPaymentVOS());
      }
      List<ClientPaymentFinRelationVO> clientPaymentFinRelationVOS = new ArrayList<>();
      ClientPaymentFinRelationVO clientPaymentFinRelationVO = null;
      Map<String,List<AttachmentModelVO>> clientPaymentAttachmentFile = new HashMap<>();
      String objectId = null;
      for(Map.Entry<String,List<ClientPaymentFinVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<ClientPaymentFinVO> clientPaymentFinVOList = map.getValue();
         for(ClientPaymentFinVO clientPaymentFinVO:clientPaymentFinVOList){
            String clientPaymentFinId = clientPaymentFinVO.getId();
            clientPaymentFinRelationVO = new ClientPaymentFinRelationVO();
            BeanUtils.copyProperties(clientPaymentFinVO,clientPaymentFinRelationVO);
            objectId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
            clientPaymentFinRelationVO.setId(objectId);
            clientPaymentFinRelationVO.setParentId(deliverableId);
            clientPaymentFinRelationVO.setClientPayFinId(clientPaymentFinId);
            List<ClientPaymentFinRelationVO> clientPaymentFinRelationVOList = deliverableService.getClientPaymentFinVO(clientPaymentFinRelationVO);
            if(clientPaymentFinRelationVOList.size()<1){
               clientPaymentFinRelationVOS.add(clientPaymentFinRelationVO);
               //获取合同中的附件
               List<AttachmentModelVO> attachmentModels = deliverableService.getAttachmentS(clientPaymentFinVO.getClientPayId());
               clientPaymentAttachmentFile.put(clientPaymentFinId,attachmentModels);
            }
         }
      }
      int returnCode = 0;
      if(clientPaymentFinRelationVOS.size()>0){
         returnCode = deliverableService.addClientPaymentInfo(clientPaymentFinRelationVOS);
      }

      //组装附件信息
      List<AttachmentModelVO> attachmentModelList = new ArrayList<>();
      for(ClientPaymentFinRelationVO clientPaymentFinRelationVOTemp:clientPaymentFinRelationVOS){
         List<AttachmentModelVO> attachmentModels = clientPaymentAttachmentFile.get(clientPaymentFinRelationVOTemp.getClientPayFinId());
         for(AttachmentModel attachmentModel:attachmentModels){
            AttachmentModelVO attachmentModelTemp = new AttachmentModelVO();
            BeanUtils.copyProperties(attachmentModel,attachmentModelTemp);
            objectId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
            attachmentModelTemp.setId(objectId);
            attachmentModelTemp.setSchemaCode("ClientPaymentInfo");
            attachmentModelTemp.setParentSchemaCode("Deliverable");
            attachmentModelTemp.setBizObjectId(clientPaymentFinRelationVOTemp.getId());
            attachmentModelTemp.setParentBizObjectId(clientPaymentFinRelationVOTemp.getParentId());
            attachmentModelTemp.setCreatedBy(StringUtils.isNotBlank(getUserId())?getUserId():"2c9280a26706a73a016706a93ccf002b");
            attachmentModelList.add(attachmentModelTemp);
         }
      }
      if(attachmentModelList.size()>0){
         deliverableService.saveAttachment(attachmentModelList);
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
      Map<String, List<StagePaymentVO> > deliverableMap = new HashMap<>(deliverableIdArr.length);
      for(int i=0;i<deliverableIdArr.length;i++){
         deliverableMap.put(deliverableIdArr[i],deliverableContractParam.getVendorPaymentVOS());
      }
      List<VendorPaymentRelationVO> vendorPaymentRelationVOS = new ArrayList<>();
      VendorPaymentRelationVO vendorPaymentRelationVO = null;
      Map<String,List<AttachmentModelVO>> vendorPaymentAttachmentFile = new HashMap<>();
      String objectId = null;
      for(Map.Entry<String,List<StagePaymentVO>> map:deliverableMap.entrySet()){
         String deliverableId = map.getKey();
         List<StagePaymentVO> vendorPaymentVOList = map.getValue();
         for(StagePaymentVO stagePaymentVO:vendorPaymentVOList){
            String stagePaymentId = stagePaymentVO.getId();
            vendorPaymentRelationVO = new VendorPaymentRelationVO();
            BeanUtils.copyProperties(stagePaymentVO,vendorPaymentRelationVO);
            objectId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
            vendorPaymentRelationVO.setId(objectId);
            vendorPaymentRelationVO.setStagePaymentId(stagePaymentId);
            vendorPaymentRelationVO.setParentId(deliverableId);
            List<VendorPaymentRelationVO> vendorPaymentRelationVOList = deliverableService.getStagePaymentVO(vendorPaymentRelationVO);
            if(vendorPaymentRelationVOList.size()<1){
               vendorPaymentRelationVOS.add(vendorPaymentRelationVO);
               //获取合同中的附件
               List<AttachmentModelVO> attachmentModels = deliverableService.getAttachmentS(stagePaymentVO.getVendorPayId());
               vendorPaymentAttachmentFile.put(stagePaymentId,attachmentModels);
            }

         }
      }
      int returnCode = 0;
      if(vendorPaymentRelationVOS.size()>0){
         returnCode = deliverableService.addVendorPaymentInfo(vendorPaymentRelationVOS);
      }
      //组装附件信息
      List<AttachmentModelVO> attachmentModelList = new ArrayList<>();
      for(VendorPaymentRelationVO vendorPaymentFinRelationVOTemp:vendorPaymentRelationVOS){
         List<AttachmentModelVO> attachmentModels = vendorPaymentAttachmentFile.get(vendorPaymentFinRelationVOTemp.getStagePaymentId());
         for(AttachmentModel attachmentModel:attachmentModels){
            AttachmentModelVO attachmentModelTemp = new AttachmentModelVO();
            BeanUtils.copyProperties(attachmentModel,attachmentModelTemp);
            objectId = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
            attachmentModelTemp.setId(objectId);
            attachmentModelTemp.setSchemaCode("VendorPaymentInfo");
            attachmentModelTemp.setParentSchemaCode("Deliverable");
            attachmentModelTemp.setBizObjectId(vendorPaymentFinRelationVOTemp.getId());
            attachmentModelTemp.setParentBizObjectId(vendorPaymentFinRelationVOTemp.getParentId());
            attachmentModelTemp.setCreatedBy(StringUtils.isNotBlank(getUserId())?getUserId():"2c9280a26706a73a016706a93ccf002b");
            attachmentModelList.add(attachmentModelTemp);
         }
      }
      if(attachmentModelList.size()>0){
         deliverableService.saveAttachment(attachmentModelList);
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

   @ApiOperation(value = "查询关联的合同信息",notes = "查询关联的合同信息")
   @PostMapping("/queryClientContractRelation")
   public ResponseResult<Integer>  queryClientContractRelation(@RequestBody QueryDeliverable queryDeliverable){
      log.info("DeliverableController|queryClientContractRelation|"+queryDeliverable.toString());
      int result = deliverableService.updateDeliverableStatus(queryDeliverable);
      return getOkResponseResult( result,"更新项目状态成功");
   }


   @ApiOperation(value = "获取激活节点信息",notes = "获取激活节点信息")
   @PostMapping("/getAtivateActivity")
   public ResponseResult<AtivateActivityVO>  getAtivateActivityInfo(@RequestBody QueryDeliverable queryDeliverable){
      String userId = this.getUserId();
      if(StringUtils.isBlank(userId)){
         userId ="2c93208b6c9e0bc6016ca80e12071e1c";
      }
      queryDeliverable.setParticipant(userId);
      log.info("DeliverableController|getAtivateActivityInfo|deliverableId|"+queryDeliverable.getDeliverableId()+"|userId|"+userId);
      AtivateActivityVO activityVO = deliverableService.getAtivateActivityInfo(queryDeliverable);
      if (Objects.isNull(activityVO)){
         return getErrResponseResult(null, -1L,"不存在要激活的节点信息");
      }
      return getOkResponseResult( activityVO,"获取激活节点信息成功");
   }

}
