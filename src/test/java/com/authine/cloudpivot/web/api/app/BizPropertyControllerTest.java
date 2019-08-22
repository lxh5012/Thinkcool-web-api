package com.authine.cloudpivot.web.api.app;

import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.engine.api.model.bizmodel.BizPropertyModel;
import com.authine.cloudpivot.engine.api.model.bizmodel.BizSchemaModel;
import com.authine.cloudpivot.engine.enums.type.BizPropertyType;
import com.authine.cloudpivot.web.api.WebAPIApplicationTests;
import com.authine.cloudpivot.web.api.view.app.BizPropertyVO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

/**
 * @ClassName: DataItemControllerTest
 * @Description: 数据项单元测试
 * @Author: dengchao
 * @Date: 2018年10月29日
 * @Version 1.0
 */
@Slf4j
public class BizPropertyControllerTest extends WebAPIApplicationTests {

    //关联子表
    private static BizPropertyVO bizPropertyModelVOSubSchema = new BizPropertyVO();
    private static BizSchemaModel bizParentSchemaModel = new BizSchemaModel();

    //关联短文本
    private static BizPropertyVO bizPropertyModelVOShortText = new BizPropertyVO();
    //关联表单
    private static BizPropertyVO bizPropertyModelVOTable = new BizPropertyVO();

    @BeforeClass
    public static void setUp() {
        bizParentSchemaModel.setPublished(true);
        bizParentSchemaModel.setName("数据模型名称");
        bizParentSchemaModel.setIcon("http://www.baidu.com");
        bizParentSchemaModel.setCode("sub123456");
        List<BizPropertyModel> bizPropertyModelList = Lists.newArrayList();
        /********test关联子表**********/
        //关联子表测试数据
        bizPropertyModelVOSubSchema.setSchemaCode(bizParentSchemaModel.getCode());

        //子表数据模型
        BizSchemaModel bizSubSchemaModel = new BizSchemaModel();
        List<BizPropertyModel> bizSubPropertyModelList = Lists.newArrayList();
        BizPropertyModel bizPropertyModel = new BizPropertyModel();
        bizPropertyModel.setPropertyType(BizPropertyType.LONG_TEXT);
        bizPropertyModel.setCode("BIZ_ADDRESS");
        bizPropertyModel.setSchemaCode(bizParentSchemaModel.getCode());
        bizPropertyModel.setName("地址");
        bizPropertyModel.setPropertyLength(50000);
        bizPropertyModel.setPropertyIndex(true);
        bizPropertyModel.setPropertyEmpty(false);
        bizPropertyModel.setDefaultValue("请填写您的地址");

        BizPropertyModel bizPropertyModel2 = new BizPropertyModel();
        bizPropertyModel2.setSchemaCode(bizParentSchemaModel.getCode());
        bizPropertyModel2.setCode("BIZ_ORDER");
        bizPropertyModel2.setName("子表订单编码");
        bizPropertyModel2.setPropertyType(BizPropertyType.SHORT_TEXT);
        bizPropertyModel2.setPropertyLength(200);
        bizPropertyModel2.setPropertyEmpty(false);
        bizPropertyModel2.setDefaultValue("请填写您的地址");

        bizSubPropertyModelList.add(bizPropertyModel);
        bizSubPropertyModelList.add(bizPropertyModel2);
        bizSubSchemaModel.setProperties(bizSubPropertyModelList);
        bizPropertyModelVOSubSchema.setSubSchema(bizSubSchemaModel);


        /********test关联短文本**********/
        bizPropertyModelVOShortText.setCode("testshort");
        bizPropertyModelVOShortText.setName("测试短文本");
        bizPropertyModelVOShortText.setSchemaCode(bizParentSchemaModel.getCode());
        bizPropertyModelVOShortText.setPropertyType(BizPropertyType.SHORT_TEXT);
        bizPropertyModelVOShortText.setPropertyEmpty(false);
        bizPropertyModelVOShortText.setPropertyIndex(true);
        /********test关联短文本**********/

        /********test关联表单**********/
        bizPropertyModelVOTable.setPropertyType(BizPropertyType.WORK_SHEET);
        bizPropertyModelVOTable.setCode("testshort");
        bizPropertyModelVOTable.setName("测试短文本");
        bizPropertyModelVOTable.setSchemaCode(bizParentSchemaModel.getCode());
        bizPropertyModelVOTable.setRelativeCode("table_code");

        /********test关联表单**********/

        //添加关联子表数据
        bizPropertyModelList.add(bizPropertyModelVOSubSchema);
        bizPropertyModelList.add(bizPropertyModelVOShortText);
        bizPropertyModelList.add(bizPropertyModelVOTable);

        bizParentSchemaModel.setProperties(bizPropertyModelList);

    }


    /**
     * 保存数据项单元测试
     *
     * @throws Exception
     */
    @Test
    public void addBizProperty() throws Exception {
        String requestJson = JSONObject.toJSONString(bizPropertyModelVOSubSchema);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/app/bizproperty/create")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

}
