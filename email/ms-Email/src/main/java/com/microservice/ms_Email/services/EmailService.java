package com.microservice.ms_Email.services;

import com.microservice.ms_Email.entity.EmailModel;
import com.microservice.ms_Email.entity.StatusEmail;
import com.microservice.ms_Email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailRepository emailRepository;
    final  JavaMailSender emailSender;

public EmailService(EmailRepository emailRepository, JavaMailSender emailSender){
    this.emailRepository = emailRepository;
    this.emailSender = emailSender;
}

    @Value(value = "${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public EmailModel sendMessage(EmailModel emailModel){

        try {
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);


            SimpleMailMessage message = new SimpleMailMessage(); // objeto java que envia emails simples.
            message.setTo(emailModel.getEmailTo());     // conteudo que vamos enviar
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message); // carteiro que envia a mensagem

            emailModel.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }
}


