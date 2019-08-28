package com.authine.cloudpivot.ext.controller;


import com.authine.cloudpivot.engine.api.model.application.AppFunctionModel;
import com.authine.cloudpivot.engine.api.model.runtime.WorkItemModel;
import com.authine.cloudpivot.engine.api.model.runtime.WorkflowInstanceModel;
import com.authine.cloudpivot.engine.api.model.workflow.WorkflowTemplateHeaderModel;
import com.authine.cloudpivot.engine.api.model.workflow.WorkflowTemplateModel;
import com.authine.cloudpivot.engine.api.model.workflow.WorkflowTokenModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.engine.enums.status.WorkItemStatus;
import com.authine.cloudpivot.engine.enums.status.WorkflowInstanceStatus;
import com.authine.cloudpivot.engine.enums.status.WorkflowTokenStatus;
import com.authine.cloudpivot.engine.enums.type.WorkItemType;
import com.authine.cloudpivot.engine.workflow.enums.ActivityType;
import com.authine.cloudpivot.engine.workflow.model.activity.Activity;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.exception.PortalException;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.authine.cloudpivot.web.api.view.runtime.ActionHeaderVO;
import com.authine.cloudpivot.web.api.view.runtime.ActivityVO;
import com.authine.cloudpivot.web.api.view.runtime.ParticipantVO;
import com.authine.cloudpivot.web.api.view.runtime.WorkflowIntanceBaseInfoVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/api/thinkCool/workflow")
public class FormDataUpdateController extends BaseController {


    /**
     * 盛鸪定制化，不用admin账号也可以将已结束流程重新发起
     */
    @GetMapping({"/get_instance_baseinfo"})
    public ResponseResult<WorkflowIntanceBaseInfoVO> getWorkflowIntanceBaseInfoById(@NotBlank(message = "流程实例id不允许为空") String workflowInstanceId) {
        return this.handleWorkflowIntanceBaseInfo(workflowInstanceId);
    }


    public ResponseResult<WorkflowIntanceBaseInfoVO> handleWorkflowIntanceBaseInfo(String workflowInstanceId) {
        if (log.isDebugEnabled()) {
            log.debug("load instance base info...workflowInstanceId = {}", workflowInstanceId);
        }

        String userId = this.getUserId();
        WorkflowIntanceBaseInfoVO baseInfo = new WorkflowIntanceBaseInfoVO();
        WorkflowInstanceModel instanceModel = this.getWorkflowInstanceFacade().getWorkflowInstance(workflowInstanceId);
        if (instanceModel == null) {
            throw new PortalException(ErrCode.WORKFLOW_INS_EMPTY);
        } else {
            WorkflowTemplateHeaderModel headerModel = this.getAppManagementFacade().getWorkflowTemplateHeader(instanceModel.getWorkflowCode());
            if (headerModel == null) {
                throw new PortalException(ErrCode.WORKFLOW_NOT_EXIST);
            } else {
                AppFunctionModel appFunction = this.getAppManagementFacade().getAppFunctionByCode(headerModel.getSchemaCode());
                //所有调用该接口的用户都有权限
                Boolean editPermit = true;
                if (appFunction == null) {
                    throw new PortalException(ErrCode.APP_FUNCTION_MODEL_NOTEXIST);
                } else {
                    ActionHeaderVO headerAction = new ActionHeaderVO(editPermit);
                    baseInfo.setHeaderAction(headerAction);
                    baseInfo.setWorkflowName(headerModel.getWorkflowName());
                    baseInfo.setName_i18n(headerModel.getName_i18n());
                    baseInfo.setStatus(instanceModel.getState());
                    baseInfo.setStartTime(instanceModel.getStartTime());
                    if (instanceModel.getState() != WorkflowInstanceStatus.PROCESSING && instanceModel.getState() != WorkflowInstanceStatus.EXCEPTION) {
                        if (instanceModel.getState() == WorkflowInstanceStatus.COMPLETED) {
                            baseInfo.setFinishTime(instanceModel.getFinishTime());
                        } else if (instanceModel.getState() == WorkflowInstanceStatus.CANCELED) {
                            baseInfo.setCancelTime(instanceModel.getFinishTime());
                        }
                    } else {
                        baseInfo.getUsedTime();
                    }

                    baseInfo.setParticipants(this.getCurrentParticipants(workflowInstanceId));
                    return this.getOkResponseResult(baseInfo, "成功调用获取流程基础信息");
                }
            }
        }
    }


    private List<ActivityVO> getCurrentParticipants(String workflowInstanceId) {
        List<WorkItemModel> workItemModels = this.getWorkflowInstanceFacade().getWorkItems(workflowInstanceId, false);
        List<ActivityVO> activityVOList = new ArrayList();
        WorkflowInstanceModel parentWorkflowInstance = this.getWorkflowInstanceFacade().getWorkflowInstance(workflowInstanceId);
        WorkflowTemplateModel template = this.getAppManagementFacade().getPublishedWorkflowTemplate(parentWorkflowInstance.getWorkflowCode(), parentWorkflowInstance.getWorkflowVersion());
        List<WorkflowTokenModel> workflowtokens = this.getWorkflowInstanceFacade().getWorkflowTokens(workflowInstanceId);
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(workItemModels)) {
            this.setSubWorkflowModel(workflowInstanceId, template, activityVOList);
            this.checkCurrentSystemParticipants(template, workflowtokens, activityVOList);
            return activityVOList;
        } else {
            Map<String, List<WorkItemModel>> modelMap = (Map)workItemModels.stream().collect(Collectors.groupingBy(WorkItemModel::getActivityCode));
            Map<String, List<Activity>> listMap = (Map)template.getActivities().stream().collect(Collectors.groupingBy(Activity::getActivityCode));
            Iterator var9 = modelMap.entrySet().iterator();

            while(var9.hasNext()) {
                Map.Entry<String, List<WorkItemModel>> entry = (Map.Entry)var9.next();
                ActivityVO activityVO = new ActivityVO();
                activityVO.setActivityName(((WorkItemModel)((List)entry.getValue()).get(0)).getActivityName());
                activityVO.setName_i18n(((Activity)((List)listMap.get(((WorkItemModel)((List)entry.getValue()).get(0)).getActivityCode())).get(0)).getName_i18n());
                List<ParticipantVO> participants = Lists.newArrayList();
                Iterator var13 = ((List)entry.getValue()).iterator();

                while(var13.hasNext()) {
                    WorkItemModel model = (WorkItemModel)var13.next();
                    if (model.getState() != WorkItemStatus.CANCELLED && model.getState() != WorkItemStatus.FINISHED) {
                        ParticipantVO participantVO = new ParticipantVO();
                        participantVO.setId(model.getParticipant());
                        participantVO.setName(model.getParticipantName());
                        participantVO.setSourceId(model.getSourceId());
                        participantVO.setSourceName(model.getSourceName());
                        participantVO.setWorkItemType(model.getWorkItemType());
                        participants.add(participantVO);
                    }
                }

                activityVO.setParticipants(participants);
                activityVOList.add(activityVO);
            }

            this.checkCurrentSystemParticipants(template, workflowtokens, activityVOList);
            this.setSubWorkflowModel(workflowInstanceId, template, activityVOList);
            return activityVOList;
        }
    }


    private void setSubWorkflowModel(String workflowInstanceId, WorkflowTemplateModel template, List<ActivityVO> activityVOList) {
        List<WorkflowTokenModel> tokens = this.getWorkflowInstanceFacade().getWorkflowTokens(workflowInstanceId);
        List<WorkflowInstanceModel> subWorkflowInstance = this.getWorkflowInstanceFacade().getWorkflowInstanceByParentId(workflowInstanceId);
        if (subWorkflowInstance != null && subWorkflowInstance.size() > 0) {
            Iterator var6 = subWorkflowInstance.iterator();
            if (var6.hasNext()) {
                WorkflowInstanceModel instance = (WorkflowInstanceModel)var6.next();
                String activityName = "";
                String activityName_i18n = "";
                Optional<WorkflowTokenModel> tokenOptional = tokens.stream().filter((t) -> {
                    return Objects.equals(t.getId(), instance.getWorkflowTokenId());
                }).findFirst();
                if (tokenOptional.isPresent()) {
                    String activityCode = ((WorkflowTokenModel)tokenOptional.get()).getActivityCode();
                    Activity activity = template.getActivityByActivityCode(activityCode);
                    if (activity != null) {
                        activityName = activity.getActivityName();
                        activityName_i18n = activity.getName_i18n();
                    }
                }

                ActivityVO activityVO = new ActivityVO();
                List<ParticipantVO> participantVOList = new ArrayList();
                ParticipantVO participantVO = new ParticipantVO();
                participantVO.setId("---");
                participantVO.setName("---");
                participantVO.setSourceId("---");
                participantVO.setSourceName("---");
                participantVO.setWorkItemType(WorkItemType.SUB_INSTANCE);
                participantVOList.add(participantVO);
                activityVO.setActivityName(activityName);
                activityVO.setParticipants(participantVOList);
                activityVO.setName_i18n(activityName_i18n);
                activityVOList.add(activityVO);
            }
        }
    }



    private void checkCurrentSystemParticipants(WorkflowTemplateModel template, List<WorkflowTokenModel> workflowtokens, List<ActivityVO> activityVOList) {
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(workflowtokens)) {
            WorkflowTokenModel systemActiveWorkflow = null;
            Iterator var5 = workflowtokens.iterator();

            while(var5.hasNext()) {
                WorkflowTokenModel tokenModel = (WorkflowTokenModel)var5.next();
                if (ActivityType.SYSTEM_ACTIVITY == tokenModel.getActivityType() && WorkflowTokenStatus.UNFINISHED == tokenModel.getState()) {
                    systemActiveWorkflow = tokenModel;
                    break;
                }
            }

            if (systemActiveWorkflow != null && template != null) {
                Activity activity = template.getActivityByActivityCode(systemActiveWorkflow.getActivityCode());
                if (activity != null && activityVOList != null) {
                    ActivityVO activityVO = new ActivityVO();
                    List<ParticipantVO> participantVOList = new ArrayList();
                    ParticipantVO participantVO = new ParticipantVO();
                    participantVO.setId("---");
                    participantVO.setName("---");
                    participantVO.setSourceId("---");
                    participantVO.setSourceName(activity.getActivityName());
                    participantVO.setWorkItemType(WorkItemType.NORMAL);
                    participantVOList.add(participantVO);
                    activityVO.setParticipants(participantVOList);
                    activityVO.setActivityName(activity.getActivityName());
                    activityVO.setParticipants(participantVOList);
                    activityVO.setName_i18n(activity.getName_i18n());
                    activityVOList.add(activityVO);
                }
            }
        }

    }

}
