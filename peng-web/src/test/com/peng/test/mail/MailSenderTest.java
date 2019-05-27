package com.peng.test.mail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailSenderTest {
   private String host = "smtp.163.com";
   private String port = "25";
   private String userName163 = "daipeng128@163.com";
   private String password163 = "daipeng128email";
   private String userNameQQ = "904839214@qq.com";
   private String passwordQQ = "ntudxivrhaibbajj";
   private String to = "904839214@qq.com";



   @Test
   public void test() {
      List<Map<String,String>> files = new ArrayList<>();
      String fileName = "IMG_3274.JPG";
      String path = "/Users/apple/peng/image/IMG_3274.JPG";
      Map<String,String> map = new HashMap<>();
      map.put("path",path);
      map.put("fileName",fileName);
      files.add(map);

      MySendEmail.sendEmail(userNameQQ, passwordQQ,"daipeng128@163.com","测试邮件","赵小雷",files);
   }
}