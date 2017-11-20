package com.ecs.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.global.GlobalVar;
import com.ecs.pojo.RequestModule;
import com.ecs.pojo.ResponseModule;
import com.ecs.pojo.UserInfo;
import com.ecs.service.UserService;
import com.ecs.util.HttpHelper;
import com.kaka.base.gson.GsonUtils;

/**
 * 用户相关接口控制类
 * @author A8509
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	/**
	 * 用户注册接口控制类
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String userRegist(HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		RequestModule req = HttpHelper.getRequestModule(String.valueOf(request.getAttribute(GlobalVar.RK_REQ_DATA)));
		UserInfo user = GsonUtils.getJson(GsonUtils.toJson(req.getData()), UserInfo.class);
		
		ResponseModule resp = null;
		return HttpHelper.responseDatas(resp);
	}

	
}
