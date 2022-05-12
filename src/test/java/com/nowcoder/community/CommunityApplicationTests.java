package com.nowcoder.community;

import com.nowcoder.community.utils.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    private MailClient mailClient;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    void contextLoads() {
    }
    @Test
    void testSendMail() {
        mailClient.sendMail("i4maxin@outlook.com", "TEST", "Welcome！");
    }
    @Test
    void testSendMailFroHtml() {
        Context context = new Context();
        context.setVariable("username","MaXin");
        final String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);
        mailClient.sendMail("i4maxin@outlook.com", "HTML", content);
    }
}
