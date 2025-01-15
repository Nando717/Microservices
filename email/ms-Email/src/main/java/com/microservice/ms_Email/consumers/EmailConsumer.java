package com.microservice.ms_Email.consumers;

import com.microservice.ms_Email.dtos.EmailRecordDto;
import com.microservice.ms_Email.entity.EmailModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void ListenEmailQueue(@Payload EmailRecordDto emailRecordDto){
       var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRecordDto, emailModel);
    // send email
    }
}
