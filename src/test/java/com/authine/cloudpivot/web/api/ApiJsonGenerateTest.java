package com.authine.cloudpivot.web.api;

import com.authine.cloudpivot.engine.api.model.bizmodel.BizPropertyModel;
import com.authine.cloudpivot.engine.api.model.bizmodel.BizSchemaModel;
import com.authine.cloudpivot.engine.enums.type.BizPropertyType;
import com.authine.cloudpivot.web.api.view.app.BizPropertyVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: 生成 json 字符串
 * @Author: dengchao
 * @Date: 2018年11月19日
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@Slf4j
public class ApiJsonGenerateTest {

    public static <T> void logResult(T t) {
//        String resultJson = JSONObject.toJSONString(t);
        String resultJson = ToStringBuilder.reflectionToString(t, ToStringStyle.JSON_STYLE);

        log.debug("\n ====== {\"errcode\": 0,\"errmsg\": \"成功\",\"data\": {}}", resultJson);

    }

    @Test
    public void geneerateJson() {
        BizSchemaModel bizSchemaModel = new BizSchemaModel();
        logResult(bizSchemaModel);
    }

    @Test
    public void test() {
        BizPropertyModel bizPropertyModel = new BizPropertyModel();
        bizPropertyModel.setPropertyType(BizPropertyType.LONG_TEXT);
        String resultJson = ToStringBuilder.reflectionToString(bizPropertyModel, ToStringStyle.JSON_STYLE);

        log.debug("\n={}", resultJson);
    }

    @Test
    public void generateBizPropertyModelVOTest() {
        BizPropertyModel bizProperty = new BizPropertyModel();
        bizProperty.setPropertyType(BizPropertyType.LONG_TEXT);
        BizPropertyVO bizPropertyModelVO = new BizPropertyVO();
        BeanUtils.copyProperties(bizProperty, bizPropertyModelVO);
        logResult(bizPropertyModelVO);
    }

    @Test
    public void testlist() {
        BizSchemaModel bizSchemaModel = new BizSchemaModel();
        bizSchemaModel.setCode("100000");
        bizSchemaModel.setIcon("http://www.baidu.com");
        bizSchemaModel.setName("测试模型");
        bizSchemaModel.setPublished(false);
        bizSchemaModel.setSummary("测试摘要");

        BizPropertyModel bizPropertyModel = new BizPropertyModel();
        bizPropertyModel.setSchemaCode("100000");//所属数据模型对象Code
        bizPropertyModel.setPropertyType(BizPropertyType.LONG_TEXT);
        bizPropertyModel.setDefaultValue("default,base");//属性默认值，数组使用',' 进行分隔
        bizPropertyModel.setCode("10001");
        bizPropertyModel.setName("测试数据项");
        bizPropertyModel.setPropertyEmpty(false);
        bizPropertyModel.setPropertyIndex(true);
        bizPropertyModel.setPropertyLength(10);//数据项长度
        bizPropertyModel.setSubSchema(null);
        bizPropertyModel.setRelativeCode("xxxx");//如果数据类型为“关联表单”，该字段保存模型表对应的模型Code

        List<BizPropertyModel> bizPropertyModelList = Lists.newArrayList();
        bizPropertyModelList.add(bizPropertyModel);
        bizSchemaModel.setProperties(bizPropertyModelList);
    }

    @Test
    public void addBizProperty() {

    }
}
