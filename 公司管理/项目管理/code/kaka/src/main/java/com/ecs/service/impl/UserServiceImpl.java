/*package com.ecs.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecs.base.util.StringUtils;
import com.ecs.base.util.date.TimeUtil;
import com.ecs.dao.QuotaInfoDao;
import com.ecs.dao.UserDao;
import com.ecs.dao.UserOnlineInfoDao;
import com.ecs.global.GlobalVar;
import com.ecs.pojo.QuotaInfo;
import com.ecs.pojo.ResponseModule;
import com.ecs.pojo.UserInfo;
import com.ecs.pojo.UserOnlineInfo;
import com.ecs.service.UserService;
import com.ecs.util.KakaStringUtils;

@Service
public class UserServiceImpl implements UserService{
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserDao userDao;
	@Resource
	private QuotaInfoDao quotaInfoDao;
	@Resource
	private UserOnlineInfoDao userOnlineInfoDao;
	
	@Override
	public ResponseModule login(String accountId,String userPass) throws IOException {
		ResponseModule results = new ResponseModule();
		
		UserInfo  userInfo = userDao.queryByUserId(accountId);
		if(StringUtils.isEmpty(userInfo)){
			logger.error("当前用户不存在：{}",accountId);
			results.setCode(GlobalVar.RC_OUT_LOGIN);
			results.setMsg(GlobalVar.RM_USER_NOT_EXIT);
			return results;
		}
		
		if(GlobalVar.RV_USER_STATUS_1.equals(userInfo.getUserStatus())){
			logger.error("当前用户已被锁定，登录失败{}",accountId);
			results.setCode(GlobalVar.RC_OUT_LOGIN);
			results.setMsg(GlobalVar.RM_USER_LOCK);
			return results;
		}
		
		if(userPass.equals(userInfo.getUserPass())){
			UserOnlineInfo userOnlineInfo = new UserOnlineInfo();
			
			String onlineId = StringUtils.getRandomUnique();
			userOnlineInfo.setOnlineId(onlineId);
			userOnlineInfo.setAccountId(accountId);
			saveOrUpdateOnlineInfo(userOnlineInfo);
			
			userInfo.setFailCount(0);
			userDao.updateUserInfo(userInfo);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("onlineId", onlineId);
			results.setData(map);
			results.setCode(GlobalVar.RC_SUCCESS);
			results.setMsg(GlobalVar.RM_LOGIN_SUCCESS);
			return results;
		}
		
		logger.error("用户登录密码错误：{}",accountId);
		int failCount = userInfo.getFailCount()+1;
		if(failCount>GlobalVar.RV_USER_LOGIN_FAIL_ACCOUNT){
			userInfo.setUserStatus(GlobalVar.RV_USER_STATUS_1);
			userInfo.setFailCount(failCount);
		}
		userDao.updateUserInfo(userInfo);
		
		
		results.setCode(GlobalVar.RC_OUT_LOGIN);
		results.setMsg(GlobalVar.RM_USER_LOGIN_PASS_ERR);
		return results;
	}
	
	@Override
	public ResponseModule userRegist(UserInfo userInfo){
		ResponseModule results = new ResponseModule();
		
		UserInfo userMax = userDao.queryMaxAccountId();
		
		String maxAccountId = (userMax == null?"":userMax.getAccountId());
		String accountId = KakaStringUtils.getAccountId(maxAccountId);
		
		userInfo.setAccountId(accountId);
		int i = userDao.saveUserInfo(userInfo);
		QuotaInfo quotaInfo = new QuotaInfo();
		
		quotaInfo.setAccountId(accountId);
		quotaInfoDao.insertQuotaInfo(quotaInfo);
		
		if(i>0){
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("accountId", accountId);
			data.put("accountName", userInfo.getAccountName());
			data.put("userPass", userInfo.getUserPass());
			results.setData(data);
			results.setCode(GlobalVar.RC_SUCCESS);
			results.setMsg(GlobalVar.RM_USER_REGIST_SUCCSS);
		}else{
			logger.error("注册保存数据库失败");
			results.setCode(GlobalVar.RC_FAIL);
			results.setMsg(GlobalVar.RM_USER_REGIST_ERROR);
		}
		
		return results;
	}
	//保存或更新用户在线信息
	private void saveOrUpdateOnlineInfo(UserOnlineInfo userOnlineInfo){
		
		userOnlineInfo.setOnlineId(userOnlineInfo.getOnlineId());
		userOnlineInfo.setAccountId(userOnlineInfo.getAccountId());
		userOnlineInfo.setModifyTime(TimeUtil.getDate());
		UserOnlineInfo oldUserOnlineInfo = userOnlineInfoDao.queryOnlineInfoByAccountId(userOnlineInfo.getAccountId());
		if(StringUtils.isNotEmpty(oldUserOnlineInfo)){
			userOnlineInfoDao.updateUserOnlineInfo(userOnlineInfo);
		}else{
			userOnlineInfoDao.saveUserOnlineInfo(userOnlineInfo);
		}
	}
	
	@Override
	public ResponseModule personInfo(String accountId){
		ResponseModule results = new ResponseModule();
		
		UserInfo  userInfo = userDao.queryByUserId(accountId);
		QuotaInfo quotaInfo = quotaInfoDao.queryQuotaInfoByAccountId(accountId);
		if(StringUtils.isEmpty(userInfo)||StringUtils.isEmpty(quotaInfo)){
			logger.error("accountId:{}个人信息为空",accountId);
			results.setCode(GlobalVar.RC_FAIL);
			results.setMsg(GlobalVar.RM_FAIL);
			return results;
		}
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("accountId", accountId);
		data.put("accountName", userInfo.getAccountName());
		data.put("mobile", userInfo.getMobile()==null?"":userInfo.getMobile());
		data.put("accountName", userInfo.getAccountName()==null?"":userInfo.getAccountName());
		data.put("gameGold", quotaInfo.getGameGold()==null?0:quotaInfo.getGameGold());
		data.put("level", userInfo.getLevel()==null?"A":userInfo.getLevel());
		
		results.setCode(GlobalVar.RC_SUCCESS);
		results.setMsg(GlobalVar.RM_SUCCESS);
		results.setData(data);
		return results;
	}
	
	@Override
	public ResponseModule updatePersonInfo(UserInfo  userInfo){
		ResponseModule results = new ResponseModule();
		
		int rstInt =userDao.updateUserInfo(userInfo);
		if(rstInt>0){
			results.setCode(GlobalVar.RC_SUCCESS);
			results.setMsg(GlobalVar.RM_SUCCESS);
		}else{
			results.setCode(GlobalVar.RC_FAIL);
			results.setMsg(GlobalVar.RM_FAIL);
		}
		
		return results;
	}
	
}
*/