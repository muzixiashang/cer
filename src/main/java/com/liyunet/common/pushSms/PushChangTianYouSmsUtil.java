package com.liyunet.common.pushSms;

import com.liyunet.common.constant.Constant;
import com.liyunet.common.http.PostAndGet;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成6位短信验证码
 */
public class PushChangTianYouSmsUtil {
	private static Logger opLogger = LogManager.getLogger("exception");

	public static String sendSmsCode(String mobile) {
		String un = Constant.un;
		String pwd = Constant.pwd;
		String msg = String.format("【Time Treaty Team】您有新的订单生成，请于30分钟内前往处理。本条短信免费。");
		String param = "";
		String sendParam="";
		try {
			param = "un=" + un + "&pwd=" + pwd + "&mobile=" + mobile + "&msg=" + URLEncoder.encode(msg);
			sendParam=param;
			String resxml = PostAndGet.sendGet("http://sms.800617.com:4400/sms/SendSMS.aspx", param);
			opLogger.info(resxml);
			if (StringUtils.isBlank(resxml)) {
				opLogger.error("sms response is null:" + sendParam);
				return "error";
			}
			Document doc = null;
			doc = DocumentHelper.parseText(resxml);
			Element root = doc.getRootElement();
			Map<String, Object> map = (Map<String, Object>) xml2map(root);
			if (root.elements().size() == 0 && root.attributes().size() == 0) {
				opLogger.error("root is null:" + resxml);
				return "error";
			}
			if(!map.containsKey("Result")){
				opLogger.error("Result key is null:" + resxml);
				return "error";
			}
			String resultCode=String.valueOf(map.get("Result"));
			if(!"1".equals(resultCode)){
				opLogger.error("sms response code:" + resultCode);
				return "error";
			}
			return "success";
		} catch (Exception e1) {
			opLogger.error("畅天游短信发送失败:",e1);
			return "error";
		}
	}
	public static String sendSmsCode(String mobile,String code) {
		String un = Constant.un;
		String pwd = Constant.pwd;
		String msg = String.format("【Time Treaty Team】您的短信验证码是:%s，请于30分钟内填写。本条短信免费。", code);
		String param = "";
		String sendParam="";
		try {
			param = "un=" + un + "&pwd=" + pwd + "&mobile=" + mobile + "&msg=" + URLEncoder.encode(msg);
			sendParam=param;
			String resxml = PostAndGet.sendGet("http://sms.800617.com:4400/sms/SendSMS.aspx", param);
			opLogger.info(resxml);
			if (StringUtils.isBlank(resxml)) {
				opLogger.error("sms response is null:" + sendParam);
				return "error";
			}
			Document doc = null;
			doc = DocumentHelper.parseText(resxml);
			Element root = doc.getRootElement();
			Map<String, Object> map = (Map<String, Object>) xml2map(root);
			if (root.elements().size() == 0 && root.attributes().size() == 0) {
				opLogger.error("root is null:" + resxml);
				return "error";
			}
			if(!map.containsKey("Result")){
				opLogger.error("Result key is null:" + resxml);
				return "error";
			}
			String resultCode=String.valueOf(map.get("Result"));
			if(!"1".equals(resultCode)){
				opLogger.error("sms response code:" + resultCode);
				return "error";
			}
			return "success";
		} catch (Exception e1) {
			opLogger.error("畅天游短信发送失败:",e1);
			return "error";
		}
	}
	/**
	 * xml转map 不带属性
	 * 
	 * @param e
	 * @return
	 */
	private static Map xml2map(Element e) {
		Map map = new LinkedHashMap();
		List list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if (iter.elements().size() > 0) {
					Map m = xml2map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!(obj instanceof List)) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj instanceof List) {
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), m);
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!(obj instanceof List)) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj instanceof List) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), iter.getText());
				}
			}
		} else
			map.put(e.getName(), e.getText());
		return map;
	}
}
