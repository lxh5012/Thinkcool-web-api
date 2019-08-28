package com.authine.cloudpivot.web.api.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.authine.cloudpivot.engine.api.model.bizmodel.BizPropertyModel;
import com.authine.cloudpivot.engine.api.model.bizmodel.BizSchemaModel;
import com.authine.cloudpivot.engine.api.model.bizquery.BizQueryColumnModel;
import com.authine.cloudpivot.engine.api.model.bizquery.BizQueryHeaderModel;
import com.authine.cloudpivot.engine.api.model.bizquery.BizQueryModel;
import com.authine.cloudpivot.engine.api.model.runtime.*;
import com.authine.cloudpivot.engine.component.query.api.Page;
import com.authine.cloudpivot.engine.component.query.api.helper.PageableImpl;
import com.authine.cloudpivot.engine.enums.status.SequenceStatus;
import com.authine.cloudpivot.engine.enums.type.BizObjectType;
import com.authine.cloudpivot.engine.enums.type.BizPropertyType;
import com.authine.cloudpivot.engine.enums.type.DefaultPropertyType;
import com.authine.cloudpivot.web.api.controller.base.BaseQueryRuntimeController;
import com.authine.cloudpivot.web.api.entity.SummaryTaskModel;
import com.authine.cloudpivot.web.api.mapper.SummaryTaskMapper;
import com.authine.cloudpivot.web.api.service.SummaryTaskService;
import com.authine.cloudpivot.web.api.view.PageVO;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import com.authine.cloudpivot.web.api.view.runtime.FilterVO;
import com.authine.cloudpivot.web.api.view.runtime.QueryDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@Api(value = "查询派工列表数据接口", tags = "Runtime::Query")
@RestController
@RequestMapping("/formList/query")
@Slf4j
public class ThinkCoolFormList extends BaseQueryRuntimeController {


    @Autowired
    private SummaryTaskMapper summaryTaskMapper;

    @ApiOperation(value = "查询数据接口")
    @GetMapping("/count")
    public Integer count() {
        return summaryTaskMapper.getcount();
    }

    @ApiOperation(value = "查询数据接口")
    @PostMapping("/list")
    public ResponseResult<PageVO<BizObjectModel>> list(@RequestBody QueryDataVO queryData) {
        validateNotEmpty(queryData.getSchemaCode(), "模型编码不能为空");
        BizSchemaModel bizSchema = getAppManagementFacade().getBizSchemaBySchemaCode(queryData.getSchemaCode());
        parseFilterVo(queryData,bizSchema);
        if (log.isDebugEnabled()) {
            log.debug("用于查询的条件为：【{}】", (Object) (queryData == null ? null : (queryData.getFilters() == null ? null : JSON.toJSONString(queryData.getFilters()))));
        }
        String userId = getUserId();
        log.debug("用户id为={}",userId);
        Page<BizObjectModel> data = getBizObject(userId, queryData,false,true);
        data.getContent().get(0);
        if (data==null|| CollectionUtils.isEmpty(data.getContent())) {
            this.getOkResponseResult(new PageVO<>(org.springframework.data.domain.Page.empty()), "获取数据成功");
        }
        if (queryData.getMobile() != null && queryData.getMobile()) {
            UserFavoritesModel favorite = new UserFavoritesModel();
            favorite.setUserId(getUserId());
            favorite.setBizObjectKey(queryData.getSchemaCode());
            favorite.setBizObjectType(BizObjectType.BIZ_MODEL);
            getUserSettingFacade().addUserFavoriteBizModel(favorite);
        }
        return this.getOkResponseResult(new PageVO<>(data), "获取数据成功");
    }

    /**
     * 处理数据项类型
     * @param queryData
     * @param bizSchema
     */
    private void parseFilterVo(QueryDataVO queryData, BizSchemaModel bizSchema) {
        if (CollectionUtils.isNotEmpty(bizSchema.getProperties())) {
            Map<String, List<BizPropertyModel>> listMap = bizSchema.getProperties().stream().collect(Collectors.groupingBy(BizPropertyModel :: getCode));
            if (CollectionUtils.isNotEmpty(queryData.getFilters())) {
                for (FilterVO filterVO : queryData.getFilters()) {
                    if (filterVO.getPropertyType() == null) {
                        filterVO.setPropertyType(listMap.get(filterVO.getPropertyCode()).get(0).getPropertyType());
                    }
                }
            }
        }
    }


    /**
     * 获取列表数据信息
     *
     * @param userId    用户id
     * @param queryData 查询数据结果
     * @return Page
     */
    protected Page<BizObjectModel> getBizObject(String userId, QueryDataVO queryData, Boolean isExport, Boolean isAuthFilter) {
        if (StringUtils.isEmpty(queryData.getQueryCode())) {
            //如果列表编码为空则获取默认第一个列表
            List<BizQueryHeaderModel> bizQueryHeaders = getAppManagementFacade().getBizQueryHeaders(queryData.getSchemaCode());
            queryData.setQueryCode(CollectionUtils.isNotEmpty(bizQueryHeaders) ? bizQueryHeaders.get(0).getCode() : "");
        }
        BizQueryModel bizQueryModel = getAppManagementFacade().getBizQuery(queryData.getSchemaCode(), queryData.getQueryCode());
        //生成查询条件方法
        BizObjectQueryModel bizObjectQueryModel = getBizObjectQuery(userId, queryData, bizQueryModel,isAuthFilter);
        //分页信息
        int page = queryData.getPage() == null ? 0 : queryData.getPage();
        //int page = 0;
        int size = queryData.getSize();
        PageableImpl pageable = null;
        if (isExport) {
            pageable = new PageableImpl(0, size);
        } else {
            pageable = new PageableImpl(page * size, size);
        }

        bizObjectQueryModel.setPageable(pageable);
        bizObjectQueryModel.setSchemaCode(queryData.getSchemaCode());
        bizObjectQueryModel.setQueryCode(queryData.getQueryCode());
        bizObjectQueryModel.setOptions(queryData.getOptions());
        Page<BizObjectModel> data = getBizObjectFacade().queryBizObjects(bizObjectQueryModel);
        //返回到页面的BizObjectModel
        List<BizObjectModel> resBizList = new ArrayList<>();
        long res = data.getTotal();
        //project summary可派工 列表查询
        if("Deliverable".equals(queryData.getSchemaCode())  ){
            //回传到 project summary可派工 页面的model为UnFinishTaskModel
            List<SummaryTaskModel> unFinishTaskModelList = new ArrayList<>();


            StringBuffer jobCodeSb = new StringBuffer();
            List<? extends BizObjectModel> bizList = data.getContent();
            //原来deliverable下每一个jobcode出现的次数
            Map<String, Integer> countMap = new HashMap<>();
            for (BizObjectModel bizObjectModel :bizList) {
                //获取当前对象的业务数据
                Map<String, Object> map = bizObjectModel.getData();
                //对deliverable下的数据根据jobCode进行去重出处理，如果jobCode不存在，则在返回的model中增加jobcode对对应的数据

                if(!jobCodeSb.toString().equals(map.get("Jobcode").toString())){
                    Map<String,Object> jobMap = (Map<String,Object>)map.get("Jobcode");
                    jobCodeSb = jobCodeSb.append(jobMap.get("schemaCode").toString());
                    countMap.put(map.get("Jobcode").toString(), 1);
                    resBizList.add(bizObjectModel);




                    continue;
                }
                Integer count = countMap.get(map.get("Jobcode").toString());
                countMap.put(map.get("Jobcode").toString(), count++);

            }

            //对返回结果进行封装
            for (BizObjectModel  object: resBizList) {
                Map<String,Object> resMap = object.getData();
                //对 Deliverable 数量 进行保存
                Integer count = (Integer)countMap.get(resMap.get("Jobcode").toString());
                resMap.put("deliverableNum", count);
                object.getData().putAll(resMap);
                Map<String,Object> jobMap = (Map<String,Object>)resMap.get("Jobcode");


                QueryDataVO taskQueryData = new QueryDataVO();
                taskQueryData.setSchemaCode("DispatchSheet");
                taskQueryData.setQueryCode("DispatchSheet");
                List<FilterVO> filterList = new ArrayList<>();
                FilterVO filter = new FilterVO();
                filter.setPropertyCode("Jobcode");
                filter.setPropertyValue(jobMap.get("id").toString());
                filterList.add(filter);
                taskQueryData.setFilters(filterList);
                taskQueryData.setMobile(false);
                taskQueryData.setPage(0);
                taskQueryData.setSize(20);

                BizSchemaModel taskBizSchema = getAppManagementFacade().getBizSchemaBySchemaCode(taskQueryData.getSchemaCode());
                parseFilterVo(taskQueryData,taskBizSchema);
                BizQueryModel taskizQueryModel = getAppManagementFacade().getBizQuery(taskQueryData.getSchemaCode(), taskQueryData.getQueryCode());
                //生成查询条件方法
                BizObjectQueryModel taskBizObjectQueryModel = getBizObjectQuery(userId, taskQueryData, taskizQueryModel,isAuthFilter);
                taskBizObjectQueryModel.setPageable(pageable);
                taskBizObjectQueryModel.setSchemaCode("DispatchSheet");
                taskBizObjectQueryModel.setQueryCode("DispatchSheet");
                Page<BizObjectModel> taskData = getBizObjectFacade().queryBizObjects(taskBizObjectQueryModel);
                long taskNum = taskData.getTotal();
                resMap.put("taskNum", taskNum);

                Sort sort = new Sort(Sort.Direction.DESC, "finishTime");
                Pageable pageabled = PageRequest.of(page == 0 ? 0 : page, size == 0 ? 10 : size, sort);

                //查询已办数量
                org.springframework.data.domain.Page<WorkItemModel> workItemPage = getWorkflowInstanceFacade().queryFinishedWorkItems(userId, null, null, "DispatchSheet", userId, null, null, null, null, pageabled);
                List<String> instanceIdList = workItemPage.getContent().stream().map(WorkItemModel::getInstanceId).collect(Collectors.toList());


            }


        }




        if (CollectionUtils.isNotEmpty(data.getContent())) {
            List<BizQueryColumnModel> bizQueryColumnModels = bizQueryModel.getQueryColumns();
            parseAddressAndReference(data,bizQueryColumnModels, isExport,queryData.getQueryCode());
            if (!queryData.getMobile()) {
                //处理PC端选人
                parseSpecialProperties(data, isExport);
            } else {
                //处理移动端用户图像
                disposeUserInfo(data);
            }
        }
        return data;
    }



    /**
     * 处理PC端选人控件展示
     *
     * @param data 分页数据
     */
    private void parseSpecialProperties(Page<BizObjectModel> data, Boolean isExport) {
        //获选人数据项
        BizObjectModel objectModel = data.getContent().get(0);
        BizSchemaModel bizSchema = getAppManagementFacade().getBizSchemaBySchemaCode(objectModel.getSchemaCode());
        if (isExport) {
            List<String> selectCodes = getPropertyCodeList(objectModel, BizPropertyType.SELECTION);
            if (CollectionUtils.isEmpty(selectCodes)) {
                return;
            }
            log.debug("需要处理的选人数据项为{}", JSON.toJSONString(selectCodes));
            Set<String> dataCodes = null;
            for (String selectCode : selectCodes) {
                for (BizObjectModel bizObject : data.getContent()) {
                    if (CollectionUtils.isEmpty(dataCodes)) {
                        dataCodes = bizObject.getData().keySet();
                        log.debug("需要展示的数据项为{}", JSON.toJSONString(dataCodes));
                    }
                    if (!dataCodes.contains(selectCode)) {
                        log.debug("不需要展示的选人数据项为{}", selectCode);
                        break;
                    }
                    if (bizObject.get(selectCode) == null) {
                        continue;
                    }
                    List<SelectionValue> selectionValues = (List<SelectionValue>) bizObject.get(selectCode);
                    bizObject.put(selectCode, getUserNames(selectionValues));
                }
            }
            //处理单据状态
            if (data.getContent().get(0).getData().keySet().contains(DefaultPropertyType.SEQUENCE_STATUS.getCode())) {
                for (BizObjectModel bizObjectModel : data.getContent()) {
                    bizObjectModel.put(DefaultPropertyType.SEQUENCE_STATUS.getCode(), parseSequenceStatus(bizObjectModel));
                }
            }
            //获取逻辑型
            List<BizPropertyModel> logicalModels = bizSchema.getProperties().stream().filter(t -> t.getPropertyType().equals(BizPropertyType.LOGICAL)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(logicalModels)) {
                List<String> logicals = logicalModels.stream().map(BizPropertyModel::getCode).collect(Collectors.toList());
                Set<String> logicalDataCodes = null;
                for (String logical : logicals) {
                    for (BizObjectModel bizObjectModel : data.getContent()) {
                        if (CollectionUtils.isEmpty(logicalDataCodes)) {
                            logicalDataCodes = bizObjectModel.getData().keySet();
                        }
                        if (!logicalDataCodes.contains(logical)) {
                            break;
                        }
                        if (bizObjectModel.get(logical) == null) {
                            continue;
                        }
                        bizObjectModel.put(logical, disposeLogic(bizObjectModel.getString(logical)));
                    }

                }
            }
        }

    }


    /**
     * 设置用户显示图像
     *
     * @param data 分页数据
     */
    private void disposeUserInfo(Page<BizObjectModel> data) {
        if (CollectionUtils.isEmpty(data.getContent())) {
            return;
        }
        for (BizObjectModel bizObject : data.getContent()) {
            List<SelectionValue> selectionValues = (List<SelectionValue>) bizObject.get(DefaultPropertyType.CREATER.getCode());
            if (CollectionUtils.isNotEmpty(selectionValues)) {
                bizObject.put("imgUrl", selectionValues.get(0).getImgUrl());
                bizObject.put("originator", selectionValues.get(0).getName());
            }
        }
    }

    private List<String> getPropertyCodeList(BizObjectModel objectModel, BizPropertyType propertyType) {
        BizSchemaModel bizSchema = getAppManagementFacade().getBizSchemaBySchemaCode(objectModel.getSchemaCode());
        List<BizPropertyModel> bizPropertyModels = bizSchema.getProperties().stream().filter(t -> t.getPropertyType().equals(propertyType)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(bizPropertyModels)) {
            return bizPropertyModels.stream().map(BizPropertyModel::getCode).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }


    /**
     * 处理单据状态
     *
     * @param bizObjectModel
     * @return
     */
    private Object parseSequenceStatus(BizObjectModel bizObjectModel) {
        switch (bizObjectModel.get(DefaultPropertyType.SEQUENCE_STATUS.getCode()).toString()) {
            case "DRAFT":
                return SequenceStatus.DRAFT.getName();
            case "PROCESSING":
                return SequenceStatus.PROCESSING.getName();
            case "CANCELLED":
                return SequenceStatus.CANCELLED.getName();
            case "COMPLETED":
                return SequenceStatus.COMPLETED.getName();
            default:
                return null;
        }
    }
}

