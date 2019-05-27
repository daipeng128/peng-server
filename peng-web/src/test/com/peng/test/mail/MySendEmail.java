package com.peng.test.mail;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 1、创建连接对象
 *           设置邮件发送的协议
 *           设置发送邮件的服务器
 *           填写自己的密钥
 *
 * 2、创建邮件对象
 *         设置发件人
 *         设置收件人
 *         设置抄送者
 *         设置邮件主题
 *         设置邮件内容
 *
 * 3、发送邮件
 */
public class MySendEmail {



	//批量发送邮箱
	public static void sendEmail(String userName,String password,
								 String sendTo,String subject,String text,
								 List<Map<String,String>> files) {
		try {
			//判断接收方邮箱协议
			String smtp_server = getSmtp(userName);

			//格式化
			String[] to = sendTo.replace("；",";").split(";");


			//创建连接对象
			Properties mailProps = new Properties();
			//1.1设置邮件发送的协议
			mailProps.put("mail.transport.protocol" , "smtp");
			//1.2设置发送邮件的服务器
			mailProps.put("mail.smtp.host", smtp_server);
			//1.3需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
			mailProps.put("mail.smtp.auth", "true");
			//1.4下面一串是发送邮件用465端口，如果不写就是以25端口发送，阿里云已经关闭了25端口
			mailProps.setProperty("mail.smtp.port", "465");//qq 163 [465][587]
			mailProps.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			mailProps.put("username", userName);
			mailProps.put("password", password);

			//1.5认证信息(用户名和密码)
			Session mailSession = Session.getInstance(mailProps , new Authenticator() {
				protected PasswordAuthentication
				getPasswordAuthentication(){
					return new PasswordAuthentication(userName , password);
				}
			});
			mailSession.setDebug(true);
			MimeMessage message = new MimeMessage(mailSession);
			// true表示开始附件模式
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
			
			
			//这是收件人
			messageHelper.setTo(to);
			//这是发件人
			messageHelper.setFrom(new InternetAddress(userName));
			//设置标题
			messageHelper.setSubject(subject);//subject
			// true 表示启动HTML格式的邮件
			messageHelper.setText("<html><head></head><body>"+text+"</body></html>", true);
			
			if(null != files){
				for (int i = 0; i < files.size(); i++) {
					Map<String,String> file = files.get(i);
					FileSystemResource file1 = new FileSystemResource(new File(file.get("path")));
					messageHelper.addAttachment(MimeUtility.encodeWord(file.get("fileName")), file1);
				}
				//附件名有中文可能出现乱码			
				/* 添加2个附件
			FileSystemResource file2 = new FileSystemResource(new File("a:/测试.txt"));
			messageHelper.addAttachment("logo.jpg", file1);*/
			}
			
			Transport.send(message);
			
			System.out.println("发送邮件成功！");
		} catch (Exception ex) {
			System.err.println("邮件发送失败的原因是：" + ex.getMessage());
			System.err.println("具体的错误原因");
			ex.printStackTrace(System.err);
		}
	}



	public static String getSmtp(String userName){

		if(userName.contains("@qq.")){
			return "smtp.qq.com";
		}else if(userName.contains("@163.")){
			return "smtp.163.com";
		}else if (userName.contains("@sina.")) {
			return "smtp.sina.com";
		}

		return null;

	}
}