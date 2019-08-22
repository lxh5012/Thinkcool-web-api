package com.authine.cloudpivot.web.api.app;

import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.web.api.WebAPIApplicationTests;
import com.authine.cloudpivot.web.api.view.app.AppPackageVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * 应用中心控制器测试类
 *
 * @author linjh
 */
@Slf4j
public class AppPackageControllerTest extends WebAPIApplicationTests {

    private static AppPackageVO appPackageVO;

    @Before()
    public void setup() {
        appPackageVO = new AppPackageVO();
        appPackageVO.setCode("dddd");
        appPackageVO.setName("bbbb2");
        appPackageVO.setLogoUrl("/xxxx.jpg");
        appPackageVO.setEnabled(true);
    }

    @Test
    public void testListAllSimpleAppPackages() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/app/apppackage/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListAllAppPackages() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/app/apppackage/trees").param("appCode", "appCode"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListAppPackageByAppCode() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/app/apppackage/get").param("appCode", appPackageVO.getCode()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testAddAppPackage() throws Exception {
        String requestJson = JSONObject.toJSONString(appPackageVO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/app/apppackage/create")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUpdateAppPackage() throws Exception {
        String requestJson = JSONObject.toJSONString(appPackageVO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/app/apppackage/update")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDeleteAppPackage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/app/apppackage/delete").param("appCode", "appCode"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testBatchUpdateAppPackagesSortKeys() throws Exception {
        String content = "{\"appCode\":\"a\",\"sortKey\":1}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/app/apppackage/sortkeys")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testBatchUpdateAppFunctionSortKeys() throws Exception {
        String content = "{\"id\":\"aaa\",\"parentId\":\"bbb\",\"sortKey\":1}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/app/apppackage/appfuncsortkeys")
                .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testCheckBizModelsByAppCode() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/app/apppackage/bizmodels/exits")
                .param("appCode", "appCode"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListFolderByAppCode() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/app/apppackage/folders").param("appCode", appPackageVO.getCode()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }
}
