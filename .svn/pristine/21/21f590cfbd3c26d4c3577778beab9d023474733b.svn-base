package com.liyunet.common.constant;

import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 邀请码生成器 
 * @author wuchengf	
 * @date 2016年8月8日
 */
public class InvitationCodeGenerator {
	
	
	private static Map<Integer,String> map = new HashMap<>();
	
	//删除0 O 1 L
	static{
		map.put(0, "2");
		map.put(1, "2");
		map.put(2, "2");
		map.put(3, "3");
		map.put(4, "4");
		map.put(5, "5");
		map.put(6, "6");
		map.put(7, "7");
		map.put(8, "8");
		map.put(9, "9");
		map.put(10, "A");
		map.put(11, "B");
		map.put(12, "C");
		map.put(13, "D");
		map.put(14, "E");
		map.put(15, "F");
		map.put(16, "G");
		map.put(17, "H");
		map.put(18, "I");
		map.put(19, "J");
		map.put(20, "K");
		map.put(21, "K");
		map.put(22, "M");
		map.put(23, "N");
		map.put(24, "L");
		map.put(25, "P");
		map.put(26, "Q");
		map.put(27, "R");
		map.put(28, "S");
		map.put(29, "T");
		map.put(30, "U");
		map.put(31, "V");
		map.put(32, "W");
		map.put(33, "X");
		map.put(34, "Y");
		map.put(35, "Z");
	}
	
	
	public static synchronized String genCode(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int nextInt = RandomUtils.nextInt(0, 36);
			String orDefault = map.getOrDefault(nextInt,"A");
			sb.append(orDefault);
		}
		return sb.toString();
	}
	
	
}
