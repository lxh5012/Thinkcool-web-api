package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.ClientContractMapper;
import com.authine.cloudpivot.ext.queryVo.QueryClientContract;
import com.authine.cloudpivot.ext.service.ClientContractService;
import com.authine.cloudpivot.ext.vo.ClientContractVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientContractServiceImpl implements ClientContractService {

    @Autowired
    private ClientContractMapper clientContractMapper;


    @Override
    public PageResult getClientContractList(QueryClientContract queryClientContract) {
        int pageNum = queryClientContract.getPageNum() == 0?1:queryClientContract.getPageNum();
        int pageSize = queryClientContract.getPageSize() == 0?10:queryClientContract.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ClientContractVO> clientContractList = clientContractMapper.getClientContractList(queryClientContract);
        for(ClientContractVO clientContractVO:clientContractList){
            clientContractVO.setFormUrl(getFormUrl(clientContractVO.getId()));
        }
        PageInfo<ClientContractVO> clientContractVOPageInfo = new PageInfo<>(clientContractList);
        PageResult pageResult = PageUtils.getPageResult(clientContractVOPageInfo);
        return pageResult;

    }

    private String getFormUrl(String id){
        String ip = "47.103.123.171";
        StringBuffer fromUrl = new StringBuffer();
        fromUrl.append("http://");
        fromUrl.append(ip);
        fromUrl.append("/form/detail?");
        fromUrl.append("sheetCode=ClientContract");
        fromUrl.append("&objectId=").append(id);
        fromUrl.append("&schemaCode=ClientContract");
        fromUrl.append("&return=/application/ProjectSummary/application-list/ClientContract?parentId=2c93208b6c9e0bc6016c9e36d7ac0011");
        fromUrl.append("&code=ClientContract");
        fromUrl.append("&openMode");
        fromUrl.append("&pcUrl");
        return fromUrl.toString();
    }
}
