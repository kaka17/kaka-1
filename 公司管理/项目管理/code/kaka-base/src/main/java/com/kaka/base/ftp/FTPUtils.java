/**   
 * @Project: tfs-base 
 * @Title: FTPUtils.java 
 * @Package com.tfstec.base.ftp 
 * @Description: JDK1.7以上FTP上传下载工具类 
 * @author Administrator 
 * @date 2016年10月24日 下午7:38:13 
 * @Copyright: 2016 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 */
package com.kaka.base.ftp;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

/** 
 * @ClassName FTPUtils  
 * @Description FTP上传下载工具类 
 * @author Administrator 
 * @date 2016年10月24日  
 *   
 */
public class FTPUtils {
	// FTP客户端
	private FtpClient ftpClient;

	/** 
	 * 服务器连接  
	 * @param ip    服务器IP   
	 * @param port  服务器端口  
	 * @param user  用户名   
	 * @param password   密码  
	 * @param path  服务器路径 
	 */
	public void connectServer(String ip, int port, String user, String password, String path) {
		try {
			ftpClient = FtpClient.create();
			try {
				SocketAddress addr = new InetSocketAddress(ip, port);
				this.ftpClient.connect(addr);
				this.ftpClient.login(user, password.toCharArray());
				System.out.println("login success!");
				if (path.length() != 0) {
					// 把远程系统上的目录切换到参数path所指定的目录
					this.ftpClient.changeDirectory(path);
				}
			} catch (FtpProtocolException e) {
				e.printStackTrace();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @Title: upload 
	 * @Description: 文件上传 
	 * @param localFile 本地文件
	 * @param remoteFile 远程文件
	 * @return void    返回类型
	 */
	public void upload(String localFile, String remoteFile) {
		OutputStream os = null;
		FileInputStream is = null;

		try {
			try {
				// 将远程文件加入输出流中
				os = this.ftpClient.putFileStream(remoteFile);
			} catch (FtpProtocolException e) {
				e.printStackTrace();
			}
			// 获取本地文件输入流
			File file_in = new File(localFile);
			is = new FileInputStream(file_in);

			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("upload success");
		} catch (IOException ex) {
			System.out.println("not upload");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @Title: upload 
	 * @param localFile 本地文件
	 * @return void    返回类型
	 */
	public void upload(File localFile) {
		OutputStream os = null;
		FileInputStream is = null;
		
		try {
			os = this.ftpClient.putFileStream(localFile.getName());
			// 获取本地文件输入流
			is = new FileInputStream(localFile);
			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("upload success");
		} catch (Exception ex) {
			System.out.println("not upload");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 返回FTP目录下的文件列表
	 * 
	 * @param ftpDirectory
	 * @return
	 */
	public List<String> downloadFileNameList() {
		List<String> list = new ArrayList<String>();
		try {
			DataInputStream dis = new DataInputStream(ftpClient.nameList(""));
			String filename = "";
			while ((filename = dis.readLine()) != null) {
				list.add(filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @Title: download 
	 * @Description: 文件下载
	 * @param remoteFile 远程文件
	 * @param localFile 本地文件
	 * @return void    返回类型
	 */
	public void download(String remoteFile, String localFile) {
		InputStream is = null;
		FileOutputStream os = null;

		try {
			try {
				// 获取远程机器上的文件filename,借助TelnetInputStream把文件传送到本地
				is = this.ftpClient.getFileStream(remoteFile);
			} catch (FtpProtocolException e) {
				e.printStackTrace();
			}
			// 获取本地文件输入流
			File file_in = new File(localFile);
			os = new FileOutputStream(file_in);

			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("download success");
		} catch (IOException ex) {
			System.out.println("not download");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 删除文件
	 * @Title: deleteFile 
	 * @param remoteFile
	 */
	public void deleteFile(String remoteFile) {
		try {
			this.ftpClient.deleteFile(remoteFile);
			System.out.println("delete success");
		} catch (Exception e) {
			if (e.getMessage().contains("No such file or directory.")) {
				System.out.println("file not exist");

			} else {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * @Title: closeConnect 
	 * @Description: 关闭连接
	 * @return void    返回类型
	 */
	public void closeConnect() {
		try {
			this.ftpClient.close();
			System.out.println("disconnect success");
		} catch (IOException ex) {
			System.out.println("not disconnect");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
