package com.liyunet.test;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class TestConn2 {
	public static String getDiskPath(){
		Properties props = System.getProperties(); //系统属性
		String osName = props.getProperty("os.name").toLowerCase();
		String diskPath = "";
		if(osName.contains("windows")){
			diskPath = "E:/liyugame/ipexclude";
		}else if (osName.contains("linux")){
			diskPath = "/data/liyugame/ipexclude";
		}
		File file = new File(diskPath);
		if(!file.exists())
			file.mkdirs();
		return diskPath;
	}
	
	public static void opDb(String sql,String fname) {
	    Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("加载驱动程序有错误");
			}
			//String url = "jdbc:mysql://127.0.0.1:3306/liyugame?user=root&password=root&useUnicode=true&characterEncoding=utf-8";
			String url = "jdbc:mysql://123.207.171.234:3306/liyugame?user=liyugameuser&password=liyugame_!@#123_&useUnicode=true&characterEncoding=utf-8";
			conn = DriverManager.getConnection(url);
			System.out.println("成功连接数据库！！");
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			int n=0;
			while (rs.next()) {
				int id=rs.getInt(1);
				String code=rs.getString(3);
				TestConn2.saveStrToFile(code+"\n",fname);
				int a=pstmt.executeUpdate("update exchange_info set is_userd=1 where id = "+id);
				n=n+a;
			}
			System.out.println("==========受影响的行数:"+n);
			Thread.sleep(10000);
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
		} catch (Exception ex1) {
			ex1.printStackTrace();
			System.out.print("系统异常");
		}
		/**finally {
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
		}*/
	}

	//字符串写入文本
	public static void saveStrToFile(String str,String fname) {
		InputStream in = new ByteArrayInputStream(str.getBytes());
		BufferedInputStream bips = new BufferedInputStream(in);
		int len;
		byte[] b = new byte[8192];
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(getDiskPath()+"/"+fname),true);
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
			
		String sql1="select id,'还珠鲤鱼新手礼包', exchange_code from exchange_info where game_package_id =24 and is_userd=0 limit 100";
		String sql2="select id,'还珠鲤鱼进阶礼包', exchange_code from exchange_info where game_package_id =25 and is_userd=0 limit 100";
		String sql3="select id,'还珠鲤鱼至尊礼包', exchange_code from exchange_info where game_package_id =26 and is_userd=0 limit 100";
		TestConn2.opDb(sql1,"excode_1");
		//TestConn2.opDb(sql2,"excode_2");
		//TestConn2.opDb(sql3,"excode_3");
	}

}
