package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.engine.api.model.runtime.AttachmentModel;
import com.authine.cloudpivot.ext.queryVo.AtivateActivityVO;
import com.authine.cloudpivot.ext.queryVo.DeliverableContractParam;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeliverableService {
    public DeliverableVO getDeliverableList(QueryDeliverable queryDeliverable);

    public PageResult queryDeliverables(QueryDeliverable queryDeliverable);

    public int addContractRelation(List<DeliverableContract> deliverableContracts);

    public int addClientContractInfo(List<ClientContractRelationVO> clientContractRelationVOS);

    public int addVendorContractInfo(List<VendorContractRelationVO> vendorContractRelationVOList);

    public int addClientPaymentInfo(List<ClientPaymentFinRelationVO> clientPaymentVOList);

    public int addVendorPaymentInfo(List<VendorPaymentRelationVO> vendorPaymentVOList);

    public int updateDeliverableStatus(QueryDeliverable queryDeliverable);

    public int updateRelationInfo(DeliverableContractParam deliverableContractParam);

    public List<ClientContractRelationVO> getClientContractRelation(ClientContractRelationVO clientContractRelationVO );

    public List<VendorContractRelationVO> getVendorContractRelation(VendorContractRelationVO vendorContractRelationVO);

    public List<ClientPaymentFinRelationVO> getClientPaymentFinVO(ClientPaymentFinRelationVO clientPaymentFinRelationVO);

    public List<VendorPaymentRelationVO> getStagePaymentVO(VendorPaymentRelationVO vendorPaymentRelationVO);

    public  List<AttachmentModelVO> getAttachmentS(String bizObjectId);

    public int saveAttachment(List<AttachmentModelVO> attachmentModelList);

    public AtivateActivityVO getAtivateActivityInfo(QueryDeliverable queryDeliverable);
}
