/**   
 * @Project: tfs-base 
 * @Title: FastdfsTP.java 
 * @Package com.tfscore.fdfs 
 * @Description: 使用http方式进行图片分布式存储 
 * @author lx 
 * @date 2016年6月6日 上午8:51:39 
 * @Copyright: 2016 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 */
package com.kaka.base.fdfs;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaka.base.BaseGlobal;
import com.kaka.base.date.TimeUtil;
import com.kaka.base.gson.GsonUtils;
import com.kaka.base.http.HttpUtil;
import com.kaka.base.pojo.FastdfsToken;
import com.kaka.base.pojo.FastdfsUpload;

/** 
 * @ClassName FastdfsTP  
 * @Description 使用http方式进行图片分布式存储 
 * @author lx 
 * @date 2016年6月6日  
 *   
 */
public class FastdfsTP {
	private static Logger logger = LoggerFactory.getLogger(FastdfsTP.class);
	private static String encoding = "utf-8";
	private static String token = null;

	/** 
	 * 获取token
	 * @param  fastDfsHost 远程IP地址
	 * @param  projectName 远程部署连接fastdfs的tracker服务名称
	 * @param  accessKeyID 连接账户名称
	 * @param  accessKeySecret 连接账户密码
	 */
	public static void getToken(String fastDfsHost, String projectName, String accessKeyID,
			String accessKeySecret) {
		HttpUtil util = HttpUtil.getInstance(false);
		String url = "http://" + fastDfsHost + "/" + projectName + "/user/getToken";
		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("accessKeyID", accessKeyID);
		formParams.put("accessKeySecret", accessKeySecret);
		formParams.put("uploadTime", TimeUtil.getNowDateByFormat(TimeUtil.Y_M_D_H_M_D));
		HttpResponse response = util.doPost(url, null, formParams);
		try {
			if (response != null) {
				String res = EntityUtils.toString(response.getEntity());
				FastdfsToken info = GsonUtils.getJson(res, FastdfsToken.class);
				if ("0".equals(info.getCode())) {
					token = info.getToken();
				}
			}
		} catch (Exception e) {
			logger.error("文件上传下载(分布式系统)获取Token值出现异常：", e);
		}
	}

	/** 
	 * 文件上传 
	 * @param  localFilePath 本地临时存放文件目录
	 * @param  fastDfsHost 远程IP地址
	 * @param  projectName 远程部署连接fastdfs的tracker服务名称
	 * @param  accessKeyID 连接账户名称
	 * @param  accessKeySecret 连接账户密码
	 */
	@SuppressWarnings("deprecation")
	public static FastdfsUpload uploadFile(String localFilePath,String fastDfsHost, String projectName, String accessKeyID,
			String accessKeySecret) {
		if (token == null) {
			getToken(fastDfsHost,projectName,accessKeyID,accessKeySecret);
		}
		HttpUtil util = HttpUtil.getInstance(false);
		String url = "http://" + fastDfsHost + "/" + projectName +"/file/uploadFile";
		try {
			List<FormBodyPart> formParts = new ArrayList<FormBodyPart>();
			formParts.add(new FormBodyPart("token",
					new StringBody(token, Charset.forName(encoding))));
			formParts.add(new FormBodyPart("fileData", new FileBody(new File(localFilePath))));
			formParts.add(new FormBodyPart("uploadTime", new StringBody(TimeUtil
					.getNowDateByFormat(TimeUtil.Y_M_D_H_M_D), Charset.forName(encoding))));
			HttpResponse response = util.multipartPost(url, null, formParts);
			if (response != null) {
				String res = EntityUtils.toString(response.getEntity());
				System.out.println(res);
				FastdfsUpload info = GsonUtils.getJson(res, FastdfsUpload.class);
				if ("501".equals(info.getResult().getCode())
						|| "502".equals(info.getResult().getCode())) {
					getToken(fastDfsHost,projectName,accessKeyID,accessKeySecret);
					// 移除token并重新添加
					formParts.remove(0);
					formParts.add(new FormBodyPart("token", new StringBody(token, Charset
							.forName(encoding))));
					response = util.multipartPost(url, null, formParts);
					res = EntityUtils.toString(response.getEntity());
					info = GsonUtils.getJson(res, FastdfsUpload.class);
				}
				return info;
			}
		} catch (Exception e) {
			logger.error("文件上传上传到分布式系统出现异常：", e);
		}
		return null;
	}
	
	/** 
	 * 文件上传 
	 * @param  localFilePath 本地临时存放文件目录
	 * @param  fastDfsHost 远程IP地址
	 * @param  projectName 远程部署连接fastdfs的tracker服务名称
	 * @param  accessKeyID 连接账户名称
	 * @param  accessKeySecret 连接账户密码
	 */
	@SuppressWarnings("deprecation")
	public static FastdfsUpload uploadFile(File localFilePath,String fastDfsHost, String projectName, String accessKeyID,
			String accessKeySecret) {
		if (token == null) {
			getToken(fastDfsHost,projectName,accessKeyID,accessKeySecret);
		}
		HttpUtil util = HttpUtil.getInstance(false);
		String url = "http://" + fastDfsHost + "/" + projectName +"/file/uploadFile";
		try {
			List<FormBodyPart> formParts = new ArrayList<FormBodyPart>();
			formParts.add(new FormBodyPart("token",
					new StringBody(token, Charset.forName(encoding))));
			formParts.add(new FormBodyPart("fileData", new FileBody(localFilePath)));
			formParts.add(new FormBodyPart("uploadTime", new StringBody(TimeUtil
					.getNowDateByFormat(TimeUtil.Y_M_D_H_M_D), Charset.forName(encoding))));
			HttpResponse response = util.multipartPost(url, null, formParts);
			if (response != null) {
				String res = EntityUtils.toString(response.getEntity());
				FastdfsUpload info = GsonUtils.getJson(res, FastdfsUpload.class);
				if ("501".equals(info.getResult().getCode())
						|| "502".equals(info.getResult().getCode())) {
					getToken(fastDfsHost,projectName,accessKeyID,accessKeySecret);
					// 移除token并重新添加
					formParts.remove(0);
					formParts.add(new FormBodyPart("token", new StringBody(token, Charset
							.forName(encoding))));
					response = util.multipartPost(url, null, formParts);
					res = EntityUtils.toString(response.getEntity());
					info = GsonUtils.getJson(res, FastdfsUpload.class);
				}
				return info;
			}
		} catch (Exception e) {
			logger.error("文件上传上传到分布式系统出现异常：", e);
		}
		return null;
	}

	/** 
	 * 文件下载 
	 * @param  filePath 文件远程存储的路径
	 * @param  localFilePath 本地临时存放文件目录
	 * @param  fastDfsHost 远程IP地址
	 * @param  projectName 远程部署连接fastdfs的tracker服务名称
	 * @param  accessKeyID 连接账户名称
	 * @param  accessKeySecret 连接账户密码
	 * @return String 
	 */
	public static String downFile(String filePath, String localFilePath, String Origin,String fastDfsHost, String projectName, String accessKeyID,
			String accessKeySecret) {
		logger.info("文件下载downFile  start");
		long startTime = System.currentTimeMillis();
		// 失败标识
		String str = BaseGlobal.RC_FAIL;
		// 本地文件存在就不再下载
		File file = new File(localFilePath);
		if (file.exists()) {
			str = BaseGlobal.RC_SUCCESS;
		} else {
			if (token == null) {
				getToken(fastDfsHost,projectName,accessKeyID,accessKeySecret);
			}
			HttpUtil util = HttpUtil.getInstance(false);
			String url = "http://" + fastDfsHost + "/" + projectName +"/file/downloadFile?token=" + token
					+ "&filePath=" + filePath + "&fileOrigin=" + Origin;
			
			HttpResponse response = util.doGet(url, null);
			try {
				if (response != null) {
					int code = response.getStatusLine().getStatusCode();
					if (code == 200) {
						HttpUtil.writeToFile(response.getEntity().getContent(), localFilePath);
						str = BaseGlobal.RC_SUCCESS;
					} else if (code == 403) {
						getToken(fastDfsHost,projectName,accessKeyID,accessKeySecret);
						response = util.doGet(url, null);
						code = response.getStatusLine().getStatusCode();
						if (code == 200) {
							HttpUtil.writeToFile(response.getEntity().getContent(), localFilePath);
							str = BaseGlobal.RC_SUCCESS;
						}
					}
				}
			} catch (Exception e) {
				logger.error("从分布式系统下载文件出现异常：", e);
			}
		}
		long endTime = System.currentTimeMillis();
		logger.info("文件下载downFile end costTime={},result={}",(endTime - startTime),str);
		return str;
	}
}
