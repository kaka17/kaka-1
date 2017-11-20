package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**    
* 读取Ftp Properties文件
*/
public final class FtpProperties {
	public static Logger log = LoggerFactory.getLogger(FtpProperties.class);
	/**
	 * 本地文件ZIP存放目录
	 */
	public String localPathZip;
	/**
	 * 本地临时文件存放目录
	 */
	public String localPathTemp;
	/**
	 * ftp地址
	 */
	public String remoteHost;
	/**
	 * ftp端口
	 */
	public int remotePort;
	/**
	 * ftp账户
	 */
	public String remoteUsername;
	/**
	 * ftp密码
	 */
	public String remotePassword;
	/**
	 * ftp上传目录
	 */
	public String remotePathUpload;
	/**
	 * ftp下载目录
	 */
	public String remotePathDownload;
	/**
	 * ftp下载目录-文件前缀
	 */
	public String remotePathDownloadPre;

	public void init() {
		Properties prop = null;
		InputStream is = null;
		try {
			prop = new Properties();
			String path = FtpProperties.class.getClassLoader().getResource("/ftp.properties").getPath();
//			String path="H://workspace//tfs-credit//tfs-credit-biz//src//main//resources//ftp.properties";
			is = new FileInputStream(path);
			prop.load(is);

			localPathZip = prop.getProperty("local.path.zip");
			localPathTemp = prop.getProperty("local.path.temp");
			remoteHost = prop.getProperty("remote.host");
			remotePort = Integer.parseInt(prop.getProperty("remote.port"));
			remoteUsername = prop.getProperty("remote.userName");
			remotePassword = prop.getProperty("remote.password");
			remotePathUpload = prop.getProperty("remote.path.upload");
			remotePathDownload = prop.getProperty("remote.path.download");
			remotePathDownloadPre = prop.getProperty("remote.path.download.pre");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("读取ftp配置文件异常", e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public FtpProperties() {
		init();
	}

}
