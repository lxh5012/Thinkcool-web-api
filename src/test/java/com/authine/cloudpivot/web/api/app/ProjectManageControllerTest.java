package com.authine.cloudpivot.web.api.app;

import com.authine.cloudpivot.web.api.WebAPIApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@Slf4j
public class ProjectManageControllerTest extends WebAPIApplicationTests {
    @Test
    public void testQueryProject() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/projectmanage/queryProject")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("function", UUID.randomUUID().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }
}
