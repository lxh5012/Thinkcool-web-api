package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.engine.api.model.runtime.AttachmentModel;
import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ClientPaymentMapper;
import com.authine.cloudpivot.ext.mapper.DeliverableMapper;
import com.authine.cloudpivot.ext.queryVo.AtivateActivityVO;
import com.authine.cloudpivot.ext.queryVo.DeliverableContractParam;
import com.authine.cloudpivot.ext.queryVo.QueryClientPayment;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.service.ClientPaymentService;
import com.authine.cloudpivot.ext.service.DeliverableService;
import com.authine.cloudpivot.ext.utils.ProjectStatusEnum;
import com.authine.cloudpivot.ext.utils.ThinkoolProjectUtils;
import com.authine.cloudpivot.ext.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service(value="deliverableServiceImpl")
public class DeliverableServiceImpl implements DeliverableService {
    @Autowired
    public DeliverableMapper deliverableMapper;

    @Autowired
    private DeliverableService deliverableService;
    @Override
    public DeliverableVO getDeliverableList(QueryDeliverable queryDeliverable) {
        DeliverableVO deliverableVO = deliverableMapper.getDeliverableList(queryDeliverable);
        return deliverableVO;
    }

    @Override
    public PageResult queryDeliverables(QueryDeliverable queryDeliverable) {
        int pageNum = queryDeliverable.getPageNum() == 0?1:queryDeliverable.getPageNum();
        int pageSize = queryDeliverable.getPageSize() == 0?10:queryDeliverable.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<DeliverableVO> queryDeliverableVOList = deliverableMapper.queryDeliverables(queryDeliverable);
        for(DeliverableVO deliverableVO:queryDeliverableVOList){
            // 获取关联客户合同信息
            ClientContractRelationVO clientContractRelationVO = new ClientContractRelationVO();
            clientContractRelationVO.setParentId(deliverableVO.getId());
            List<ClientContractRelationVO> clientContractRelationVOS = deliverableMapper.getClientContractRelation(clientContractRelationVO);
            StringBuffer clientContractContent = new StringBuffer("");
            for (int i=0;i<clientContractRelationVOS.size();i++){
                ClientContractRelationVO clientContractRelationVOTemp = clientContractRelationVOS.get(i);
                String clientName = clientContractRelationVOTemp.getClientName();
                String clientContractCode = clientContractRelationVOTemp.getClientContractCode();
                if (StringUtils.isNotBlank(clientName)&&StringUtils.isNotBlank(clientContractCode)){
                    if(i!=clientContractRelationVOS.size()-1){
                        clientContractContent.append(clientName).append(clientContractCode).append("/");
                    }else{
                        clientContractContent.append(clientName).append(clientContractCode);
                    }
                }
            }
            deliverableVO.setClientContractContent(clientContractContent.toString());
            // 获取关联供应商合同信息
            VendorContractRelationVO vendorContractRelationVO = new VendorContractRelationVO();
            vendorContractRelationVO.setParentId(deliverableVO.getId());
            List<VendorContractRelationVO> vendorContractRelationVOS = deliverableMapper.getVendorContractRelation(vendorContractRelationVO);
            StringBuffer vendorContractContent = new StringBuffer("");
            for (int i=0;i<vendorContractRelationVOS.size();i++){
                VendorContractRelationVO vendorContractRelationVOTemp = vendorContractRelationVOS.get(i);
                String vendorName = vendorContractRelationVOTemp.getVendorName();
                String vendorContractCode = vendorContractRelationVOTemp.getVendorContractCode();
                if(StringUtils.isNotBlank(vendorName)&&StringUtils.isNotBlank(vendorContractCode)){
                    if(i!=clientContractRelationVOS.size()-1){
                        vendorContractContent.append(vendorName).append(vendorContractCode).append("/");
                    }else{
                        vendorContractContent.append(vendorName).append(vendorContractCode);
                    }
                }

            }
            deliverableVO.setVendorContractContent(vendorContractContent.toString());
            // 获取关联客户收款信息
            ClientPaymentFinRelationVO clientPaymentFinRelationVO = new ClientPaymentFinRelationVO();
            clientContractRelationVO.setParentId(deliverableVO.getId());
            List<ClientPaymentFinRelationVO> clientPaymentFinVOList = deliverableMapper.getClientPaymentFinVO(clientPaymentFinRelationVO);
            StringBuffer clientPaymentContent = new StringBuffer("");
            for (int i=0;i<clientPaymentFinVOList.size();i++){
                ClientPaymentFinRelationVO clientPaymentFinVOTemp = clientPaymentFinVOList.get(i);
                String clientPo = clientPaymentFinVOTemp.getClientPO();
                if(StringUtils.isNotBlank(clientPo)){
                    if(i!=clientContractRelationVOS.size()-1){
                        clientPaymentContent.append(clientPo).append("/");
                    }else{
                        clientPaymentContent.append(clientPo);
                    }
                }
            }
            deliverableVO.setClientPaymentContent(clientPaymentContent.toString());
            // 获取关联供应商付款信息
            VendorPaymentRelationVO vendorPaymentRelationVO = new VendorPaymentRelationVO();
            vendorPaymentRelationVO.setParentId(deliverableVO.getId());
            List<VendorPaymentRelationVO> vendorPaymentRelationVOS = deliverableMapper.getStagePaymentVO(vendorPaymentRelationVO);
            StringBuffer vendorPaymentContent = new StringBuffer("");
            for (int i=0;i<vendorPaymentRelationVOS.size();i++){
                VendorPaymentRelationVO vendorPaymentRelationVOTemp = vendorPaymentRelationVOS.get(i);
                String pay = vendorPaymentRelationVOTemp.getPay();
                String vendorInvoice = vendorPaymentRelationVOTemp.getVendorInvoice();
                if(StringUtils.isNotBlank(pay)&&StringUtils.isNotBlank(vendorInvoice)){
                    if(i!=clientContractRelationVOS.size()-1){
                        vendorPaymentContent.append(pay).append(vendorInvoice).append("/");
                    }else{
                        vendorPaymentContent.append(pay).append(vendorInvoice);
                    }
                }
            }
            deliverableVO.setVendorPaymentContent(vendorPaymentContent.toString());
            deliverableVO.setFormUrl(ThinkoolProjectUtils.getFormUrl("Deliverable",deliverableVO.getId(),"Deliverable","2c93208b6c9e0bc6016c9e36d7ac0011","ProjectSummary"));
        }
        PageInfo<DeliverableVO> queryDeliverableVOPageInfo =new PageInfo<>(queryDeliverableVOList);
        PageResult pageResult = PageUtils.getPageResult(queryDeliverableVOPageInfo);
        return pageResult ;
    }

    @Override
    public int addContractRelation(List<DeliverableContract> deliverableContracts) {
        return deliverableMapper.addContractRelation(deliverableContracts);
    }

    @Override
    public int addClientContractInfo(List<ClientContractRelationVO> clientContractRelationVOList) {
        return deliverableMapper.addClientContractInfo(clientContractRelationVOList);
    }

    @Override
    public int addVendorContractInfo(List<VendorContractRelationVO> vendorContractRelationVOList) {
        return deliverableMapper.addVendorContractInfo(vendorContractRelationVOList);
    }

    @Override
    public int addClientPaymentInfo(List<ClientPaymentFinRelationVO> clientPaymentFinVOList) {
        return deliverableMapper.addClientPaymentInfo(clientPaymentFinVOList);
    }

    @Override
    public int addVendorPaymentInfo(List<VendorPaymentRelationVO> stagePaymentVOList) {
        return deliverableMapper.addVendorPaymentInfo(stagePaymentVOList);
    }

    @Override
    public int updateDeliverableStatus(QueryDeliverable queryDeliverable) {
        return deliverableMapper.updateDeliverableStatus(queryDeliverable);
    }

    @Override
    public int updateRelationInfo(DeliverableContractParam deliverableContractParam) {
        return deliverableMapper.updateRelationInfo(deliverableContractParam);
    }

    @Override
    public List<ClientContractRelationVO> getClientContractRelation(ClientContractRelationVO clientContractRelationVO) {
        return deliverableMapper.getClientContractRelation(clientContractRelationVO);
    }

    @Override
    public List<VendorContractRelationVO> getVendorContractRelation(VendorContractRelationVO vendorContractRelationVO) {
        return deliverableMapper.getVendorContractRelation(vendorContractRelationVO);
    }

    @Override
    public List<ClientPaymentFinRelationVO> getClientPaymentFinVO(ClientPaymentFinRelationVO clientPaymentFinRelationVO) {
        return deliverableMapper.getClientPaymentFinVO(clientPaymentFinRelationVO);
    }

    @Override
    public List<VendorPaymentRelationVO> getStagePaymentVO(VendorPaymentRelationVO vendorPaymentRelationVO) {
        return deliverableMapper.getStagePaymentVO(vendorPaymentRelationVO);
    }

    @Override
    public List<AttachmentModelVO> getAttachmentS(String bizObjectId) {
        return deliverableMapper.getAttachmentS(bizObjectId);
    }

    @Override
    public int saveAttachment(List<AttachmentModelVO> attachmentModelList) {
        return deliverableMapper.saveAttachment(attachmentModelList);
    }

    @Override
    public AtivateActivityVO getAtivateActivityInfo(QueryDeliverable queryDeliverable) {
        return deliverableMapper.getAtivateActivityInfo(queryDeliverable);
    }


}
