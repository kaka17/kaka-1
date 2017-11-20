/**   
 * @Project: tfscore 
 * @Title: FileUtil.java 
 * @Package com.tfscore.file 
 * @Description: 文件处理帮助工具 
 * @author lx 
 * @date 2016年6月5日 下午1:31:33 
 * @Copyright: 2016 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 */
package com.kaka.base.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName FileUtil  
 * @Description 文件处理帮助工具 
 * @author lx 
 * @date 2016年6月5日  
 *   
 */
public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	/**
	 * 文件分隔符
	 */
	public final static String SEPARATOR = File.separator;

	// 验证字符串是否为正确路径名的正则表达式
	// private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * @param sPath  要删除的目录或文件
	 *@return 删除成功返回 true，否则返回 false。
	 */
	public static boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * @param sPath     被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * @param sPath    被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 遍历文件夹下所有以身份证号开头的文件
	 * @param dirStr    文件目录路径
	 * @param IDCard	身份证号
	 * @return 
	 */
	public static void findFileByEndStr(String dirStr, String IDCard) {
		File dir = new File(dirStr);
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().startsWith(IDCard)) {
				logger.debug("文件名:" + file.getName());
				logger.debug("文件大小:" + file.length());
			}
		}
	}

	/**
	 * 遍历文件夹下所有指定后缀文件
	 * @param dirStr    文件目录路径
	 * @param suffix 后缀
	 * @return 
	 */
	public static List<String> findFileListBySuffix(String dirStr, String suffix) {
		List<String> list = new ArrayList<String>();

		File file = new File(dirStr);
		if (file.exists()) {
			LinkedList<File> folderList = new LinkedList<File>();
			File[] files = file.listFiles();
			for (File folderFile : files) {
				if (folderFile.isDirectory()) {
					folderList.add(folderFile);
				} else {
					if (folderFile.getName().endsWith(suffix)) {
						list.add(folderFile.getPath());
					}
				}
			}
			File temp_file;
			while (!folderList.isEmpty()) {
				temp_file = folderList.removeFirst();
				files = temp_file.listFiles();
				for (File folderFile : files) {
					if (folderFile.isDirectory()) {
						folderList.add(folderFile);
					} else {
						if (folderFile.getName().endsWith(suffix)) {
							list.add(folderFile.getPath());
						}
					}
				}
			}
		}

		return list;
	}

	/**
	 * 获取指定路径的File
	 * @Title: getFileByPath 
	 * @param filePath
	 * @return
	 */
	public static File getFileByPath(String filePath) {
		try {
			File file = new File(filePath);
			if (!(file.isFile() && file.exists())) {
				throw new RuntimeException("找不到指定文件");
			}
			return file;
		} catch (Exception e) {
			logger.error("获取指定路径的File异常", e);
			throw new RuntimeException("获取文件异常");
		}

	}

	/**
	 * 根据文件路径获取文件名
	 * @Title: getFileNameByPath 
	 * @param filePath
	 * @return
	 */
	public static String getFileNameByPath(String filePath) {
		return new File(filePath).getName();
	}

	/**
	 * 目录创建
	 * @param path
	 */
	public static void makeDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * inputstream转File
	 * @Title: inputstreamToFile 
	 * @param ins
	 * @param filePath
	 * @return
	 */
	public static File inputstreamToFile(InputStream ins, String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			throw new RuntimeException("inputstreamToFile异常", e);
		}
		return file;
	}
}
