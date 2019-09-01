package com.authine.cloudpivot.ext.serviceImpl;

import cn.hutool.http.HttpUtil;
import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.ext.PageUtils;
import com.authine.cloudpivot.ext.mapper.SummaryTaskMapper;
import com.authine.cloudpivot.ext.service.SummaryTaskService;
import com.authine.cloudpivot.ext.utils.NetworkUtil;
import com.authine.cloudpivot.ext.vo.*;
import com.authine.cloudpivot.web.api.dubbo.DubboConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
@Slf4j
public class SummaryTaskServiceImpl implements SummaryTaskService {

    @Autowired
    private SummaryTaskMapper summaryTaskMapper;

    @Autowired
    private DubboConfigService dubboConfigService;

    @Autowired
    private HttpServletRequest request;

    public OrganizationFacade getOrganizationFacade() {
        return dubboConfigService.getOrganizationFacade();
    }

    @Override
    public PageResult queryProjectTask(SummaryTaskVO summaryTaskVO) {
        int pageNum = summaryTaskVO.getPage();
        int pageSize = summaryTaskVO.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<SummaryTaskVO> summaryList = summaryTaskMapper.queryProjectTask(summaryTaskVO);
        PageInfo<SummaryTaskVO> summaryPage = new PageInfo<>(summaryList);
        return PageUtils.getPageResult(summaryPage);
    }

    @Override
    public PageResult queryDeliverableTask(DeliverableTaskVO deliverableTaskVO) {
        int pageNum =deliverableTaskVO.getPage();
        int pageSize = deliverableTaskVO.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<DeliverableTaskVO> deliverableTaskList = summaryTaskMapper.queryDeliverableTask(deliverableTaskVO);
        //获取deliverableTaskList所有deadline的集合
        Date maxDate = new Date();
        if(CollectionUtils.isNotEmpty(deliverableTaskList) && Objects.nonNull(deliverableTaskList.get(0))){
            maxDate = deliverableTaskList.get(0).getDeadline();
        }
        for (DeliverableTaskVO  deliverableTask: deliverableTaskList) {
            if(Objects.nonNull(deliverableTask) && Objects.nonNull(deliverableTask.getDeadline()) ){
                if(Objects.isNull(maxDate) || maxDate.before(deliverableTask.getDeadline())){
                    maxDate = deliverableTask.getDeadline();
                }
            }
        }
        //将日期转换为制定形式
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String resDate = "";
        if(Objects.nonNull(maxDate)){
            resDate = format.format(maxDate);
        }
        StringBuffer dispatchUrl = new StringBuffer();
        String ip = NetworkUtil.getIPAddress(request);
        dispatchUrl.append("http://47.103.123.171/form/detail?startWorkflowCode=DispatchSheet&return=%2Fworkflow-center%2Fstart-workflow");
        for (DeliverableTaskVO  deliverableTask: deliverableTaskList) {
            deliverableTask.setDeadlineShow(resDate);
            deliverableTask.setDispatchUrl(dispatchUrl.toString());
        }
        PageInfo<DeliverableTaskVO> deliverableTaskPage = new PageInfo<>(deliverableTaskList);
        return PageUtils.getPageResult(deliverableTaskPage);
    }

    @Override
    public PageResult queryTaskDetial(TaskDetialVO taskDetialVO) {
        int pageNum =taskDetialVO.getPage();
        int pageSize = taskDetialVO.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<TaskDetialVO> taskDetialList = summaryTaskMapper.queryTaskDetial(taskDetialVO);
        for (TaskDetialVO res: taskDetialList) {
            if(Objects.nonNull(res.getTaskDistributors())){
                String taskDistributors = res.getTaskDistributors();
                String userNames = getUserNameById(taskDistributors);
                res.setTaskDistributorUserName(userNames);
            }
            if(Objects.nonNull(res.getTaskOwners())){
                String TaskOwners = res.getTaskOwners();
                String userNames = getUserNameById(res.getTaskOwners());
                res.setTaskOwnerUserName(userNames);
            }
            //获取请求IP和端口号
            String approveUrl = getUnfinshRequestUrl(res.getWorkItemId(), res.getWorkflowInstanceId());
            res.setApproveUrl(approveUrl);
            log.info("-------------派单代办URL   start---------------");
            log.info(approveUrl.toString());
            log.info("-------------派单代办URL   end---------------");
        }
        PageInfo<TaskDetialVO> taskDetialPage = new PageInfo<>(taskDetialList);
        return PageUtils.getPageResult(taskDetialPage);
    }

    @Override
    public PageResult acceptTaskList(AcceptTaskVO acceptTaskVO) {
        int pageNum =acceptTaskVO.getPage();
        int pageSize = acceptTaskVO.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<AcceptTaskVO> acceptTaskList = summaryTaskMapper.acceptTaskList(acceptTaskVO);
        if(CollectionUtils.isNotEmpty(acceptTaskList)){
            Date maxDate = new Date();
            if(Objects.nonNull(acceptTaskList.get(0))){
                maxDate = acceptTaskList.get(0).getDeadline();
            }
            for (AcceptTaskVO res: acceptTaskList) {
                if(Objects.nonNull(res)){
                    if(Objects.nonNull(res.getTaskDistributors())){
                        String taskDistributors = res.getTaskDistributors();
                        String userNames = getUserNameById(taskDistributors);
                        res.setTaskDistributorNames(userNames);
                    }
                    //接单处理按钮链接
                    if(Objects.nonNull(res.getWorkItemId()) && Objects.nonNull(res.getWorkflowInstanceId())){
                        //获取请求IP和端口号
                        String approveUrl = getUnfinshRequestUrl(res.getWorkItemId(), res.getWorkflowInstanceId());
                        res.setTaskUrl(approveUrl);
                        log.info("-------------接单URL---------------");
                        log.info(approveUrl.toString());
                    }
                    if(Objects.nonNull(res) && Objects.nonNull(res.getDeadline()) ){
                        if(Objects.isNull(maxDate) || maxDate.before(res.getDeadline())){
                            maxDate = res.getDeadline();
                        }
                    }
                }
            }
            //将日期转换为制定形式
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String resDate = "";
            if(Objects.nonNull(resDate)){
                resDate = format.format(maxDate);
            }

            for (AcceptTaskVO res: acceptTaskList) {
                res.setDeadlineShow(resDate);
            }
        }
        PageInfo<AcceptTaskVO> acceptTasPage = new PageInfo<>(acceptTaskList);
        return PageUtils.getPageResult(acceptTasPage);
    }


    private String getUnfinshRequestUrl(String workItemId, String workflowInstanceId){
        String ip = NetworkUtil.getIPAddress(request);
        int port = request.getServerPort();
        StringBuffer approveUrl = new StringBuffer();
        approveUrl.append("http://47.103.123.171");
        approveUrl.append("/form/detail?");
        if(Objects.nonNull(workflowInstanceId)){
            approveUrl.append("workflowInstanceId=" + workflowInstanceId);
            approveUrl.append("&");
        }
        if(Objects.nonNull(workItemId)){
            approveUrl.append("workitemId=" + workItemId);
        }
        approveUrl.append(workflowInstanceId);
        return approveUrl.toString();
    }


    private String getUserNameById(String userIds){
        if(Objects.nonNull(userIds)){
            StringBuffer userNames = new StringBuffer();
            JSONArray jsonArray = JSONArray.fromObject(userIds);
            List<Map<String,Object>> mapList = (List)jsonArray;
            for (Map<String,Object>  map: mapList) {
                if(Objects.nonNull(map)){
                    String userId = map.get("id").toString();
                    UserModel userModel = getOrganizationFacade().getUser(userId);
                    userNames = userNames.append(userModel.getName() + ",");
                }
            }
            return userNames.toString();
        }
        return null;
    }


}
