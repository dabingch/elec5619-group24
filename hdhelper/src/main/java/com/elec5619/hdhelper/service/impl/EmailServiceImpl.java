package com.elec5619.hdhelper.service.impl;

import com.elec5619.hdhelper.entity.Post;
import com.elec5619.hdhelper.entity.Student;
import com.elec5619.hdhelper.service.EmailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("dabingchhh@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("Fail to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }

    @Override
    @Async
    public void apply(Student student, Post post) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText("Hi, I'm " + student.getFirstName() + " " + student.getLastName()
                    + ". I would like to join your group in " + post.getCourse().getCourseName()
                    + ". Please email me, my email is:" + student.getEmail(), false);
            helper.setTo(post.getStudent().getEmail());
            helper.setSubject(post.getCourse().getCourseName()
                    + " Group application from " + student.getFirstName() + " " +student.getLastName());
            helper.setFrom("dabingchhh@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("Fail to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
