package com.liyunet.common.password;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DesString {

	private static Log log=LogFactory.getLog(DesString.class);
	public static final String desSecret="liyuke$#3423";//des加解密密钥
	public DES getDes(String strKey){
		DES des = null;
		try{
			des = new DES(strKey);
		}catch (Exception e){
			 log.error("创建des错误"+e);
		}
		return des;
	}
		
	/**
	 * 加密
	 * @param te（明文）
	 * @return 密文
	 */
	public String encrypt(String te,String strKey){
		return getDes(strKey).encrypt(te);
	}
	
	/**
	 * 解密
	 * @param te  密文
	 * @return 明文
	 */
	public String decrypt(String te,String strKey){
		return getDes(strKey).decrypt(te);
	}
	
	/**
	 * md5加密
	 * @param te
	 * @return
	 */
//	public String md5En(String te){
//		MD5 md=new MD5();
//		return md.getMD5of32(te);
//	}
//	
//	public String encryption(String password,String strKey){
//		return md5En(encrypt(password,strKey));
//	}

	
	public String decryptXml(String xml,String secretKey){
		DesString ds=new DesString();
		String reXml;
		try {
			reXml=ds.decrypt(xml, secretKey);
			reXml = URLDecoder.decode(reXml,"utf-8");
			return reXml;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
}
