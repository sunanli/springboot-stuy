package com.tangwh;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEmailApplicationTests {

    /**
     * 发送简单邮件
     */
    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Test
    public  void contextLoads() {

        SimpleMailMessage message = new SimpleMailMessage();


        message.setSubject("这个是测试邮件主题");

        message.setText("这是测试文件内容");
        // 邮件是谁发送的? 配置文件配置的什么 就是什么
        message.setFrom("t2678691035@163.com");
          // 发送时间
        message.setSentDate(new Date());
        // 邮件要发送给谁
        message.setTo("2678691035@qq.com");

        javaMailSender.send(message);
    }

    /**
     * 测试邮件 发送邮件
     */
    @Test
    public void test1() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        message.setSubject("这个是测试邮件主题(带附件)");

        helper.setText("这是测试文件内容(带附件)");
        // 邮件是谁发送的? 配置文件配置的什么 就是什么
        helper.setFrom("t2678691035@163.com");
        // 发送时间
        helper.setSentDate(new Date());
        // 邮件要发送给谁
        helper.setTo("2678691035@qq.com");

        helper.addAttachment("附件的名字Howe.jpg",new File("C:\\Users\\86177\\Pictures\\Saved Pictures\\Howe.jpg"));
        javaMailSender.send(message);
    }

    /**
     * 带照片资源的发送邮件
     */
    @Test
    public void test2() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        message.setSubject("这个是测试邮件主题(带照片)");

        helper.setText("这是测试文件内容(带照片),这是第一张照片:<img src='cid:p01'>,这是第二张照片:<img src='cid:p02'>",true);
        // 邮件是谁发送的? 配置文件配置的什么 就是什么
        helper.setFrom("t2678691035@163.com");
        // 发送时间
        helper.setSentDate(new Date());
        // 邮件要发送给谁
        helper.setTo("2678691035@qq.com");

        helper.addInline("p01", new FileSystemResource(new File("C:\\Users\\86177\\Pictures\\Saved Pictures\\Howe.jpg")));
        helper.addInline("p02", new FileSystemResource(new File("C:\\Users\\86177\\Pictures\\Saved Pictures\\Howe" +
                ".jpg")));

        javaMailSender.send(message);



    }

    /**
     * Thymeleaf模板引擎 邮件测试
     */
    @Autowired
    TemplateEngine templateEngine;
    @Test
    public void thymeleafTest() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        message.setSubject("这个是测试邮件主题(Thymeleaf)");

        Context context = new Context();
           context.setVariable("username", "javaboy");
           context.setVariable("position", "java工程师");
           context.setVariable("dep", "产品研发部");
           context.setVariable("salary", 99999);
           context.setVariable("joblevel", "高级工程师");
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        // 邮件是谁发送的? 配置文件配置的什么 就是什么
        helper.setFrom("t2678691035@163.com");
        // 发送时间
        helper.setSentDate(new Date());
        // 邮件要发送给谁
        helper.setTo("2678691035@qq.com");


        javaMailSender.send(message);


    }

    /**
     * Freemarker模板引擎 邮件测试
     */
    @Test
    public void freemarkerTest() throws MessagingException, IOException, TemplateException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        message.setSubject("这个是测试邮件主题(Freemarke)");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(),"templates");
        Template template = configuration.getTemplate("mail.ftl");

        Map<String,Object> map = new HashMap<>();
        map.put("username", "javaboy");
        map.put("position", "java工程师");
        map.put("dep", "产品研发部");
        map.put("salary", 99999);
        map.put("joblevel", "高级工程师");


        StringWriter out = new StringWriter();
        template.process(map, out);

        helper.setText(out.toString(),true);

        // 邮件是谁发送的? 配置文件配置的什么 就是什么
        helper.setFrom("t2678691035@163.com");
        // 发送时间
        helper.setSentDate(new Date());
        // 邮件要发送给谁
        helper.setTo("2678691035@qq.com");


        javaMailSender.send(message);


    }
}
