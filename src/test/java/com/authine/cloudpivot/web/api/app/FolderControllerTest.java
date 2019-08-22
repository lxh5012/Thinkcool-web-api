package com.authine.cloudpivot.web.api.app;

import com.alibaba.fastjson.JSONObject;
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
public class FolderControllerTest extends WebAPIApplicationTests {

    private static AppFunctionVO appFunctionVO;

    @Before()
    public void setup() {
        appFunctionVO = new AppFunctionVO();
        appFunctionVO.setAppCode("aaaa");
        appFunctionVO.setName("bbbb");
        appFunctionVO.setIcon("/cccc.jpg");
        appFunctionVO.setParentId(UUID.randomUUID().toString());
    }

    @Test
    public void testAddFolder() throws Exception {
        String requestJson = JSONObject.toJSONString(appFunctionVO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/app/folders/create")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListFolderByFolderId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/app/folders/get").param("folderId", UUID.randomUUID().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUpdateFolder() throws Exception {
        String requestJson = JSONObject.toJSONString(appFunctionVO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/app/folders/update")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDeleteFolder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/app/folders/delete").param("folderId", "appCode"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testCheckBizModelsByFolderId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/app/folders/bizschemas/exits").param("appCode", "appCode").param("folderId", "folderId"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

}
