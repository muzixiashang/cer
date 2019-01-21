package com.liyunet.common.util;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	// 逐行读取txt文件
	public static List<String> readFile(File file, String charset) {
		List<String> list = new ArrayList<String>();
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), charset);
			BufferedReader reader = new BufferedReader(read);
			String line = "";
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
			read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		try {
			List<String> list=FileUtils.readLines(new File("C:\\Users\\Administrator\\Desktop\\temp\\temp\\temp.txt"), "UTF-8");
			for (String string : list) {
				System.out.println(string);
			}
			//readFile(new File("C:\\Users\\Administrator\\Desktop\\temp\\temp\\temp.txt"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
