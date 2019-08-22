package com.authine.cloudpivot.web.api.app;

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
 * 公式函数控制器测试类
 *
 * @author linjh
 */
@Slf4j
public class FunctionControllerTest extends WebAPIApplicationTests {

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
    public void testValidateFormula() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/function/validate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("function", UUID.randomUUID().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListFormulaTrees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/function/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListFormulaTreesById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/function/get").param("id", UUID.randomUUID().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListOrgTreesById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/function/org/tree").param("id", UUID.randomUUID().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

}
