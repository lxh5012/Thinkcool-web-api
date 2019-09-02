package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.OtherLegalDocumentMapper;
import com.authine.cloudpivot.ext.queryVo.QueryOtherLegalDocument;
import com.authine.cloudpivot.ext.service.OtherLegalDocumentService;
import com.authine.cloudpivot.ext.vo.OtherLegalDocumentVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OtherLegalDocumentServiceImpl implements OtherLegalDocumentService {

    @Autowired
    private OtherLegalDocumentMapper otherLegalDocumentMapper;

    @Override
    public PageResult getOtherLegalDocumentList(QueryOtherLegalDocument queryOtherLegalDocument) {
        int pageNum = queryOtherLegalDocument.getPageNum() == 0?1:queryOtherLegalDocument.getPageNum();
        int pageSize = queryOtherLegalDocument.getPageSize() == 0?10:queryOtherLegalDocument.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<OtherLegalDocumentVO> otherLegalDocumentList = otherLegalDocumentMapper.getOtherLegalDocumentList(queryOtherLegalDocument);
        for (OtherLegalDocumentVO otherLegalDocumentVO:otherLegalDocumentList){
            otherLegalDocumentVO.setFormUrl(getFormUrl(otherLegalDocumentVO.getId()));
        }
        PageInfo<OtherLegalDocumentVO> clientContractVOPageInfo = new PageInfo<>(otherLegalDocumentList);
        PageResult pageResult = PageUtils.getPageResult(clientContractVOPageInfo);
        return pageResult;
    }


    private String getFormUrl(String id){
        String ip = "47.103.123.171";
        StringBuffer fromUrl = new StringBuffer();
        fromUrl.append("http://");
        fromUrl.append(ip);
        fromUrl.append("/form/detail?");
        fromUrl.append("sheetCode=OtherLegalDocument");
        fromUrl.append("&objectId=").append(id);
        fromUrl.append("&schemaCode=OtherLegalDocument");
        fromUrl.append("&return=/application/ProjectSummary/application-list/OtherLegalDocument?parentId=2c93208b6c9e0bc6016c9e36d7ac0011");
        fromUrl.append("&code=OtherLegalDocument");
        fromUrl.append("&openMode");
        fromUrl.append("&pcUrl");
        return fromUrl.toString();
    }


}
