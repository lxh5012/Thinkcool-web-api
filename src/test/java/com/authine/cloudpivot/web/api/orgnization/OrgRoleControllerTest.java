package com.authine.cloudpivot.web.api.orgnization;

import com.authine.cloudpivot.web.api.WebAPIApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @ClassName: DataItemControllerTest
 * @Description: 组织机构相关的单元测试
 * @Author: dengchao
 * @Date: 2018年10月29日
 * @Version 1.0
 */
@Slf4j
public class OrgRoleControllerTest extends WebAPIApplicationTests {

    /**
     * @return void
     * @Description: 获取角色列表
     * @Param []
     * @author dengchao
     * @date 2018/10/29 16:23
     */
    @Test
    public void testGetRoleTree() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/role/tree"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("data"))
                .andReturn();
        //得到返回代码
        int status = mvcResult.getResponse().getStatus();
        //得到返回结果
        String data = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(200, status);
        log.debug(data);
    }

    /**
     * @return void
     * @Description: 根据角色 Id 获取角色下的用户列表
     * @Param []
     * @author dengchao
     * @date 2018/10/29 16:30
     */
    @Test
    public void testGetByRoleId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/role/get")
                .param("roleId", "111")
                .param("page", "1")
                .param("size", "100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("data"))
                .andReturn();
        //得到返回代码
        int status = mvcResult.getResponse().getStatus();
        //得到返回结果
        String data = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(200, status);
        log.debug(data);
    }

    /**
     * @return void
     * @Description: 根据 groupId 查询角色集合
     * @Param []
     * @author dengchao
     * @date 2018/10/29 16:31
     */
    @Test
    public void testListRoleTree() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/role/list")
                .param("groupId", "111"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("data"))
                .andReturn();
        //得到返回代码
        int status = mvcResult.getResponse().getStatus();
        //得到返回结果
        String data = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(200, status);
        log.debug(data);
    }


}
