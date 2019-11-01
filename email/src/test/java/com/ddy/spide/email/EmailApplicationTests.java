package com.ddy.spide.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("1171148438@qq.com","test simple mail"," hello this is simple mail");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("1171148438@qq.com","test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath= "d:\\dubbo.xsd"; //"e:\\tmp\\application.log";
        mailService.sendAttachmentsMail("1171148438@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
//        String imgPath = "C:\\Users\\summer\\Pictures\\favicon.png";
        String imgPath = "C:\\Users\\lenovo\\AppData\\Local\\Temp\\1571363458.png";
        mailService.sendInlineResourceMail("1171148438@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }
    @Test
    public void sendInlineResourceMail1() {
        mailService.sendTemplateMail();
    }



}
