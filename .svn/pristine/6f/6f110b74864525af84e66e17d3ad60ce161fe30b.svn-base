package com.liyunet.common.mail;

import com.liyunet.common.util.StringUtil;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class MailSender {
	
	private static Logger logger = LogManager.getLogger("mail_sms");
	public static Properties properties = new Properties();
	
	static{
		properties.put("mail.smtp.host", "smtp.mxhichina.com");
		//properties.put("mail.debug", "true");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");
//		properties.put("username", "wuchengfu@liyunet.com");
//		properties.put("password", "fallen.1991");
		
		properties.put("username", "coin@timetreaty.org");
		properties.put("password", "Aa734165606");
		
		
		
//		properties.put("username", "postmaster@liyunet.com");
//		properties.put("password", "LIYUhome12345");
		
		
		
		properties.put("mail.smtp.auth", "true");
	}
	
	public static void send_email(String content) throws IOException, AddressException,
			MessagingException {

		String to = "439906701@qq.com";
		String subject = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()) + " 数据";// 邮件主题
		Properties properties = new Properties();
		
		//properties.put("mail.smtp.host", "smtp.aliyun.com");
		properties.put("mail.smtp.host", "smtp.mxhichina.com");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");
		properties.put("username", "wuchengfu@liyunet.com");
		properties.put("password", "fallen.1991");
		
		
		
		Authenticator authenticator = new EmailAuthenticator("wuchengfu@liyunet.com", "fallen.1991");
		
		Session sendMailSession = Session.getDefaultInstance(properties, authenticator);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);

		mailMessage.setFrom(new InternetAddress(properties.get("username").toString()));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mailMessage.setSubject(subject, "UTF-8");
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		html.setContent(content.trim(), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		Transport.send(mailMessage);
		System.out.println("mail send success");
	}

	//发送pv uv 详情页访问记录
	public void sendEmailWithPvUv(List list,String content) throws UnsupportedEncodingException, AddressException, MessagingException{

		//String test = "点击这个链接";
		String subject = "您的%s邮箱验证码为:123456";

		Authenticator authenticator = new EmailAuthenticator(properties.getProperty("username"), properties.getProperty("password"));
		Session sendMailSession = Session.getDefaultInstance(properties, authenticator);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		//本磊漫联
		String nickname = MimeUtility.encodeText("本磊漫联");
		mailMessage.setFrom(new InternetAddress(nickname + "<blml@liyunet.com>"));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("3028433705@qq.com"));
		mailMessage.setSubject(subject, "UTF-8");
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();

		html.setContent(content, "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		Transport.send(mailMessage);

	}


	public static void main(String[] args) throws AddressException, IOException, MessagingException {
		Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.aliyun.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        msg.setSubject("JavaMail测试");
        // 设置邮件内容
        msg.setText("这是一封由JavaMail发送的邮件！");
        // 设置发件人
        msg.setFrom(new InternetAddress("wuchengfu@liyunet.com"));

        Transport transport = session.getTransport();
        // 连接邮件服务器
        transport.connect("wuchengfu@liyunet.com", "fallen.1991");
        // 发送邮件
        transport.sendMessage(msg, new Address[] {new InternetAddress("403519557@qq.com")});
        // 关闭连接
        transport.close();

//		send_email("sdf");
//		sendEnpEnterEmail("tuyanghui@liyuhome.net","WRDFGF",35,2,false);
//		sendEnpEnterEmail("403519557@qq.com","WRDFGF",35,0,false);
//		sendEnpEnterEmail("tianxiao@liyunet.com","WRDFGF",35,2,false);
//		sendEnpEnterEmail("tianxiao@liyunet.com","WRDFGF",35,1,false);
//		sendEnpEnterEmail("tianxiao@liyunet.com","WRDFGF",35,2,false);
//		sendEnpEnterEmail("airong_xiao@163.com","WRDFGF",35,0,false);

//		sendRegOrPwdEmail(holder);

	}


	/**
	 * 发送企业入驻邮件
	 */
	public static void sendEnpEnterEmail(EnpEnterEmailInfo enpEnterEmailInfo) throws MessagingException, UnsupportedEncodingException {
		Authenticator authenticator = new EmailAuthenticator(properties.getProperty("username"), properties.getProperty("password"));

		Session sendMailSession = Session.getDefaultInstance(properties, authenticator);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);

		//本磊漫联
		String nickname = MimeUtility.encodeText(getNicknameByCountry(enpEnterEmailInfo.getCountry()));
		mailMessage.setFrom(new InternetAddress(nickname + "<blml@liyunet.com>"));
		//mailMessage.setFrom(new InternetAddress(nickname + "<postmaster@liyunet.com>"));
		//mailMessage.setFrom(new InternetAddress(nickname + "<wuchengfu@liyunet.com>"));

		String subject = getSubjectByCountry(enpEnterEmailInfo.getCountry());

		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(enpEnterEmailInfo.getEmail()));
		mailMessage.setSubject(subject, "UTF-8");
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();

		html.setContent(getHtmlByCountry(enpEnterEmailInfo), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		Transport.send(mailMessage);

		logger.info("邮件发送成功 收件邮箱为:" + enpEnterEmailInfo.getEmail());
	}



	private static String getNicknameByCountry(Integer country){
		switch(country){
			case 0 : return "本磊漫联";
			case 1 : return "Basics&Animation&Games United";
			case 2 : return "本磊漫联";
			default :return "本磊漫联";
		}
	}

	private static String getSubjectByCountry(Integer country){
		switch(country){
			case 0 : return "企业入驻确认";
			case 1 : return "Confirm your registration";
			case 2 : return "会員登録確認";
			default :return "企业入驻确认";
		}
	}


	private static String getHtmlByCountry(EnpEnterEmailInfo enpEnterEmailInfo){
		Integer country = enpEnterEmailInfo.getCountry();
		//邮箱验证token
		String token = EnpEnterEmailJSONToken.createJsonWebToken(enpEnterEmailInfo,TimeUnit.DAYS, 800L);

		String token_url = "http://www.liyunet.com/wap/enp/enter?token=" + token;
		String link = "http://www.liyunet.com/wap/enp/enter";
		//String token_url = "http://localhost:8080/liyu-web/wap/enp/enter?token=" + token;
		//String link = "http://localhost:8080/liyu-web/wap/enp/enter";
		if(country == 1){
			LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
			String htmlEN="<!DOCTYPE html><html><head><meta charset='UTF-8'><style type='text/css'>.mainPC {width: 100%;height: 100%;background-color: #f8f8f8;display: -webkit-flex;display: flex;justify-content: center;font-size: 15px;}.context {width: 750px;padding: 20px ;height: 100%;background-color: #ffffff;}.logoImg{width: 250px;height: 150px;padding-left:250px ;background-color: #FFFFFF;}.spaceLineTop{padding-bottom: 20px;border: none;border-top:1px solid #0070c0 ;overflow: hidden;}.spaceLineBtm{padding-top: 20px;border: none;border-bottom:1px solid #ff0000 ;overflow: hidden;}.font1{line-height: 50px;}.font2{line-height: 25px;margin-left: 50px;}.font3{margin-left: 50px;}.font4{text-align: right;line-height: 25px;margin-right: 50px;}.erixin{float: left;margin-left: 50px;margin: 20px;}.erixin img{width: 100px;height: 100px;padding-left: 300px;}.font5{float: left;line-height: 33px;width: 100%;text-align: center;}.font6{color: #548dd4;}.font7{color: #3fa8ff;}.font8{color: #FF0000;}.ff{margin-top: 20px;}</style></head><body><div class='mainPC'><div class='context'><div class='spaceLineTop'></div><div ><img class='logoImg'  src='http://www.liyunet.com/assets/public/email_logo_no_background.png' alt='' /></div><div class='font1'>Welcome to LIYU IP Index Platform.</div><div class='font3'>Please complete your registration and get the full benefits of your account: <a href='"
					+ token_url
					+ "'>"+link+"</a></div><div class='font3'>Click this link to view the initial password after registration. </div><div class='font4'> LIYU-HOME</div><div class='font4'>" + now.toString() +"</div><div class='spaceLineBtm'></div><div><div class='erixin'><img src='http://www.liyunet.com/assets/public/email_qr_code.jpg' alt='' /></div><div class='font5 ff'>WeChat：Scan QR code or search '<span class='font6'>鲤鱼家族</span>' to follow our offical account, find more insight IP information.</div><div class='font5'>Copyright ? 2016 LIYU HOME 鲤鱼家族.All Rights Reserved.</div><div class='font5'>This is an automatic message from the<span class='font7'> LIYU Home registration system</span>. <span class='font8'>Please do not reply to this email</span>. In case of any questions or doubts, please contact us at info@liyunet.com</div></div></div></div></body></html>";
			return htmlEN;
		}else if(country == 2){
			LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
			String htmlJP = "<!DOCTYPE html><html><head><meta charset='UTF-8'><style type='text/css'>.mainPC {width: 100%;height: 100%;background-color: #f8f8f8;display: -webkit-flex;display: flex;justify-content: center;font-size: 15px;}.context {width: 750px;padding: 20px ;height: 100%;background-color: #ffffff;}.logoImg{width: 250px;height: 150px;padding-left:250px ;background-color: #FFFFFF;}.spaceLineTop{padding-bottom: 20px;border: none;border-top:1px solid #0070c0 ;overflow: hidden;}.spaceLineBtm{padding-top: 20px;border: none;border-bottom:1px solid #ff0000 ;overflow: hidden;}.font1{line-height: 50px;}.font2{line-height: 25px;margin-left: 50px;}.font3{margin-left: 50px;}.font4{text-align: right;line-height: 25px;margin-right: 50px;}.erixin{float: left;margin-left: 50px;margin: 20px;}.erixin img{width: 100px;height: 100px;padding-left: 300px;}.font5{float: left;line-height: 33px;width: 100%;text-align: center;}.font6{color: #548dd4;}.font7{color: #3fa8ff;}.font8{color: #FF0000;}.ff{margin-top: 20px;}</style></head><body><div class='mainPC'><div class='context'><div class='spaceLineTop'></div><div ><img class='logoImg'  src='http://www.liyunet.com/assets/public/email_logo_no_background.png' alt='' /></div><div class='font1'> 鯉魚（リーユー）国際IPインデックスプラットフォームへようこそ！</div><div class='font3'><a href='"
					+ token_url
					+ "'>" + link +"</a>リンクをクリックしてから、プラットフォームのビッピになることを確認します。</div><div class='font3'>会員登録終了後，クリックすると 初期パスワードを入手することができます.</div><div class='font1'> 鯉魚（リーユー）国際IPインデックスプラットフォームはビッピに以下のサービスを提供致します。</div><div class='font2'> 1.プラットフォームから認証マークをもらい、尊いビッピを見せられます。</div><div class='font2'>2.IP価値開発に関する評価報告書を読めます。</div><div class='font2'>3.プラットフォームで貴社と貴社のIPを宣伝できる広告を差し上げます。</div><div class='font2'> 4.最新のIP情報を提供致します。</div><div class='font2'>5.ビジネスチャンスを発見できます。</div><div class='font4'> LIYU-HOME</div><div class='font4'>"+ now.toString() +"</div><div class='spaceLineBtm'></div><div><div class='erixin'><img src='http://www.liyunet.com/assets/public/email_qr_code.jpg' alt='' /></div><div class='font5 ff'>Wechat：ORコードをスキャンし、或いは「'<span class='font6'>鲤鱼家族</span>'」を検索してから、もっと多くのIP情報を発見できます。</div><div class='font5'>Copyright ? 2016 LIYU-HOME 鯉魚（リーユー）家族.All Rights Reserved.</div><div class='font5'><span class='font7'>システム</span>から送信したメールですので、回復<span class='font8'>しないでください。</span>(何か問題があれば、カスタマー?サービスと連絡してください。info@liyunet.com)</div></div></div></div></body></html>";
			return htmlJP;
		}else{
			LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
			String htmlCN = "<!DOCTYPE html><html><head><meta charset='UTF-8'><style type='text/css'>.mainPC {width: 100%;height: 100%;background-color: #f8f8f8;display: -webkit-flex;display: flex;justify-content: center;font-size: 15px;}.context {width: 750px;padding: 20px ;height: 100%;background-color: #ffffff;}.logoImg{width: 250px;height: 150px;padding-left:250px ;background-color: #FFFFFF;}.spaceLineTop{padding-bottom: 20px;border: none;border-top:1px solid #0070c0 ;overflow: hidden;}.spaceLineBtm{padding-top: 20px;border: none;border-bottom:1px solid #ff0000 ;overflow: hidden;}.font1{line-height: 50px;}.font2{line-height: 25px;margin-left: 50px;}.font3{margin-left: 50px;}.font4{text-align: right;line-height: 25px;margin-right: 50px;}.erixin{float: left;margin-left: 50px;margin: 20px;}.erixin img{width: 100px;height: 100px;padding-left: 300px;}.font5{float: left;line-height: 33px;width: 100%;text-align: center;}.font6{color: #548dd4;}.font7{color: #3fa8ff;}.font8{color: #FF0000;}.ff{margin-top: 20px;}</style></head><body><div class='mainPC'><div class='context'><div class='spaceLineTop'></div><div ><img class='logoImg'  src='http://www.liyunet.com/assets/public/email_logo_no_background.png' alt='' /></div><div class='font1'> 尊敬的用户，欢迎您加入鲤鱼！</div><div class='font3'><a href='"
					+ token_url
					+ "'>" + link +"</a>单击此链接，确认入驻平台。</div><div class='font3'>入驻成功后再次点击此链接可查看初始密码</div><div class='font4'> LIYU-HOME</div><div class='font4'>" + now.toString() + "</div><div class='spaceLineBtm'></div><div><div class='erixin'><img src='http://www.liyunet.com/assets/public/email_qr_code.jpg' alt='' /></div><div class='font5 ff'>微信：扫描二维码或搜索公众号'<span class='font6'>鲤鱼家族</span>'，发现更多精彩IP资讯。</div><div class='font5'>Copyright © 2016 LIYU-HOME 鲤鱼家族.All Rights Reserved.</div><div class='font5'>此邮件由<span class='font7'>系统</span>发出，<span class='font8'>请勿</span>直接回复(有问题请联系客服info@liyunet.com)</div></div></div></div></body></html>";
			return htmlCN;
		}
	}

	public static void sendEmailByTencent(RegOrPwdEmailHolder holder) {
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", "smtp");
        //服务器
        prop.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        //端口
        prop.setProperty("mail.smtp.port", "465");
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //
        //获取Session对象
        Session s = Session.getDefaultInstance(prop,new Authenticator() {
            //此访求返回用户和密码的对象
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
//                PasswordAuthentication pa = new PasswordAuthentication("ttc@timetreaty.com", "Aa439906701");
            	 PasswordAuthentication pa = new PasswordAuthentication("admin@timetreaty.com", "gjp5DCBDTxHEg5DG");
                return pa;
            }
        });
        //设置session的调试模式，发布时取消
        //s.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(s);
        try {
//            mimeMessage.setFrom(new InternetAddress("ttc@timetreaty.com","Timetreaty"));
        	mimeMessage.setFrom(new InternetAddress("admin@timetreaty.com","Timetreaty"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(holder.getEmail()));
            mimeMessage.setSentDate(new Date());
            //设置内容
            String  content="";
            //设置主题
            if("en".equals(holder.getLanguageType())){
            	 mimeMessage.setSubject("The email verification code");
//            	 content = "<!DOCTYPE html><html>	<head><meta charset='UTF-8'><style type='text/css'>a{text-decoration: none !important;}.mainPC {width: 100%;height: 100%;background-color: #f8f8f8;display: -webkit-flex;display: flex;justify-content: center;font-size: 15px;	}.context {width: 750px;padding: 20px;height: 100%;background-color: #ffffff;	}.spaceLineTop {padding-bottom: 20px;border: none;border-top: 1px solid #0070c0;overflow: hidden;	}.spaceLineBtm {padding-top: 20px;border: none;border-bottom: 1px solid #ff0000;overflow: hidden;	}.font5 {float: left;line-height: 33px;width: 100%;text-align: center;	}.font7 {color: #3fa8ff;	}.font8 {color: #FF0000;	}.verlidation {width: 550px;margin: 0 100px;border: 1px solid #E1E1E1;border-radius: 5px;overflow: hidden;	}.title {height: 54px;background-color: #0373d6;font-size: 22px;color: #FFFFFF;text-align: center;line-height: 54px;	}.hello {font-weight: bold;background-color: #ffffff;font-size: 17px;color: #7b7b7b;padding: 25px 0 0 25px;line-height: 25px;	}.verMes {width: 500px;font-size: 14px;color: #7b7b7b;line-height: 25px;font-family: Hiragino Sans GB;padding: 25px	}.verNum {border-bottom: 1px dashed rgb(204, 204, 204);padding: 10px 18px 10px 18px;border-radius: 3px;text-align: center;text-decoration: none;background-color: #ecf4fb;color: #4581E9;font-size: 20px;font-weight: 700;letter-spacing: 2px;margin: 0;display: inline-block;white-space: nowrap;margin: 0 0 25px 220px;	}</style>	</head>	<body><div class='mainPC'>	<div class='context'><div class='spaceLineTop'></div><div class=\"verlidation\">	<a href = \"http://www.timetreaty.org\"><div class=\"title\">Timetreaty Chain Official Website</div></a>	<div class=\"hello\">Hi,</div>	<div class=\"verMes\">" + holder.getEmail() +": your verification code is as followed (letters are not case-sensitive).Please enter the code within 30 minutes to proceed the operation. If it is not your request, just ignore it.</div>	<span class=\"verNum\">" + holder.getCode() +"</span></div><div class='spaceLineBtm'></div><div>	<div class='font5'>Copyright 2017 TIMETREATY.All Rights Reserved.</div>	<div class='font5'>This email is sent automatically. Please <span class='font8'>do not</span> reply (If you have any questions, please contact Customer Service at ttc@timetreaty.com). </div></div>	</div></div></body></html>";
				 content = "<!DOCTYPE html><html>	<head><meta charset='UTF-8'><style type='text/css'>a{text-decoration: none !important;}.mainPC {width: 100%;height: 100%;background-color: #f8f8f8;display: -webkit-flex;display: flex;justify-content: center;font-size: 15px;	}.context {width: 750px;padding: 20px;height: 100%;background-color: #ffffff;	}.spaceLineTop {padding-bottom: 20px;border: none;border-top: 1px solid #0070c0;overflow: hidden;	}.spaceLineBtm {padding-top: 20px;border: none;border-bottom: 1px solid #ff0000;overflow: hidden;	}.font5 {float: left;line-height: 33px;width: 100%;text-align: center;	}.font7 {color: #3fa8ff;	}.font8 {color: #FF0000;	}.verlidation {width: 550px;margin: 0 100px;border: 1px solid #E1E1E1;border-radius: 5px;overflow: hidden;	}.title {height: 54px;background-color: #0373d6;font-size: 22px;color: #FFFFFF;text-align: center;line-height: 54px;	}.hello {font-weight: bold;background-color: #ffffff;font-size: 17px;color: #7b7b7b;padding: 25px 0 0 25px;line-height: 25px;	}.verMes {width: 500px;font-size: 14px;color: #7b7b7b;line-height: 25px;font-family: Hiragino Sans GB;padding: 25px	}.verNum {border-bottom: 1px dashed rgb(204, 204, 204);padding: 10px 18px 10px 18px;border-radius: 3px;text-align: center;text-decoration: none;background-color: #ecf4fb;color: #4581E9;font-size: 20px;font-weight: 700;letter-spacing: 2px;margin: 0;display: inline-block;white-space: nowrap;margin: 0 0 25px 220px;	}</style>	</head>	<body><div class='mainPC'>	<div class='context'><div class='spaceLineTop'></div><div class=\"verlidation\">	<a href = \"http://www.timetreaty.org\"><div class=\"title\">Timetreaty Chain Official Website</div></a>	<div class=\"hello\">Hi,</div>	<div class=\"verMes\">" + holder.getEmail() +":the verification code that you have applied for is as follows (case-insensitive). Please enter the verification code in 30 minutes for the next step.If it is not operated by yourself, please ignore this email.</div>	<span class=\"verNum\">" + holder.getCode() +"</span></div><div class='spaceLineBtm'></div><div>	<div class='font5'>Copyright 2017 TIMETREATY.All Rights Reserved.</div>	<div class='font5'>This email is sent automatically. Please <span class='font8'>do not</span> reply (If you have any questions, please contact Customer Service at ttc@timetreaty.com). </div></div>	</div></div></body></html>";
            }else if("zh".equals(holder.getLanguageType())){
            
//            	 mimeMessage.setSubject("邮件验证码");
            	 mimeMessage.setSubject(MimeUtility.encodeText("邮件验证码",MimeUtility.mimeCharset("gb2312"), null)); 
            	 content = "<!DOCTYPE html><html>	<head><meta charset='UTF-8'><style type='text/css'>a{text-decoration: none !important;}.mainPC {width: 100%;height: 100%;background-color: #f8f8f8;display: -webkit-flex;display: flex;justify-content: center;font-size: 15px;	}.context {width: 750px;padding: 20px;height: 100%;background-color: #ffffff;	}.spaceLineTop {padding-bottom: 20px;border: none;border-top: 1px solid #0070c0;overflow: hidden;	}.spaceLineBtm {padding-top: 20px;border: none;border-bottom: 1px solid #ff0000;overflow: hidden;	}.font5 {float: left;line-height: 33px;width: 100%;text-align: center;	}.font7 {color: #3fa8ff;	}.font8 {color: #FF0000;	}.verlidation {width: 550px;margin: 0 100px;border: 1px solid #E1E1E1;border-radius: 5px;overflow: hidden;	}.title {height: 54px;background-color: #0373d6;font-size: 22px;color: #FFFFFF;text-align: center;line-height: 54px;	}.hello {font-weight: bold;background-color: #ffffff;font-size: 17px;color: #7b7b7b;padding: 25px 0 0 25px;line-height: 25px;	}.verMes {width: 500px;font-size: 14px;color: #7b7b7b;line-height: 25px;font-family: Hiragino Sans GB;padding: 25px	}.verNum {border-bottom: 1px dashed rgb(204, 204, 204);padding: 10px 18px 10px 18px;border-radius: 3px;text-align: center;text-decoration: none;background-color: #ecf4fb;color: #4581E9;font-size: 20px;font-weight: 700;letter-spacing: 2px;margin: 0;display: inline-block;white-space: nowrap;margin: 0 0 25px 220px;	}</style>	</head>	<body><div class='mainPC'>	<div class='context'><div class='spaceLineTop'></div><div class=\"verlidation\">	<a href = \"http://www.timetreaty.org\"><div class=\"title\">时间条约链官网</div></a>	<div class=\"hello\">您好,</div>	<div class=\"verMes\">" + holder.getEmail() +": 你申请的验证码如下(不区分大小写),请在 30 分钟内输入验证码进行下一步操作。 如非你本人操作，请忽略此邮件。</div>	<span class=\"verNum\">" + holder.getCode() +"</span></div><div class='spaceLineBtm'></div><div>	<div class='font5'>Copyright 2017 TIMETREATY 时间条约.All Rights Reserved.</div>	<div class='font5'>此邮件由<span class='font7'>系统</span>发出，<span class='font8'>请勿</span>直接回复(有问题请联系客服coin@timetreaty.org)</div></div>	</div></div></body></html>";
            }

           // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=UTF-8");

            multipart.addBodyPart(contentPart);
            mimeMessage.setContent(multipart);
          //  mimeMessage.setText(content);
            mimeMessage.saveChanges();
            //发送
            Transport.send(mimeMessage);
        } catch (Exception e) {
        	logger.info(StringUtil.exceptionToStr(e));
        }
    }


	/**
	 * 发送注册或者忘记密码
	 * @param holder
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendRegOrPwdEmail(RegOrPwdEmailHolder holder) {
		sendEmailByTencent(holder);
//		StringBuilder sb = new StringBuilder();
//		try {
//			RegOrPwdEmailType type = holder.getType();
//			String content = "";
//			String email = holder.getEmail();
//			String code = holder.getCode();
//
//			String subject = "您的%s邮箱验证码为: %s";
//
//			if(type == RegOrPwdEmailType.REG){
//				content = "<!DOCTYPE html><html>	<head><meta charset='UTF-8'><style type='text/css'>a{text-decoration: none !important;}.mainPC {width: 100%;height: 100%;background-color: #f8f8f8;display: -webkit-flex;display: flex;justify-content: center;font-size: 15px;	}.context {width: 750px;padding: 20px;height: 100%;background-color: #ffffff;	}.spaceLineTop {padding-bottom: 20px;border: none;border-top: 1px solid #0070c0;overflow: hidden;	}.spaceLineBtm {padding-top: 20px;border: none;border-bottom: 1px solid #ff0000;overflow: hidden;	}.font5 {float: left;line-height: 33px;width: 100%;text-align: center;	}.font7 {color: #3fa8ff;	}.font8 {color: #FF0000;	}.verlidation {width: 550px;margin: 0 100px;border: 1px solid #E1E1E1;border-radius: 5px;overflow: hidden;	}.title {height: 54px;background-color: #0373d6;font-size: 22px;color: #FFFFFF;text-align: center;line-height: 54px;	}.hello {font-weight: bold;background-color: #ffffff;font-size: 17px;color: #7b7b7b;padding: 25px 0 0 25px;line-height: 25px;	}.verMes {width: 500px;font-size: 14px;color: #7b7b7b;line-height: 25px;font-family: Hiragino Sans GB;padding: 25px	}.verNum {border-bottom: 1px dashed rgb(204, 204, 204);padding: 10px 18px 10px 18px;border-radius: 3px;text-align: center;text-decoration: none;background-color: #ecf4fb;color: #4581E9;font-size: 20px;font-weight: 700;letter-spacing: 2px;margin: 0;display: inline-block;white-space: nowrap;margin: 0 0 25px 220px;	}</style>	</head>	<body><div class='mainPC'>	<div class='context'><div class='spaceLineTop'></div><div class=\"verlidation\">	<a href = \"http://www.timetreaty.org\"><div class=\"title\">时间条约币官网</div></a>	<div class=\"hello\">您好,</div>	<div class=\"verMes\">" + email +": 你申请注册的验证码如下(不区分大小写)，请在 30 分钟内输入验证码进行下一步操作。 如非你本人操作，请忽略此邮件。</div>	<span class=\"verNum\">" + code +"</span></div><div class='spaceLineBtm'></div><div>	<div class='font5'>Copyright 2016 LIYU-HOME 鲤鱼家族.All Rights Reserved.</div>	<div class='font5'>此邮件由<span class='font7'>系统</span>发出，<span class='font8'>请勿</span>直接回复(有问题请联系客服info@liyunet.com)</div></div>	</div></div></body></html>";
//				subject = String.format(subject, "注册",code);
//			}else if (type == RegOrPwdEmailType.PWD){
//				content = "<!DOCTYPE html><html>	<head><meta charset='UTF-8'><style type='text/css'>a{text-decoration: none !important;}.mainPC {width: 100%;height: 100%;background-color: #f8f8f8;display: -webkit-flex;display: flex;justify-content: center;font-size: 15px;	}.context {width: 750px;padding: 20px;height: 100%;background-color: #ffffff;	}.spaceLineTop {padding-bottom: 20px;border: none;border-top: 1px solid #0070c0;overflow: hidden;	}.spaceLineBtm {padding-top: 20px;border: none;border-bottom: 1px solid #ff0000;overflow: hidden;	}.font5 {float: left;line-height: 33px;width: 100%;text-align: center;	}.font7 {color: #3fa8ff;	}.font8 {color: #FF0000;	}.verlidation {width: 550px;margin: 0 100px;border: 1px solid #E1E1E1;border-radius: 5px;overflow: hidden;	}.title {height: 54px;background-color: #0373d6;font-size: 22px;color: #FFFFFF;text-align: center;line-height: 54px;	}.hello {font-weight: bold;background-color: #ffffff;font-size: 17px;color: #7b7b7b;padding: 25px 0 0 25px;line-height: 25px;	}.verMes {width: 500px;font-size: 14px;color: #7b7b7b;line-height: 25px;font-family: Hiragino Sans GB;padding: 25px	}.verNum {border-bottom: 1px dashed rgb(204, 204, 204);padding: 10px 18px 10px 18px;border-radius: 3px;text-align: center;text-decoration: none;background-color: #ecf4fb;color: #4581E9;font-size: 20px;font-weight: 700;letter-spacing: 2px;margin: 0;display: inline-block;white-space: nowrap;margin: 0 0 25px 220px;	}</style>	</head>	<body><div class='mainPC'>	<div class='context'><div class='spaceLineTop'></div><div class=\"verlidation\">	<a href = \"http://www.timetreaty.org\"><div class=\"title\">时间条约币官网</div></a>	<div class=\"hello\">您好,</div>	<div class=\"verMes\">" + email +": 你申请找回密码的验证码如下(不区分大小写)，请在 30 分钟内输入验证码进行下一步操作。 如非你本人操作，请忽略此邮件。</div>	<span class=\"verNum\">" + code +"</span></div><div class='spaceLineBtm'></div><div>	<div class='font5'>Copyright 2016 LIYU-HOME 鲤鱼家族.All Rights Reserved.</div>	<div class='font5'>此邮件由<span class='font7'>系统</span>发出，<span class='font8'>请勿</span>直接回复(有问题请联系客服info@liyunet.com)</div></div>	</div></div></body></html>";
//				subject = String.format(subject, "找回密码",code);
//			}
//
//			Authenticator authenticator = new EmailAuthenticator(properties.getProperty("username"), properties.getProperty("password"));
//			javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
//			MimeMessage mailMessage = new MimeMessage(sendMailSession);
//			//时间条约
//			String nickname = MimeUtility.encodeText("时间条约");
//			mailMessage.setFrom(new InternetAddress(nickname + "<blml@liyunet.com>"));
//
//			// Message.RecipientType.TO属性表示接收者的类型为TO
//			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
//			mailMessage.setSubject(subject, "UTF-8");
//			mailMessage.setSentDate(new Date());
//			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
//			Multipart mainPart = new MimeMultipart();
//			// 创建一个包含HTML内容的MimeBodyPart
//			BodyPart html = new MimeBodyPart();
//
//			html.setContent(content, "text/html; charset=utf-8");
//			mainPart.addBodyPart(html);
//			mailMessage.setContent(mainPart);
//			Transport.send(mailMessage);
//
//	    	sb.append(LocalDateTime.now().toString() + "\t");
//	    	sb.append(holder.getEmail() + "\t");
//	    	sb.append(EmailSMSTypeEnum.EMAIL + "\t");
//	    	sb.append(0);
//		} catch (Exception e) {
//			sb.append(LocalDateTime.now().toString() + "\t");
//			sb.append(holder.getEmail() + "\t");
//			sb.append(EmailSMSTypeEnum.EMAIL + "\t");
//			sb.append(1);
//		}
//		logger.info(sb.toString());
	}

	public static void sendContactUsEmail(String ipName , String phoneNum) throws MessagingException, UnsupportedEncodingException {
		Authenticator authenticator = new EmailAuthenticator(properties.getProperty("username"), properties.getProperty("password"));
		Session sendMailSession = Session.getDefaultInstance(properties, authenticator);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		String nickname = MimeUtility.encodeText("本磊漫联");
		mailMessage.setFrom(new InternetAddress(nickname + "<blml@liyunet.com>"));
		String subject = "[" + ipName + "] 有联系人留了联系方式";
		String email = "wuchengfu@liyunet.com";
		InternetAddress[] addresses = new InternetAddress[4];
		addresses[0] = new InternetAddress("shuyu@liyunet.com");
		addresses[1] = new InternetAddress("tuyanghui@liyuhome.com");
		addresses[2] = new InternetAddress("info@lssip.com");
		addresses[3] = new InternetAddress("tianxiao@liyunet.com");


		mailMessage.addRecipients(Message.RecipientType.TO,addresses);
		//mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		mailMessage.setSubject(subject, "UTF-8");
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		String content = "对方联系方式为:" + phoneNum + "<br> 对方意向IP名称为:[" + ipName +"]";
		html.setContent(content, "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		Transport.send(mailMessage);
	}
}