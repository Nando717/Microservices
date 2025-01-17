package com.microservice.ms_Email.consumers;

import com.microservice.ms_Email.dtos.EmailRecordDto;
import com.microservice.ms_Email.entity.EmailModel;
import com.microservice.ms_Email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void ListenEmailQueue(@Payload EmailRecordDto emailRecordDto){
       var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRecordDto, emailModel);
        emailService.sendMessage(emailModel);
    // send email
    }
}
