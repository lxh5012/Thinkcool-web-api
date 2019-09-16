package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.engine.api.model.runtime.AttachmentModel;
import com.authine.cloudpivot.ext.queryVo.AtivateActivityVO;
import com.authine.cloudpivot.ext.queryVo.DeliverableContractParam;
import com.authine.cloudpivot.ext.queryVo.QueryDeliverable;
import com.authine.cloudpivot.ext.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverableMapper {
    public DeliverableVO getDeliverableList(QueryDeliverable queryDeliverable);

    public List<DeliverableVO> queryDeliverables(QueryDeliverable queryDeliverable);

    public int updateRelationInfo(DeliverableContractParam deliverableContractParam);

    public int addContractRelation(List<DeliverableContract> deliverableContracts);

    public int addClientContractInfo(List<ClientContractRelationVO> clientContractRelationVOList);

    public int addVendorContractInfo(List<VendorContractRelationVO> vendorContractRelationVOList);

    public int addClientPaymentInfo(List<ClientPaymentFinRelationVO> clientPaymentVOList);

    public int addVendorPaymentInfo(List<VendorPaymentRelationVO> vendorPaymentVOList);

    public int updateDeliverableStatus(QueryDeliverable queryDeliverable);

    public List<ClientContractRelationVO> getClientContractRelation(ClientContractRelationVO clientContractRelationVO);

    public List<VendorContractRelationVO> getVendorContractRelation(VendorContractRelationVO vendorContractRelationVO);

    public List<ClientPaymentFinRelationVO> getClientPaymentFinVO(ClientPaymentFinRelationVO clientPaymentFinRelationVO);

    public List<VendorPaymentRelationVO> getStagePaymentVO(VendorPaymentRelationVO vendorPaymentRelationVO);

    public  List<AttachmentModelVO> getAttachmentS(@Param("bizObjectId") String bizObjectId);

    public int saveAttachment(List<AttachmentModelVO> attachmentModelList);

    public AtivateActivityVO getAtivateActivityInfo(QueryDeliverable queryDeliverable);
}
