package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ClientPaymentMapper;
import com.authine.cloudpivot.ext.mapper.DeliverableMapper;
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


    @Override
    public DeliverableVO getDeliverableList() {
        DeliverableVO list = deliverableMapper.getDeliverableList();
        return list;
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
                if(i!=clientContractRelationVOS.size()-1){
                    clientContractContent.append(clientContractRelationVOTemp.getClientName()).append(clientContractRelationVOTemp.getClientContractCode()).append("/");
                }else{
                    clientContractContent.append(clientContractRelationVOTemp.getClientName()).append(clientContractRelationVOTemp.getClientContractCode());
                }
            }
            deliverableVO.setClientContractContent(clientContractContent.toString());
            // 获取关联供应商合同信息 todo
            VendorContractRelationVO vendorContractRelationVO = new VendorContractRelationVO();
            vendorContractRelationVO.setParentId(deliverableVO.getId());
            List<VendorContractRelationVO> vendorContractRelationVOS = deliverableMapper.getVendorContractRelation(vendorContractRelationVO);
            StringBuffer vendorContractContent = new StringBuffer("");
            for (int i=0;i<vendorContractRelationVOS.size();i++){
                VendorContractRelationVO vendorContractRelationVOTemp = vendorContractRelationVOS.get(i);
                if(i!=clientContractRelationVOS.size()-1){
                    vendorContractContent.append(vendorContractRelationVOTemp.getVendorName()).append(vendorContractRelationVOTemp.getVendorContractCode()).append("/");
                }else{
                    vendorContractContent.append(vendorContractRelationVOTemp.getVendorName()).append(vendorContractRelationVOTemp.getVendorContractCode());
                }
            }
            deliverableVO.setVendorContractContent(vendorContractContent.toString());
            // 获取关联客户收款信息 todo
            ClientPaymentFinRelationVO clientPaymentFinRelationVO = new ClientPaymentFinRelationVO();
            clientContractRelationVO.setParentId(deliverableVO.getId());
            List<ClientPaymentFinRelationVO> clientPaymentFinVOList = deliverableMapper.getClientPaymentFinVO(clientPaymentFinRelationVO);
            StringBuffer clientPaymentContent = new StringBuffer("");
            for (int i=0;i<clientPaymentFinVOList.size();i++){
                ClientPaymentFinRelationVO clientPaymentFinVOTemp = clientPaymentFinVOList.get(i);
                if(i!=clientContractRelationVOS.size()-1){
                    clientPaymentContent.append(clientPaymentFinVOTemp.getClientPO()).append("/");
                }else{
                    clientPaymentContent.append(clientPaymentFinVOTemp.getClientPO());
                }
            }
            deliverableVO.setClientPaymentContent(clientPaymentContent.toString());
            // 获取关联供应商付款信息 todo
            VendorPaymentRelationVO vendorPaymentRelationVO = new VendorPaymentRelationVO();
            vendorPaymentRelationVO.setParentId(deliverableVO.getId());
            List<VendorPaymentRelationVO> vendorPaymentRelationVOS = deliverableMapper.getStagePaymentVO(vendorPaymentRelationVO);
            StringBuffer vendorPaymentContent = new StringBuffer("");
            for (int i=0;i<vendorPaymentRelationVOS.size();i++){
                VendorPaymentRelationVO vendorPaymentRelationVOTemp = vendorPaymentRelationVOS.get(i);
                if(i!=clientContractRelationVOS.size()-1){
                    vendorPaymentContent.append(vendorPaymentRelationVOTemp.getPay()).append(vendorPaymentRelationVOTemp.getVendorInvoice()).append("/");
                }else{
                    vendorPaymentContent.append(vendorPaymentRelationVOTemp.getPay()).append(vendorPaymentRelationVOTemp.getVendorInvoice());
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


}
