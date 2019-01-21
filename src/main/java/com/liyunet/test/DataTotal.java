package com.liyunet.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liyunet.common.constant.Constant;
import com.liyunet.common.constant.DateHelper;
import com.liyunet.common.password.AES;

public class DataTotal {

	public static void main(String[] args) {
		
		//等级数据----------------------------------------------------------------------------time_created
		
		
//		try {
//			// 获取蛋生等级
//			Map<Integer, Integer> eggworldlevellist = getEggworldlevellist();
//			// 获取cer与蛋生关联
//			Map<Integer, Integer> cerlevellist = getCerlevellist();
//			System.err.println(cerlevellist);
//			// 获取cer用户
//			List<Integer> cerPeplelist = getCerPeplelist();
//			for (int i = 0; i < cerPeplelist.size(); i++) {
//				// 获取等级
//				Integer integer = eggworldlevellist.get(cerlevellist.get(cerPeplelist.get(i)));
//				if (integer == null) {
//					integer = 0;
//				}
//				System.out.println("cer用户：" + cerPeplelist.get(i) + " , 蛋生等级：" + integer);
//				// 修改
//				Integer updatelevel = updatelevel(cerPeplelist.get(i), integer);
//				if(updatelevel==1){
//					System.out.println("修改成功");
//				}
//				
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//等级数据---------------------------------------------------------------------------------

		//创建时间---------------------------------------------------------------------------------
		try {
		// 获取蛋生创建时间
		Map<Integer, String> eggworldcreatetimelist = getEggworldcreatetimelist();
		// 获取cer与蛋生关联
		Map<Integer, Integer> cerlevellist = getCerlevellist();
		System.out.println(eggworldcreatetimelist);
		// 获取cer用户
		List<Integer> cerPeplelist = getCerPeplelist();
		
		
		for (int i = 0; i < cerPeplelist.size(); i++) {
			// 获取创建时间
			String integer = eggworldcreatetimelist.get(cerlevellist.get(cerPeplelist.get(i)));
			if (integer == null||integer.equals("")) {
				integer = "0";
			}
			System.out.println("cer用户：" + cerPeplelist.get(i) + " , 蛋生创建时间：" + integer);
			// 修改
			Integer updatelevel = updatetime(cerPeplelist.get(i), integer);
//			if(updatelevel==1){
//				System.out.println("修改成功");
//			}
			
		}

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		//创建时间---------------------------------------------------------------------------------
		
		
		
		
		
	}

	// 获取蛋生等级
	public static Map<Integer, Integer> getEggworldlevellist() throws Exception {
		final String url = "jdbc:mysql://123.206.24.154:4769/egg_gamedb";
		final String name = "com.mysql.jdbc.Driver";
		final String user = "root";
		final String password = "mysql_egg";
		Long begin = new Date().getTime();
		Connection conn = null;
		PreparedStatement statement = null;
		Class.forName(name);// 指定连接类型
		// try {
		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		statement = (PreparedStatement) conn.prepareStatement("");
		int key = 0;
		String sql = "select account_index, char_level from `character`";
		String char_level = null;
		String account_index = null;
		ResultSet rsuser = statement.executeQuery(sql);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (rsuser.next()) {
			// 可以根据列名称也可以根据列索引
			// int id = rsuser.getInt(1);
			account_index = rsuser.getString("account_index");
			char_level = rsuser.getString("char_level");
			String aesEncode = AES.AESDncode(Constant.userIdSecret, account_index);
			int parseInt = Integer.parseInt(aesEncode);
			map.put(parseInt, Integer.parseInt(char_level));

		}

		conn.commit();
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}
	
	
	// 获取蛋创建时间
		public static Map<Integer, String> getEggworldcreatetimelist() throws Exception {
			final String url = "jdbc:mysql://123.206.24.154:4769/egg_gamedb";
			final String name = "com.mysql.jdbc.Driver";
			final String user = "root";
			final String password = "mysql_egg";
			Long begin = new Date().getTime();
			Connection conn = null;
			PreparedStatement statement = null;
			Class.forName(name);// 指定连接类型
			// try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			statement = (PreparedStatement) conn.prepareStatement("");
			int key = 0;
			String sql = "select account_index, time_created from `character`";
			String time_created = null;
			String account_index = null;
			ResultSet rsuser = statement.executeQuery(sql);
			Map<Integer, String> map = new HashMap<Integer, String>();
			while (rsuser.next()) {
				// 可以根据列名称也可以根据列索引
				// int id = rsuser.getInt(1);
				account_index = rsuser.getString("account_index");
				time_created = rsuser.getString("time_created");
				String aesEncode = AES.AESDncode(Constant.userIdSecret, account_index);
				int parseInt = Integer.parseInt(aesEncode);
				map.put(parseInt,time_created);

			}

			conn.commit();
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return map;
		}

	// 获取cer等级
	public static Map<Integer, Integer> getCerlevellist() throws Exception {
		final String url = "jdbc:mysql://123.206.80.238:3306/eggworld";
		final String name = "com.mysql.jdbc.Driver";
		final String user = "root";
		final String password = "liyugame_!@#123_";
		Long begin = new Date().getTime();
		Connection conn = null;
		PreparedStatement statement = null;
		Class.forName(name);// 指定连接类型
		// try {
		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		statement = (PreparedStatement) conn.prepareStatement("");
		int key = 0;
		String sql = "select open_id,block_id from user_balance where block_id is not null and open_id is not null";
		String block_id = null;
		String open_id = null;
		ResultSet rsuser = statement.executeQuery(sql);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (rsuser.next()) {
			// 可以根据列名称也可以根据列索引
			// int id = rsuser.getInt(1);
			block_id = rsuser.getString("block_id");
			open_id = rsuser.getString("open_id");
			map.put(Integer.parseInt(block_id), Integer.parseInt(open_id));

		}

		conn.commit();
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}

	// 查询年度人数
	public static List<Integer> getCerPeplelist() throws Exception {
		final String url = "jdbc:mysql://123.207.158.196:3306/timetreaty_certification";
		final String name = "com.mysql.jdbc.Driver";
		final String user = "gonggonguser";
		final String password = "gonggong_!@#123_";
		Long begin = new Date().getTime();
		Connection conn = null;
		PreparedStatement statement = null;
		Class.forName(name);// 指定连接类型
		// try {
		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		statement = (PreparedStatement) conn.prepareStatement("");
		int key = 0;
		String sql = "select userId from data_total";
		String userId = null;
		ResultSet rsuser = statement.executeQuery(sql);
		List<Integer> list = new ArrayList<Integer>();
		while (rsuser.next()) {
			// 可以根据列名称也可以根据列索引
			// int id = rsuser.getInt(1);
			userId = rsuser.getString("userId");
			list.add(Integer.parseInt(userId));
		}

		conn.commit();
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 修改等级
	public static Integer updatelevel(Integer userId,Integer level) throws Exception {
		final String url = "jdbc:mysql://123.207.158.196:3306/timetreaty_certification";
		final String name = "com.mysql.jdbc.Driver";
		final String user = "gonggonguser";
		final String password = "gonggong_!@#123_";
		Long begin = new Date().getTime();
		Connection conn = null;
		PreparedStatement statement = null;
		Integer executeUpdate = null;
		try {
			Class.forName(name);// 指定连接类型

			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			statement = (PreparedStatement) conn.prepareStatement("");

			String sql = "update data_total set egg_level="+level+" where userId="+userId;
			System.out.println(sql);
			String ttc_blance = null;
			executeUpdate = statement.executeUpdate(sql);

			conn.commit();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return executeUpdate;
	}
	
	
	
	// 修改时间
		public static Integer updatetime(Integer userId,String createtime) throws Exception {
			final String url = "jdbc:mysql://123.207.158.196:3306/timetreaty_certification";
			final String name = "com.mysql.jdbc.Driver";
			final String user = "gonggonguser";
			final String password = "gonggong_!@#123_";
			Long begin = new Date().getTime();
			Connection conn = null;
			PreparedStatement statement = null;
			Integer executeUpdate = null;
			try {
				Class.forName(name);// 指定连接类型

				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);
				statement = (PreparedStatement) conn.prepareStatement("");

				String sql = "update data_total set egg_login_time='"+createtime+"' where userId="+userId;
				System.out.println(sql);
				String ttc_blance = null;
				executeUpdate = statement.executeUpdate(sql);

				conn.commit();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return executeUpdate;
		}
}
