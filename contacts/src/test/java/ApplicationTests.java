import contacts.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("zhaokunw@126.com");  //发件人
        message.setTo("zhaokunaaaaa@126.com");  //收件人
        message.setSubject("主题：简单邮件");  //主题
        message.setText("测试邮件内容");  //内容

        mailSender.send(message);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("zhaokunw@126.com");
        helper.setTo("zhaokunaaaaa@126.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("./src/main/resources/wx.txt"));
        //FileSystemResource file = new FileSystemResource(new File("/tmp/file.txt"));文件无内容，用户无权限
        helper.addAttachment("附件-1.txt", file);
        helper.addAttachment("附件-2.txt", file);

        mailSender.send(mimeMessage);

    }
}
