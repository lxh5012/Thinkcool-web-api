package com.authine.cloudpivot.web.api.app;

import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.engine.enums.type.NodeType;
import com.authine.cloudpivot.web.api.WebAPIApplicationTests;
import com.authine.cloudpivot.web.api.view.app.AppFunctionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

/**
 * 目录中心控制器测试类
 *
 * @author linjh
 */
@Slf4j
public class BizModelControllerTest extends WebAPIApplicationTests {

    private static AppFunctionVO appFunctionVO;

    @Before()
    public void setup() {
        appFunctionVO = new AppFunctionVO();
        appFunctionVO.setId(UUID.randomUUID().toString());
        appFunctionVO.setAppCode("aaaa");
        appFunctionVO.setName("bbbb");
        appFunctionVO.setBizSchemaCode("dddd");
        appFunctionVO.setIcon("/cccc.jpg");
        appFunctionVO.setType(NodeType.BizModel);
        appFunctionVO.setParentId(UUID.randomUUID().toString());
    }

    @Test
    public void testAddBizModel() throws Exception {
        String requestJson = JSONObject.toJSONString(appFunctionVO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/app/bizmodels/create")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUpdateBizModel() throws Exception {
        String requestJson = JSONObject.toJSONString(appFunctionVO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/app/bizmodels/update")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDeleteBizModel() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/app/bizmodels/delete").param("bizModelId", "appCode"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testCheckBizSchemasByBizModelId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/app/bizmodels/bizschemas/exits").param("appCode", "appCode").param("bizModelId", "bizModelId"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListAllBizModelsByBizModelId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/app/bizmodels/list").param("appCode", "a1").param("bizModelId", "402886576771eca3016771fa8a16001c"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }
}
