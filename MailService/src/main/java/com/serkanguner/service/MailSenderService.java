package com.serkanguner.service;

import com.serkanguner.dto.request.MailCheckUpdateStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = "queue.mail")
    public void sendMail(MailCheckUpdateStatus mailCheckUpdateStatus) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("serkan.guner@yahoo.com");
        simpleMailMessage.setTo(mailCheckUpdateStatus.getEmail());
        simpleMailMessage.setSubject("User Activation");
        simpleMailMessage.setText("Aramıza hoşgeldiniz. Uygulamada hesabınızı aktifleştirmek için lütfen " + mailCheckUpdateStatus.getActivationCode() + "  kodu giriniz.");
        //javaMailSender.send(simpleMailMessage);
        javaMailSender.send(simpleMailMessage);

    }
}
