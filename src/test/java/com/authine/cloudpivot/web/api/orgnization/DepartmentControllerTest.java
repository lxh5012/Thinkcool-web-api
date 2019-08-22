package com.authine.cloudpivot.web.api.orgnization;

import com.authine.cloudpivot.web.api.WebAPIApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
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
public class DepartmentControllerTest extends WebAPIApplicationTests {

    @BeforeClass
    public static void setUp() {

    }

    /**
     * @return void
     * @Title:
     * @Description: 通过 id 获取组织机构的子组织
     * @Param []
     * @author dengchao
     * @date 2018/10/29 9:59
     */
    @Test
    public void testOrgTree() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/organization/tree")
                .param("id", "1"))
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
     * @Description: 钉钉立即同步
     * @Param []
     * @author dengchao
     * @date 2018/10/29 16:23
     */
    @Test
    public void testSynchronize() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/organization/synchronize"))
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
