package weixin.mailmanager;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晓春 on 2016/4/25.
 */
public class mailtest {
    public static final transient Logger LOGGER = Logger.getLogger(mailtest.class);
    /**
     2.*用spring mail 发送邮件,依赖jar：spring.jar，activation.jar，mail.jar
     3.*/
   public static void sendFileMail() throws MessagingException {
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
            //邮件服务器中的配置从配置文件中获取
            // 设定mail server
            senderImpl.setHost("smtp.126.com");
             senderImpl.setUsername("yuhan0");
             senderImpl.setPassword("******");
             // 建立HTML邮件消息
             MimeMessage mailMessage = senderImpl.createMimeMessage();
             // true表示开始附件模式
          MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

          // 设置收件人，寄件人
          messageHelper.setTo("slimes@126.com");
          messageHelper.setFrom("yuhan0@126.com");
          messageHelper.setSubject("测试邮件！");
          // true 表示启动HTML格式的邮件
          messageHelper.setText("<html><head></head><body><h1>你好：附件！！</h1></body></html>", true);

          FileSystemResource file1 = new FileSystemResource(new File("d:/logo.jpg"));
          FileSystemResource file2 = new FileSystemResource(new File("d:/读书.txt"));
          /* 添加2个附件 */
          messageHelper.addAttachment("logo.jpg", file1);

          try {
                  //附件名有中文可能出现乱码
                  messageHelper.addAttachment(MimeUtility.encodeWord("读书.txt"), file2);
              } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
                  throw new MessagingException();
              }
          // 发送邮件
          senderImpl.send(mailMessage);
          LOGGER.info("邮件发送成功.....");

            }






    /**
     2.*用apache commons-email 发送邮件
     3.*依赖jar：commons-email.jar，activation.jar，mail.jar
     4.*/
    public static void sendMutiMessage() {

       MultiPartEmail email = new MultiPartEmail();
       String[] multiPaths = new String[] { "D:/1.jpg", "D:/2.txt" };

        List<EmailAttachment> list = new ArrayList<EmailAttachment>();
        for (int j = 0; j < multiPaths.length; j++) {
                EmailAttachment attachment = new EmailAttachment();
                //判断当前这个文件路径是否在本地  如果是：setPath  否则  setURL;
        if (multiPaths[j].indexOf("http") == -1) {
                attachment.setPath(multiPaths[j]);
            } else {
                try {
                        attachment.setURL(new URL(multiPaths[j]));
                    } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                    }
           attachment.setDisposition(EmailAttachment.ATTACHMENT);
           attachment.setDescription("Picture of John");
                      list.add(attachment);
           }

            try {
                 // 这里是发送服务器的名字：
                 email.setHostName("smtp.126.com");
                 // 编码集的设置
                 email.setCharset("utf-8");
                 // 收件人的邮箱
                 email.addTo("slimes@126.com");
                 // 发送人的邮箱
                 email.setFrom("yuhan0@126.com");
                 // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
                 email.setAuthentication("yuhan0", "******");
                 email.setSubject("这是一封测试邮件");
                 // 要发送的信息
                 email.setMsg("<b><a href=\"http://www.baidu.com\">邮件测试内容</a></b>");

                 for (int a = 0; a < list.size(); a++) //添加多个附件
                     {
                         email.attach(list.get(a));
                     }
                 // 发送
                 email.send();
             } catch (EmailException e) {
                 e.printStackTrace();
             }
          }


}
