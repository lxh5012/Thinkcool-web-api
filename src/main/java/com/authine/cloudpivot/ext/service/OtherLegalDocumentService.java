package com.authine.cloudpivot.ext.service;

import com.authine.cloudpivot.ext.queryVo.QueryOtherLegalDocument;
import com.authine.cloudpivot.ext.vo.PageResult;
import org.apache.ibatis.annotations.Param;

public interface OtherLegalDocumentService {

    public PageResult getOtherLegalDocumentList(@Param("queryOtherLegalDocument") QueryOtherLegalDocument queryOtherLegalDocument);

}
