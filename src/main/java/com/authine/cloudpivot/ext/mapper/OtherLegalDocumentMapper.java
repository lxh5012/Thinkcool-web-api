package com.authine.cloudpivot.ext.mapper;

import com.authine.cloudpivot.ext.queryVo.QueryOtherLegalDocument;
import com.authine.cloudpivot.ext.vo.OtherLegalDocumentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherLegalDocumentMapper {

    public List<OtherLegalDocumentVO> getOtherLegalDocumentList(@Param("queryOtherLegalDocument") QueryOtherLegalDocument queryOtherLegalDocument);

}
