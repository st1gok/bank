package ru.practicum.bank.notification.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.practicum.bank.notification.domain.Message;

@Service
public class MailService implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender emailSender;
    private final MeterRegistry meterRegistry;

    @Autowired
    public MailService(JavaMailSender emailSender, MeterRegistry meterRegistry) {
        this.emailSender = emailSender;
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void notify(Message message) {
        log.info("Sending email to: " + message.getRecipient().getEmail()+". Caption: " + message.getCaption()+". Message: " + message.getMessage());
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(message.getRecipient().getEmail());
            simpleMailMessage.setSubject(message.getCaption());
            simpleMailMessage.setText(message.getMessage());
            emailSender.send(simpleMailMessage);
        } catch (MailException e) {
            meterRegistry.counter("notify_failure_total",
                            "channel", "mail")
                    .increment();
            log.warn("Error sending email to: " + message.getRecipient().getEmail());
        }

    }
}
