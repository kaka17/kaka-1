/**   
 * @Project: tfs-plm-web 
 * @Title: OnlineInterceptor.java 
 * @Package com.tfstec.plm.interceptor 
 * @Description: 拦截器 
 * @author lx 
 * @date 2016年6月2日 下午5:37:10 
 * @Copyright: 2016 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 */
package com.ecs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/** 
 * @ClassName OnlineInterceptor  
 * @Description 拦截器
 *   
 */
public class OnlineInterceptor implements HandlerInterceptor {
	
	
	private static Logger logger = LoggerFactory.getLogger(OnlineInterceptor.class);
	/*
	 * 处理完成后被调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse responose, Object obj,
			Exception e) throws Exception {
		
	}

	/*
	 * 处理前被调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		return true;
		/*//拦截客户端请求数据
		String requestURI = request.getRequestURI();
		String requestStr = HttpHelper.getParamsByStream(request);
		request.setAttribute(GlobalVar.RK_REQ_DATA, requestStr);
		logger.debug("客户端请求{}接口   数据：{}",requestURI,GsonUtils.toJson(requestStr));
		
		//解析 设置拦截规则
		ResponseModule responseModule = new ResponseModule();
		RequestModule requestModule = HttpHelper.getRequestModule(requestStr);
		if(null == requestModule){
			logger.debug("客户端请求{}接口失败：参数为空",requestURI);
			return false;
		}
		
		if(StringUtils.isNotEmpty(requestModule.getAccountId())&&StringUtils.isNotEmpty(requestModule.getOnlineId())){
			UserOnlineInfo userOnlineInfo =null;
			if(StringUtils.isNotEmpty(userOnlineInfo)){
				long surplusTime = TimeUtil.surplusTime(TimeUtil.getDate(TimeUtil.Y_M_D_H_M_D),TimeUtil.getDateByFormat(userOnlineInfo.getModifyTime(), TimeUtil.Y_M_D_H_M_D));
				if(surplusTime<GlobalVar.RV_LOGIN_TIME_OUT && requestModule.getOnlineId().equals(userOnlineInfo.getOnlineId())){
					*//**用户已在登录状态，更新在线信息表*//*
					UserOnlineInfo newUserOnline = new UserOnlineInfo();
					newUserOnline.setAccountId(requestModule.getAccountId());
					newUserOnline.setModifyTime(TimeUtil.getDate());
					//UserOnlineInfoDao.updateUserOnlineInfo(newUserOnline);
					return true;
				}
			}
			responseModule.setCode(GlobalVar.RC_OUT_LOGIN);
			responseModule.setMsg(GlobalVar.RM_OUT_ONLINE);
			
		}
		String[] mapping = new String[] {"login","regist"};
		for (int i = 0; i < mapping.length; i++) {
			if (requestURI.contains(mapping[i])) {
				return true;
			}
		}
		responseModule.setCode(GlobalVar.RC_OUT_LOGIN);
		responseModule.setMsg(GlobalVar.RM_OUT_ONLINE);
		response.getWriter().print(HttpHelper.responseDatas(responseModule));
		return false;*/
	}

	/*
	 * 处理完成后，生成视图前被调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView model) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
	}
}
