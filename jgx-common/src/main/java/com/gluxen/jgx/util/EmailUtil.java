/**   
* @Description:  
* @author sty   
* @date 2017年4月11日 上午11:37:10 
* @version V1.0   
*/
package com.gluxen.jgx.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
	
		/** 
		* @param args 
		*/ 
		public  void sendEmail(String host,String from,String to,String subject,String text,String userName,String password,String timeout) throws Exception{ 
		    JavaMailSenderImpl senderImpl = new JavaMailSenderImpl(); 
		    
		    //设定mail server 
		    senderImpl.setHost(host); 
		    
		    //建立邮件消息,发送简单邮件和html邮件的区别 
		    MimeMessage mailMessage = senderImpl.createMimeMessage(); 
		    MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage); 
		             
		    //设置收件人，寄件人 
		    messageHelper.setTo(to); 
		    messageHelper.setFrom(from); 
		    messageHelper.setSubject(subject); 
		    //true 表示启动HTML格式的邮件 
		    messageHelper.setText(text,true); 
		    
		    senderImpl.setUsername(userName) ; // 根据自己的情况,设置username
		    senderImpl.setPassword(password) ; // 根据自己的情况, 设置password
		    Properties prop = new Properties() ;
		    prop.put("mail.smtp.auth", "true") ; // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		    prop.put("mail.smtp.timeout", timeout) ; 
		    senderImpl.setJavaMailProperties(prop); 
		    //发送邮件 
		    senderImpl.send(mailMessage); 
		    
		} 
		
		
	
}
