package com.liyunet.test;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class TestConn {
	public static String getDiskPath(){
		Properties props = System.getProperties(); //系统属性
		String osName = props.getProperty("os.name").toLowerCase();
		String diskPath = "";
		if(osName.contains("windows")){
			diskPath = "F:/liyugame/ipexclude";
		}else if (osName.contains("linux")){
			diskPath = "/data/liyugame/ipexclude";
		}
		File file = new File(diskPath);
		if(!file.exists())
			file.mkdirs();
		return diskPath;
	}
	
	public static Connection getConnection(String ip) {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("加载驱动程序有错误");
			}

			String url = "jdbc:mysql://localhost:3306/liyugame?user=root&password=root&useUnicode=true&characterEncoding=utf-8";
			conn = DriverManager.getConnection(url);
			System.out.println("成功连接数据库！！");
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery("select 1 from back_acess_exclude_ip_address where ip_addr='"+ip+"'");
			boolean flag=false;
			while (rs.next()) {
				flag=true;
				break;
			}
			if(!flag){//新增公司ip
				String insertSql="insert into back_acess_exclude_ip_address values(0,'"+ip+"')";
				int a=pstmt.executeUpdate(insertSql);
				System.out.println("==========受影响的行数:"+a);
			}
			if (rs != null) { // 关闭记录集
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) { // 关闭声明
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) { // 关闭连接对象
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException ex1) {
			ex1.printStackTrace();
			System.out.print("取得连接的时候有错误，请核对用户名和密码");
		} finally {
			if (rs != null) { // 关闭记录集
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) { // 关闭声明
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) { // 关闭连接对象
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}

	//字符串写入文本
	public static void saveStrToFile(String str) {
		InputStream in = new ByteArrayInputStream(str.getBytes());
		BufferedInputStream bips = new BufferedInputStream(in);
		int len;
		byte[] b = new byte[8192];
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(getDiskPath()+"/excludeip.txt"),true);
			len = bips.read(b);
			if (len > 0) {
				out.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.close();
			bips.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFormatedDateStr(Date now, String format)
	{
		DateFormat df = new SimpleDateFormat(format);
		String str = (now == null ? null : df.format(now));
		return str;
	}
	
	public static void main(String[] args) {
//		String str=HttpUtil.ajax("http://www.baidu.com/s?wd=IP", null, "utf-8");
//		String ip=StringUtils.substringBetween(str, "<span class=\"c-gap-right\">本机IP:&nbsp;", "</span>");
//		if(StringUtils.isNotBlank(ip)){
//			saveStrToFile(getFormatedDateStr(new java.util.Date(), "yyyy-MM-dd HH:mm:ss")+"\t\t"+ip+"\n");
//			getConnection(ip);
//		}
		
	}

}
