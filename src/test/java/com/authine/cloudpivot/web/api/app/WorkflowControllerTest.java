package com.authine.cloudpivot.web.api.app;

import com.alibaba.fastjson.JSONObject;
import com.authine.cloudpivot.engine.api.model.workflow.WorkflowPermissionModel;
import com.authine.cloudpivot.engine.api.model.workflow.WorkflowTemplateHeaderModel;
import com.authine.cloudpivot.engine.enums.type.UnitType;
import com.authine.cloudpivot.web.api.WebAPIApplicationTests;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * 流程控制器测试类
 *
 * @author linjh
 */
@Slf4j
public class WorkflowControllerTest extends WebAPIApplicationTests {

    private static WorkflowTemplateHeaderModel workflowTemplateHeaderModel;

    private static WorkflowPermissionModel workflowPermissionModel;

    private static String requestJson = "";

    @Before()
    public void setup() {
        workflowTemplateHeaderModel = new WorkflowTemplateHeaderModel();
        workflowTemplateHeaderModel.setSchemaCode("bsc1");
        workflowTemplateHeaderModel.setWorkflowCode("a" + UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY).substring(1, 26));
        workflowTemplateHeaderModel.setWorkflowName(UUID.randomUUID().toString());

        workflowPermissionModel = new WorkflowPermissionModel();
        workflowPermissionModel.setUnitId(UUID.randomUUID().toString());
        workflowPermissionModel.setUnitType(UnitType.DEPARTMENT);
        workflowPermissionModel.setWorkflowCode("a3");

        try {
            String path = WorkflowControllerTest.class.getResource("").toString() + "WorkflowTemplateJsonTestData";
            path = path.replace("file:/", "");
            path = path.replace("/", "//");
            File targetFile = new File(path);
            requestJson = Files.toString(targetFile, Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateWorkflow() throws Exception {
        String requestJson = JSONObject.toJSONString(workflowTemplateHeaderModel);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/workflow/create")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUpdateWorkflowTemplateHeader() throws Exception {
        workflowTemplateHeaderModel.setId("40288657677c054d01677c62e53b0005");
        workflowTemplateHeaderModel.setWorkflowName(UUID.randomUUID().toString());
        String requestJson = JSONObject.toJSONString(workflowTemplateHeaderModel);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/workflow/update_header")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDeleteWorkflow() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/workflow/delete").param("workflowCode", "a0ef7253d7e24dc2a8b152e685"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testGetWorkflowTemplateHeader() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/workflow/get_header").param("workflowCode", "a0ef7253d7e24dc2a8b152e685"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testGetWorkflowTemplateDraft() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/workflow/get_draft").param("workflowCode", "a4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testGetWorkflowTemplatePublished() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/workflow/get_published").param("workflowCode", "test_bizs").param("workflowVersion", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUpdateWorkflowTemplate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/workflow/update")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testValidateWorkflowTemplate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/workflow/validate")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testPublishWorkflowTemplate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/workflow/publish")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListWorkflowTemplateHeardersByCode() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/workflow/list").param("schemaCode", "bsc1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListWorkflowTemplates() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/workflow/list_history").param("schemaCode", "bsc1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testCreateWorkflowPermission() throws Exception {
        String requestJson = JSONObject.toJSONString(workflowPermissionModel);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/workflow/create_permission")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testListWorkflowPermissions() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/workflow/list_permission").param("workflowCode", "a3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDeleteWorkflowPermission() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/workflow/delete_permission").param("permissionId", "2c92809567a75ef80167a7b9f4b20005"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String data = mvcResult.getResponse().getContentAsString();
        log.info(data);
        Assert.assertEquals(200, status);
    }
}
