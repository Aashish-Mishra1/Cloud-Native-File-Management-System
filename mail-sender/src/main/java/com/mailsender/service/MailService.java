package com.mailsender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void send(String link) {
        log.info("Sending verification email for link: {}", link);
        var data = link.substring(link.lastIndexOf("data") + 5);
        var verificationToken = link.substring(link.indexOf('=') + 1, link.indexOf("data") - 1);
        var linkToSend = "http://localhost:4200/verification?token=" + verificationToken + "&data=" + data;
        javaMailSender.send(create.apply(getEmail.apply(link), linkToSend));
    }

    private final Function<String, String> getEmail =
            link -> link.substring("http://localhost:8080/api/v1/user/".length(), link.indexOf('?'));

    private final BiFunction<String, String, SimpleMailMessage> create = (email, link) -> {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(fromEmail);
        message.setSubject("Verify your Cloud File Manager account");
        message.setText("Click the link below to verify your account:\n\n" + link);
        return message;
    };
}
