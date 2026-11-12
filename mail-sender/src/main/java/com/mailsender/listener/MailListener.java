package com.mailsender.listener;

import com.mailsender.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailListener {

    private final MailService mailService;

    @KafkaListener(topics = "greeting", groupId = "mail")
    void sendGreeting(String link) {
        mailService.send(link);
    }
}
