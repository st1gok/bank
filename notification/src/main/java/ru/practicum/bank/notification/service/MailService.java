package ru.practicum.bank.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.practicum.bank.notification.domain.Message;

@Service
public class MailService implements NotificationService {

    private JavaMailSender emailSender;

    @Autowired
    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void notify(Message message) {
        System.out.println("Sending email to: " + message.getRecipient().getEmail()+". Caption: " + message.getCaption()+". Message: " + message.getMessage());
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(account.getEmail());
//        simpleMailMessage.setSubject("Оповещение");
//        simpleMailMessage.setText(message);
//        emailSender.send(simpleMailMessage);
    }
}
