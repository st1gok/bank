package ru.practicum.bank.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
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
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(message.getRecipient().getEmail());
            simpleMailMessage.setSubject(message.getCaption());
            simpleMailMessage.setText(message.getMessage());
            emailSender.send(simpleMailMessage);
        } catch (MailException e) {
            System.out.println("Error sending email to: " + message.getRecipient().getEmail());
        }

    }
}
