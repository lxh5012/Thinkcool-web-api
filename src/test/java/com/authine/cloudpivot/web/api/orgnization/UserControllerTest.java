package com.authine.cloudpivot.web.api.orgnization;

import com.authine.cloudpivot.web.api.WebAPIApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @ClassName: DataItemControllerTest
 * @Description: 组织机构用户相关的单元测试
 * @Author: dengchao
 * @Date: 2018年10月29日
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest extends WebAPIApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * @return void
     * @Description: 组织机构 id，获取直接隶属于该组织的用户
     * @Param []
     * @author dengchao
     * @date 2018/10/29 16:49
     */
    @Test
    public void testGetByDeptId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/list")
                .content("{id:11}"))
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
     * @Description: 根据用户名查询用户信息
     * @Param []
     * @author dengchao
     * @date 2018/10/29 16:52
     */
    @Test
    public void testGetByNameLike() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user/search")
                .content("{keyWord:110,page:1,size:10}"))
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
     * @Description: 根据 id 获取用户信息
     * @Param []
     * @author dengchao
     * @date 2018/10/29 16:52
     */
    @Test
    public void testGetById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/get")
                .param("id", "11"))
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
