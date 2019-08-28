package com.authine.cloudpivot.ext.controller;

import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.handler.CustomizedOrigin;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目管理页面接口
 */
@Api(value = "项目管理页面接口", tags = "项目管理页面接口")
@RestController
@Validated
@Slf4j
@RequestMapping("/api/projectmanage")
@CustomizedOrigin(level = 0)
public class ProjectManageController  extends BaseController {
   }
