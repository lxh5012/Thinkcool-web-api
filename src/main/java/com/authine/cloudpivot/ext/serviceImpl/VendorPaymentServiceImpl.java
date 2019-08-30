package com.authine.cloudpivot.ext.serviceImpl;

import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.VendorPaymentMapper;
import com.authine.cloudpivot.ext.queryVo.QueryVendorPayment;
import com.authine.cloudpivot.ext.service.VendorPaymentService;
import com.authine.cloudpivot.ext.vo.VendorPaymentVO;
import com.authine.cloudpivot.ext.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="vendorPaymentServiceImpl")
public class VendorPaymentServiceImpl implements VendorPaymentService {
    @Autowired
    public VendorPaymentMapper VendorPaymentMapper;

    @Override
    public PageResult getVendorPaymentList(QueryVendorPayment queryVendorPayment) {
        int pageNum =queryVendorPayment.getPage();
        int pageSize = queryVendorPayment.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<VendorPaymentVO> VendorPaymentList = VendorPaymentMapper.getVendorPaymentList(queryVendorPayment);
        PageInfo<VendorPaymentVO> VendorPaymentVOVOPageInfo = new PageInfo<>(VendorPaymentList);
        return PageUtils.getPageResult(VendorPaymentVOVOPageInfo);
    }
}
