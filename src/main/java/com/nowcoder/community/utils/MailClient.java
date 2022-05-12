package com.nowcoder.community.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author mary
 * @version 1.1
 * @love
 */
@Component
public class MailClient {

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(username);
            helper.setTo(to);
            helper.setText(content, true);
            helper.setSubject(subject);
            javaMailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("send mail error!" + e.getMessage());
        }
    }
}
