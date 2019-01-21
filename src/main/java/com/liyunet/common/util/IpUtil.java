package com.liyunet.common.util;

import com.liyunet.common.http.HttpUtil;
import com.liyunet.test.TestConn;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class IpUtil {
	public static String getWebIP(String strUrl) {
		try {
			// 连接网页
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			String webContent = "";
			// 读取网页信息
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			// 网页信息
			webContent = sb.toString();
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]");
			// 获取网页中 当前 的 外网IP
			webContent = webContent.substring(start, end);
			return webContent;

		} catch (Exception e) {
			e.printStackTrace();
			return "error open url:" + strUrl;
		}
	}

	public static String getLocalIP() throws Exception {
		String localIP = "";
		InetAddress addr = (InetAddress) InetAddress.getLocalHost();
		// 获取本机IP
		localIP = addr.getHostAddress().toString();
		return localIP;
	}


	public static void main(String[] args) {
		String str=HttpUtil.ajax("http://www.baidu.com/s?wd=IP", null, "utf-8");
		String ip= StringUtils.substringBetween(str, "<span class=\"c-gap-right\">本机IP:&nbsp;", "</span>");
		TestConn getConn = new TestConn();
		getConn.saveStrToFile(getConn.getFormatedDateStr(new java.util.Date(), "yyyy-MM-dd HH:mm:ss")+"\t\t"+ip+"\n");
		getConn.getConnection(ip);
	}
}
