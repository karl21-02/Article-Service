package com.project.blog.domain.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 이메일 발송 관련 코드
 */
@Service
@Setter
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private final JavaMailSender mailSender;

    public void send(String to, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage(); // 이 객체 안에 데이터를 넣어야한다.
//
        try {
            mimeMessage.addRecipients(MimeMessage.RecipientType.TO, to);
            mimeMessage.setSubject(subject);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(to); // 메일 수신자 설정
            mimeMessageHelper.setSubject(subject); // 메일 제목 설정
            mimeMessageHelper.setText(body, true); // 메일 본문 내용, html true는 메일 본문에 html이 있을떄
            mailSender.send(mimeMessage); // 메일 발송


        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }
}
